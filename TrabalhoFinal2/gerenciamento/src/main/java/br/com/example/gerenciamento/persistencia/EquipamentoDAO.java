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
import com.mongodb.client.model.UpdateOptions;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class EquipamentoDAO {
    private static EquipamentoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> equips = null;
    private MongoCollection<Document> controle = null;
    
    public static EquipamentoDAO getInstance() throws MongoException{
        if(instance == null) {
            instance = new EquipamentoDAO();
        }
        return instance;
    }
    
    private EquipamentoDAO() throws  MongoException{
        conexao = Conexao.getConexao();
        controle = conexao.getCollection("controle");
    }
    
    public void insert(Equipamento equipamento) throws SelectException, InsertException {
        try {
            equips = conexao.getCollection("equipamento");

            // Busca o último valor serial utilizado
            Document filtro = new Document("_id", "ultimoIdEquipamento");
            Document documentoControle = controle.find(filtro).first();
            int ultimoIdEquipamento = 0;
            if (documentoControle != null) {
                ultimoIdEquipamento = documentoControle.getInteger("valor");
            }

            // Gera um novo valor serial
            int novoIdEquipamento = ultimoIdEquipamento + 1;

            Document documento = new Document();
            documento.append("id_equipamento", novoIdEquipamento);
            documento.append("tipo", equipamento.getTipo());
            documento.append("descricao", equipamento.getDescricao());

            equips.insertOne(documento);

            // Atualiza o último valor serial utilizado no controle
            Document update = new Document("$set", new Document("valor", novoIdEquipamento));
            controle.updateOne(filtro, update, new UpdateOptions().upsert(true));
        } catch (MongoException e) {
            throw new InsertException("Equipamento");
        }
    }





 public void delete(Equipamento equipamento) throws DeleteException {
     try {
         equips = conexao.getCollection("equipamento");

         Bson filtro = eq("id_equipamento", equipamento.getId_equipamento());

         equips.deleteOne(filtro);
     } catch (MongoException e) {
         throw new DeleteException("Equipamento");
     }
 }

    
 public void update(Equipamento equipamento) throws UpdateException {
	    try {
	    	equips = conexao.getCollection("equipamento");

	        Bson filtro = eq("id_equipamento", equipamento.getId_equipamento());

	        // Cria o objeto de atualização com os campos a serem modificados
	        Bson update = new Document("$set", new Document("descricao", equipamento.getDescricao())
                    .append("tipo", equipamento.getTipo()));


	        // Atualiza o documento na coleção
	        equips.updateOne(filtro, update);
	    } catch (MongoException e) {
	        throw new UpdateException("Equipamento");
	    }
	}
    
 public Equipamento select(int id_equipamento) throws SelectException {
	    try {
	        equips = conexao.getCollection("equipamento");

	        Bson filtro = eq("id_equipamento", id_equipamento);

	        FindIterable<Document> resultados = equips.find(filtro);

	        Document documento = resultados.first();
	        if (documento != null) {
	            String tipo = documento.getString("tipo");
	            String descricao = documento.getString("descricao");

	            return new Equipamento(id_equipamento, tipo, descricao);
	        }
	    } catch (MongoException e) {
	        throw new SelectException("Equipamento");
	    }
	    return null;
	}
    
 public List<Equipamento> selectAll() throws SelectException {
	    List<Equipamento> lista = new LinkedList<>();
	    try {
	        equips = conexao.getCollection("equipamento");

	        FindIterable<Document> resultados = equips.find();

	        for (Document documento : resultados) {
	            int id_equipamento = documento.getInteger("id_equipamento");
	            String tipo = documento.getString("tipo");
	            String descricao = documento.getString("descricao");

	            lista.add(new Equipamento(id_equipamento, tipo, descricao));
	        }
	    } catch (MongoException e) {
	        throw new SelectException("Equipamento");
	    }
	    return lista;
	}

	

}
