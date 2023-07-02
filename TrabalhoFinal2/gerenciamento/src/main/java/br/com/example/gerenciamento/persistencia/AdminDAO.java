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


public class AdminDAO {
	private static AdminDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    private static MongoDatabase conexao = null;
	private MongoCollection<Document> admins = null;

    public static AdminDAO getInstance() throws MongoException {
        if (instance == null) {
            instance = new AdminDAO();
        }
        return instance;
    }
    
    private AdminDAO() throws MongoException {
        conexao = Conexao.getConexao();
        usuarioDAO = UsuarioDAO.getInstance();
    }




    public void insert(Admin servidor) throws SelectException, InsertException, UpdateException {
        try {
            // Verifica se o usuário já existe
            Usuario usuarioExistente = usuarioDAO.select(servidor.getCpf());
            if (usuarioExistente == null) {
                // Se o usuário não existir, insere o novo usuário
                usuarioDAO.insert(servidor);
            } else {
                // Se o usuário já existir, atualiza os dados do usuário existente
                usuarioDAO.update(servidor);
            }

            // Insere os dados específicos do administrador
            admins = conexao.getCollection("admin");
            
            Document documento = new Document();
            documento.append("_id", new ObjectId());
            documento.append("pnome", servidor.getPnome());
            documento.append("unome", servidor.getUnome());
            documento.append("email", servidor.getEmail());
            documento.append("senha", servidor.getSenha());
            documento.append("status", servidor.getStatus());
            documento.append("funcao", servidor.getFuncao());
            
            admins.insertOne(documento);
            Bson filtro = eq("_id", documento.get("_id"));
            Bson operacao = new Document("$rename", new Document("_id", "id_admin"));
        } catch (MongoException e) {
            throw new InsertException("servidor");
        }
    }

    public boolean isAdmin(String cpf) throws SelectException {
        try {
            admins = conexao.getCollection("admin");

            Bson filtro = eq("cpf", cpf);
            long count = admins.countDocuments(filtro);

            return count > 0;
        } catch (MongoException e) {
            throw new SelectException("Admin");
        }
    }

    public void delete(Admin servidor) throws DeleteException {
        try {
            admins = conexao.getCollection("admin");
            Bson filtro = eq("cpf", servidor.getCpf());
            admins.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("servidor");
        }
    }

    public void update(Admin servidor) throws UpdateException {
        try {
            admins = conexao.getCollection("admin");
            
            Bson filtro = eq("cpf", servidor.getCpf());
            
            Document documento = new Document();
            documento.append("cpf", servidor.getCpf());
            documento.append("pnome", servidor.getPnome());
            documento.append("unome", servidor.getUnome());
            documento.append("email", servidor.getEmail());
            documento.append("senha", servidor.getSenha());
            documento.append("status", servidor.getStatus());
            documento.append("funcao", servidor.getFuncao());
            
            Document atualizacao = new Document("$set", documento);
            
            admins.updateOne(filtro, atualizacao);
        } catch (MongoException e) {
            throw new UpdateException("servidor");
        }
    }

    public Admin select(String cpf) throws SelectException {
        try {
            admins = conexao.getCollection("admin");
            
            Bson filtro = eq("cpf", cpf);
            
            FindIterable<Document> resultado = admins.find(filtro);
            
            Document documento = resultado.first();
            
            if (documento != null) {
                String pnome = documento.getString("pnome");
                String unome = documento.getString("unome");
                String email = documento.getString("email");
                String senha = documento.getString("senha");
                String status = documento.getString("status");
                String funcao = documento.getString("funcao");
                int id_admin = documento.getInteger("id_admin");
                
                return new Admin(cpf, pnome, unome, email, senha, status, funcao, id_admin);
            }
        } catch (MongoException e) {
            throw new SelectException("servidor");
        }
        
        return null;
    }

    
    public List<Admin> selectAll() throws SelectException {
        List<Admin> lista = new LinkedList<>();
        
        try {
            admins = conexao.getCollection("admin");
            
            FindIterable<Document> resultado = admins.find();
            
            for (Document documento : resultado) {
                String cpf = documento.getString("cpf");
                String pnome = documento.getString("pnome");
                String unome = documento.getString("unome");
                String email = documento.getString("email");
                String senha = documento.getString("senha");
                String status = documento.getString("status");
                String funcao = documento.getString("funcao");
                int id_admin = documento.getInteger("id_admin");
                
                lista.add(new Admin(cpf, pnome, unome, email, senha, status, funcao, id_admin));
            }
        } catch (MongoException e) {
            throw new SelectException("servidor");
        }
        
        return lista;
    }


}
