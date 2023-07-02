package br.com.example.gerenciamento.persistencia;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class BlocoDAO {
    private static BlocoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> blocos = null;
    private MongoCollection<Document> contadores = null;

    public static BlocoDAO getInstance() throws MongoException {
        if (instance == null) {
            instance = new BlocoDAO();
        }
        return instance;
    }

    ;

    private BlocoDAO() throws MongoException {
        conexao = Conexao.getConexao();

        // Verifica se a coleção de contadores já existe
        if (!conexao.listCollectionNames().into(new ArrayList<>()).contains("contadores")) {
            Document contador = new Document("_id", "bloco")
                    .append("seq", 0);
            conexao.getCollection("contadores").insertOne(contador);
        }

        contadores = conexao.getCollection("contadores");
    }

    public void insert(Bloco bloco) throws SelectException, InsertException, UpdateException {
        try {
            // Insere os dados do bloco
            blocos = conexao.getCollection("bloco");

            Document documento = new Document();
            documento.append("id_bloco", getNextSequenceValue("bloco"));
            documento.append("nome", bloco.getNome());
            documento.append("descricao", bloco.getDescricao());

            blocos.insertOne(documento);
        } catch (MongoException e) {
            throw new InsertException("Bloco");
        }
    }

    public void delete(Bloco bloco) throws DeleteException {
        try {
            blocos = conexao.getCollection("bloco");

            Bson filtro = eq("id_bloco", bloco.getId_bloco());
            blocos.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Bloco");
        }
    }

    public void update(Bloco bloco) throws UpdateException {
        try {
            blocos = conexao.getCollection("bloco");

            Bson filtro = eq("id_bloco", bloco.getId_bloco());

            Bson update = new Document("$set", new Document("nome", bloco.getNome())
                    .append("descricao", bloco.getDescricao()));

            blocos.updateOne(filtro, update);
        } catch (MongoException e) {
            throw new UpdateException("Bloco");
        }
    }

    public Bloco select(int id_bloco) throws SelectException {
        try {
            blocos = conexao.getCollection("bloco");

            Bson filtro = eq("id_bloco", id_bloco);
            FindIterable<Document> resultado = blocos.find(filtro);

            if (resultado.iterator().hasNext()) {
                Document documento = resultado.first();
                String nome = documento.getString("nome");
                String descricao = documento.getString("descricao");

                return new Bloco(id_bloco, nome, descricao);
            }
        } catch (MongoException e) {
            throw new SelectException("Bloco");
        }
        return null;
    }

    public List<Bloco> selectAll() throws SelectException {
        List<Bloco> lista = new LinkedList<>();

        try {
            blocos = conexao.getCollection("bloco");
            FindIterable<Document> resultado = blocos.find();

            for (Document documento : resultado) {
                int id_bloco = documento.getInteger("id_bloco");
                String nome = documento.getString("nome");
                String descricao = documento.getString("descricao");

                lista.add(new Bloco(id_bloco, nome, descricao));
            }
        } catch (MongoException e) {
            throw new SelectException("Bloco");
        }

        return lista;
    }

    private int getNextSequenceValue(String sequenceName) {
        Document find = new Document("_id", sequenceName);
        Document update = new Document("$inc", new Document("seq", 1));
        Document options = new Document("new", true);

        Document sequenceDocument = contadores.findOneAndUpdate(find, update);

        return sequenceDocument.getInteger("seq");
    }
}
