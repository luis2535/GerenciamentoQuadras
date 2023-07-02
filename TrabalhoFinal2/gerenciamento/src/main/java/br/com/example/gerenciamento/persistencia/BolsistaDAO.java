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

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class BolsistaDAO {
    private static BolsistaDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    private static MongoDatabase conexao = null;
	private MongoCollection<Document> bolsistas = null;

    public static BolsistaDAO getInstance() throws MongoException {
        if (instance == null) {
            instance = new BolsistaDAO();
        }
        return instance;
    }

    private BolsistaDAO() throws MongoException {
       conexao = Conexao.getConexao();
       usuarioDAO = UsuarioDAO.getInstance();
    }
   

    public void insert(Bolsista bolsista) throws SelectException, InsertException, UpdateException {
        try {
            // Verifica se o usuário já existe
            Usuario usuarioExistente = usuarioDAO.select(bolsista.getCpf());
            if (usuarioExistente == null) {
                // Se o usuário não existir, insere o novo usuário
                usuarioDAO.insert(bolsista);
            } else {
                // Se o usuário já existir, atualiza os dados do usuário existente
                usuarioDAO.update(bolsista);
            }

            // Insere os dados específicos do bolsista no MongoDB
            bolsistas = conexao.getCollection("bolsista");

            Document documento = new Document();
            documento.append("_id", new ObjectId());
            documento.append("cpf", bolsista.getCpf());
            documento.append("pnome", bolsista.getPnome());
            documento.append("unome", bolsista.getUnome());
            documento.append("email", bolsista.getEmail());
            documento.append("senha", bolsista.getSenha());
            documento.append("status", bolsista.getStatus());
            documento.append("funcao", bolsista.getFuncao());

            // Insere o documento na coleção
            bolsistas.insertOne(documento);

            // Renomeia o campo _id para id_bolsista
            Bson filtro = eq("_id", documento.get("_id"));
            Bson operacao = new Document("$rename", new Document("_id", "id_bolsista"));
            bolsistas.updateOne(filtro, operacao);

        } catch (MongoException e) {
            throw new InsertException("Bolsista");
        }
    }

    public boolean isBolsista(String cpf) throws SelectException {
        try {
            bolsistas = conexao.getCollection("bolsista"); // Obter a coleção "bolsista" do MongoDB

            Bson filtro = eq("cpf", cpf);
            long count = bolsistas.countDocuments(filtro);

            return count > 0;
        } catch (MongoException e) {
            throw new SelectException("Bolsista");
        }
    }


    public void delete(Bolsista bolsista) throws DeleteException {
        try {
            bolsistas = conexao.getCollection("bolsista"); // Obter a coleção "bolsista" do MongoDB

            Bson filtro = eq("cpf", bolsista.getCpf());
            bolsistas.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Bolsista");
        }
    }

    public void update(Bolsista bolsista) throws UpdateException {
        try {
            bolsistas = conexao.getCollection("bolsista"); // Obter a coleção "bolsista" do MongoDB

            Bson filtro = eq("cpf", bolsista.getCpf());

            Bson atualizacao = new Document("$set", new Document("pnome", bolsista.getPnome())
                    .append("unome", bolsista.getUnome())
                    .append("email", bolsista.getEmail())
                    .append("senha", bolsista.getSenha())
                    .append("status", bolsista.getStatus())
                    .append("funcao", bolsista.getFuncao())
                    .append("id_bolsista", bolsista.getId_bolsista()));

            bolsistas.updateOne(filtro, atualizacao);
        } catch (MongoException e) {
            throw new UpdateException("Bolsista");
        }
    }

    public Bolsista select(String cpf) throws SelectException {
        try {
            bolsistas = conexao.getCollection("bolsista"); // Obter a coleção "bolsista" do MongoDB

            Bson filtro = eq("cpf", cpf);

            FindIterable<Document> resultado = bolsistas.find(filtro);

            Document documento = resultado.first();

            if (documento != null) {
                String pnome = documento.getString("pnome");
                String unome = documento.getString("unome");
                String email = documento.getString("email");
                String senha = documento.getString("senha");
                String status = documento.getString("status");
                String funcao = documento.getString("funcao");
                int id_bolsista = documento.getInteger("id_bolsista");

                return new Bolsista(cpf, pnome, unome, email, senha, status, funcao, id_bolsista);
            }
        } catch (MongoException e) {
            throw new SelectException("Bolsista");
        }

        return null;
    }

    
    public List<Bolsista> selectAll() throws SelectException {
        List<Bolsista> lista = new LinkedList<>();

        try {
            bolsistas = conexao.getCollection("bolsista"); // Obter a coleção "bolsista" do MongoDB

            FindIterable<Document> resultado = bolsistas.find();

            for (Document documento : resultado) {
                String cpf = documento.getString("cpf");
                String pnome = documento.getString("pnome");
                String unome = documento.getString("unome");
                String email = documento.getString("email");
                String senha = documento.getString("senha");
                String status = documento.getString("status");
                String funcao = documento.getString("funcao");
                int id_bolsista = documento.getInteger("id_bolsista");

                lista.add(new Bolsista(cpf, pnome, unome, email, senha, status, funcao, id_bolsista));
            }
        } catch (MongoException e) {
            throw new SelectException("Bolsista");
        }

        return lista;
    }

    

    	
   


           
}
