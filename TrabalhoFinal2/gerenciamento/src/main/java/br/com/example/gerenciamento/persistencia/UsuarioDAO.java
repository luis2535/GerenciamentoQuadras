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

public class UsuarioDAO {
	private static UsuarioDAO instance = null;
	private static MongoDatabase conexao = null;
	private MongoCollection<Document> usuarios = null;
	
    public static UsuarioDAO getInstance() throws MongoException{
    	if(instance == null) {
    		instance = new UsuarioDAO();
    	}
    	return instance;
    }
	private UsuarioDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

	public void insert(Usuario usuario) throws InsertException{
		try {
			usuarios = conexao.getCollection("usuario");
			
			Document documento = new Document();
			documento.append("cpf", usuario.getCpf());
	        documento.append("pnome", usuario.getPnome());
	        documento.append("unome", usuario.getUnome());
	        documento.append("email", usuario.getEmail());
	        documento.append("senha", usuario.getSenha());
	        documento.append("status", usuario.getStatus());
	        documento.append("funcao", usuario.getFuncao());
	        
	        usuarios.insertOne(documento);
		} catch (MongoException e) {
			throw new InsertException("Usuario");
		}
		
	}
    
	public void delete(Usuario usuario) throws DeleteException {
	    try {
	        usuarios = conexao.getCollection("usuario");
	        
	        Bson filtro = eq("cpf", usuario.getCpf());
	        
	        usuarios.deleteOne(filtro);
	    } catch (MongoException e) {
	        throw new DeleteException("Usuario");
	    }
	}

    
	public void update(Usuario usuario) throws UpdateException {
	    try {
	        usuarios = conexao.getCollection("usuario");
	        
	        Bson filtro = eq("cpf", usuario.getCpf());
	        
	        Document documentoAtualizado = new Document("$set", new Document()
	                .append("pnome", usuario.getPnome())
	                .append("unome", usuario.getUnome())
	                .append("email", usuario.getEmail())
	                .append("senha", usuario.getSenha())
	                .append("status", usuario.getStatus())
	                .append("funcao", usuario.getFuncao()));
	        
	        usuarios.updateOne(filtro, documentoAtualizado);
	    } catch (MongoException e) {
	        throw new UpdateException("Usuario");
	    }
	}



	public void updateUser(List<Usuario> usuariosAll) throws UpdateException {
	    try {
	        usuarios = conexao.getCollection("usuario");
	        
	        for (Usuario usuario : usuariosAll) {
	            Bson filtro = eq("cpf", usuario.getCpf());
	            
	            Document documentoAtualizado = new Document("$set", new Document()
	                    .append("pnome", usuario.getPnome())
	                    .append("unome", usuario.getUnome())
	                    .append("email", usuario.getEmail())
	                    .append("senha", usuario.getSenha())
	                    .append("status", usuario.getStatus())
	                    .append("funcao", usuario.getFuncao()));
	            
	            usuarios.updateOne(filtro, documentoAtualizado);
	        }
	    } catch (MongoException e) {
	        throw new UpdateException("Usuario");
	    }
	}

    
	public Usuario select(String cpf) throws SelectException {
	    try {
	        usuarios = conexao.getCollection("usuario");
	        
	        Bson filtro = eq("cpf", cpf);
	        
	        FindIterable<Document> resultado = usuarios.find(filtro);
	        
	        Document documento = resultado.first();
	        
	        if (documento != null) {
	            String pnome = documento.getString("pnome");
	            String unome = documento.getString("unome");
	            String email = documento.getString("email");
	            String senha = documento.getString("senha");
	            String status = documento.getString("status");
	            String funcao = documento.getString("funcao");
	            
	            return new Usuario(cpf, pnome, unome, email, senha, status, funcao);
	        }
	    } catch (MongoException e) {
	        throw new SelectException("Usuario");
	    }
	    
	    return null;
	}

    
	public List<Usuario> selectAll() throws SelectException {
	    List<Usuario> lista = new LinkedList<>();
	    try {
	        usuarios = conexao.getCollection("usuario");
	        
	        FindIterable<Document> resultado = usuarios.find();
	        
	        for (Document documento : resultado) {
	            String cpf = documento.getString("cpf");
	            String pnome = documento.getString("pnome");
	            String unome = documento.getString("unome");
	            String email = documento.getString("email");
	            String senha = documento.getString("senha");
	            String status = documento.getString("status");
	            String funcao = documento.getString("funcao");
	            
	            lista.add(new Usuario(cpf, pnome, unome, email, senha, status, funcao));
	        }
	    } catch (MongoException e) {
	        throw new SelectException("Usuario");
	    }
	    
	    return lista;
	}

    
}
