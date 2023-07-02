package br.com.example.gerenciamento.persistencia;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class QuadraDAO {
    private static QuadraDAO instance = null;
    private static BlocoDAO blocoDAO = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> quadras = null;
    private MongoCollection<Document> controle = null;
    
    public static QuadraDAO getInstance() throws MongoException{
        if(instance == null) {
            instance = new QuadraDAO();
        }
        return instance;
    }
    
    private QuadraDAO() throws MongoException {
        conexao = Conexao.getConexao();
        blocoDAO = BlocoDAO.getInstance();
        controle = conexao.getCollection("controle");
    }

    public void insert(Quadra quadra) throws SelectException, InsertException {
        try {
            quadras = conexao.getCollection("quadra");

            // Busca o último valor serial utilizado
            Document filtro = new Document("_id", "ultimoIdQuadra");
            Document documentoControle = controle.find(filtro).first();
            int ultimoIdQuadra = 0;
            if (documentoControle != null) {
                ultimoIdQuadra = documentoControle.getInteger("valor");
            }

            // Gera um novo valor serial
            int novoIdQuadra = ultimoIdQuadra + 1;

            Document documento = new Document();
            documento.append("id_quadra", novoIdQuadra);
            documento.append("modalidade", quadra.getModalidade());
            documento.append("descricao", quadra.getDescricao());
            documento.append("id_bloco", quadra.getId_bloco().getId_bloco());

            // Insere o documento na coleção "quadra"
            quadras.insertOne(documento);

            // Atualiza o último valor serial utilizado no controle
            Document update = new Document("$set", new Document("valor", novoIdQuadra));
            controle.updateOne(filtro, update, new UpdateOptions().upsert(true));
        } catch (MongoException e) {
            throw new InsertException("Quadra");
        }
    }

    public void delete(Quadra quadra) throws DeleteException {
        try {
        	quadras = conexao.getCollection("quadra");

            Bson filtro = eq("id_quadra", quadra.getId_quadra());

            quadras.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Quadra");
        }
    }

    public void update(Quadra quadra) throws UpdateException {
        try {
            // Abre a coleção "quadra"
            quadras = conexao.getCollection("quadra");

            // Define o filtro para encontrar a quadra pelo id_quadra
            Bson filtro = eq("id_quadra", quadra.getId_quadra());

            // Define o objeto de atualização com os campos a serem modificados
            Bson update = new Document("$set", new Document("modalidade", quadra.getModalidade())
                                                    .append("descricao", quadra.getDescricao())
                                                    .append("id_bloco", quadra.getId_bloco().getId_bloco()));

            // Atualiza o documento correspondente ao filtro
            quadras.updateOne(filtro, update);
        } catch (MongoException e) {
            throw new UpdateException("Quadra");
        }
    }

    public Quadra select(int id_quadra) throws SelectException {
        try {
            quadras = conexao.getCollection("quadra");

            Bson filtro = eq("id_quadra", id_quadra);

            FindIterable<Document> resultado = quadras.find(filtro);

            Document documento = resultado.first();
            if (documento != null) {
                String modalidade = documento.getString("modalidade");
                String descricao = documento.getString("descricao");
                int id_bloco = documento.getInteger("id_bloco");
                Bloco bloco = blocoDAO.select(id_bloco);

                return new Quadra(id_quadra, modalidade, descricao, bloco);
            }
        } catch (MongoException e) {
            throw new SelectException("Quadra");
        }
        return null;
    }

    public List<Quadra> selectAll() throws SelectException {
        List<Quadra> lista = new LinkedList<>();
        try {
            quadras = conexao.getCollection("quadra");

            FindIterable<Document> resultado = quadras.find();

            for (Document documento : resultado) {
                int id_quadra = documento.getInteger("id_quadra");
                String modalidade = documento.getString("modalidade");
                String descricao = documento.getString("descricao");
                int id_bloco = documento.getInteger("id_bloco");
                Bloco bloco = blocoDAO.select(id_bloco);

                lista.add(new Quadra(id_quadra, modalidade, descricao, bloco));
            }
        } catch (MongoException e) {
            throw new SelectException("Quadra");
        }
        return lista;
    }


}
