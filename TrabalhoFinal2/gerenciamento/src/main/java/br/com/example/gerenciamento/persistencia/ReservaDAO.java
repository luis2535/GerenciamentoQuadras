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

public class ReservaDAO {
	private static ReservaDAO instance = null;
	private static EquipamentoDAO equipamentoDAO = null;
	private static AgendamentoDAO agendamentoDAO = null;
	private static MongoDatabase conexao = null;
	private MongoCollection<Document> reservas = null;
    private MongoCollection<Document> controle = null;


    
    public static ReservaDAO getInstance() throws MongoException{
    	if(instance == null) {
    		instance = new ReservaDAO();
    	}
    	return instance;
    	
    }
    
    private ReservaDAO() throws MongoException{
    	conexao = Conexao.getConexao();
    	
    	equipamentoDAO = EquipamentoDAO.getInstance();
    	agendamentoDAO = AgendamentoDAO.getInstance();
        controle = conexao.getCollection("controle");

    }

    
    public void insert(Reserva reserva) throws InsertException, SelectException {
        try {
            reservas = conexao.getCollection("reserva");
            
            Document filtro = new Document("_id", "ultimoIdReserva");
            Document documentoControle = controle.find(filtro).first();
            int ultimoIdReserva = 0;
            if (documentoControle != null) {
            	ultimoIdReserva = documentoControle.getInteger("valor");
            }

            // Gera um novo valor serial
            int novoIdReserva = ultimoIdReserva + 1;

            Document documento = new Document("id_reserva", novoIdReserva)
                .append("agendamento_id", reserva.getAgendamento().getId_agendamento())
                .append("equipamento_id", reserva.getEquipamento().getId_equipamento())
                .append("quantidade_equip", reserva.getQuantidade_equip());

            reservas.insertOne(documento);
            
            Document update = new Document("$set", new Document("valor", novoIdReserva));
            controle.updateOne(filtro, update, new UpdateOptions().upsert(true));
        } catch (MongoException e) {
            throw new InsertException("Reserva");
        }
    }

    public void delete(Reserva reserva) throws DeleteException {
        try {
            reservas = conexao.getCollection("reserva");

            Bson filtro = eq("id_agendamento", reserva.getAgendamento().getId_agendamento());
            reservas.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Reserva");
        }
    }
}


