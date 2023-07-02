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

public class EventoDAO {
    private static EventoDAO instance = null;
    private static AdminDAO servidorDAO = null;
    private static QuadraDAO quadraDAO = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> eventos = null;
    private MongoCollection<Document> quadraeventos = null;
    private MongoCollection<Document> controle = null;


    public static EventoDAO getInstance() throws MongoException {
        if (instance == null) {
            instance = new EventoDAO();
        }
        return instance;
    }

    private EventoDAO() throws MongoException {
        conexao = Conexao.getConexao();

        servidorDAO = AdminDAO.getInstance();
        quadraDAO = QuadraDAO.getInstance();
        controle = conexao.getCollection("controle");

    }

    public int insert(Evento evento) throws SelectException, InsertException {
        try {
            eventos = conexao.getCollection("evento");

            // Busca o último valor serial utilizado
            Document filtro = new Document("_id", "ultimoIdEvento");
            Document documentoControle = controle.find(filtro).first();
            int ultimoIdEvento = 0;
            if (documentoControle != null) {
                ultimoIdEvento = documentoControle.getInteger("valor");
            }

            // Gera um novo valor serial
            int novoIdEvento = ultimoIdEvento + 1;

            Document documento = new Document();
            documento.append("id_evento", novoIdEvento)
                    .append("data", evento.getData())
                    .append("horario_inicio", evento.getHorario_inicio())
                    .append("horario_fim", evento.getHorario_fim())
                    .append("status", evento.getStatus())
                    .append("nome", evento.getNome())
                    .append("cpf", evento.getServidor().getCpf());

            eventos.insertOne(documento);

            // Atualiza o último valor serial utilizado no controle
            Document update = new Document("$set", new Document("valor", novoIdEvento));
            controle.updateOne(filtro, update, new UpdateOptions().upsert(true));

            return novoIdEvento;
        } catch (MongoException e) {
            throw new InsertException("Evento");
        }
    }

    public void delete(Evento evento) throws DeleteException {
        try {
            eventos = conexao.getCollection("evento");

            Bson filtro = eq("id_evento", evento.getId_evento());

            eventos.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Evento");
        }
    }

    
    public void update(Evento evento) throws UpdateException {
        try {
            eventos = conexao.getCollection("evento");

            // Cria um filtro para encontrar o documento do evento pelo ID
            Bson filtro = eq("id_evento", evento.getId_evento());

            // Cria um documento com os campos a serem atualizados
            Document atualizacao = new Document("$set", new Document()
                .append("data", evento.getData())
                .append("horario_inicio", evento.getHorario_inicio())
                .append("horario_fim", evento.getHorario_fim())
                .append("status", evento.getStatus())
                .append("nome", evento.getNome())
                .append("servidor.cpf", evento.getServidor().getCpf())
            );

            // Executa a atualização do documento que corresponde ao filtro
            eventos.updateOne(filtro, atualizacao);
        } catch (MongoException e) {
            throw new UpdateException("Evento");
        }
    }

    
    ////////////////Preciso arrumar a partir daqui

    
    public List<Quadra> selectQuadraEvento(int id_evento) throws SelectException {
        List<Quadra> quadras = new LinkedList<>();
        try {
            quadraeventos = conexao.getCollection("quadraevento");

            Bson filtro = eq("id_evento", id_evento);

            FindIterable<Document> resultado = eventos.find(filtro);

            if (resultado.iterator().hasNext()) {
                // Obtém o documento do evento encontrado
                Document documento = resultado.first();

                // Obtém a lista de quadras do evento
                List<Document> quadrasDocumento = documento.getList("quadras", Document.class);

                // Itera sobre os documentos de quadras
                for (Document quadraDocumento : quadrasDocumento) {
                    // Obtém o ID da quadra
                    int id_quadra = quadraDocumento.getInteger("_id");

                    // Busca a quadra pelo ID e adiciona na lista
                    Quadra quadra = quadraDAO.select(id_quadra);
                    quadras.add(quadra);
                }
            }
        } catch (MongoException e) {
            throw new SelectException("Eventos");
        }
        return quadras;
    }

    public Evento select(int id_evento) throws SelectException {
        try {
            eventos = conexao.getCollection("evento");

            // Cria um filtro para encontrar o evento pelo ID
            Bson filtro = eq("id_evento", id_evento);

            // Executa a busca pelo evento com o filtro
            FindIterable<Document> resultado = eventos.find(filtro);

            // Verifica se encontrou o evento
            if (resultado.iterator().hasNext()) {
                // Obtém o documento do evento encontrado
                Document documento = resultado.first();

                // Obtém os dados do evento do documento
                String data = documento.getString("data");
                String horario_inicio = documento.getString("horario_inicio");
                String horario_fim = documento.getString("horario_fim");
                String status = documento.getString("status");
                String nome = documento.getString("nome");
                String cpf = documento.getString("cpf");

                // Busca o servidor pelo CPF
                Admin servidor = servidorDAO.select(cpf);

                // Obtém a lista de quadras do evento
                List<Quadra> quadras = selectQuadraEvento(id_evento);

                // Cria e retorna o objeto Evento
                return new Evento(id_evento, data, horario_inicio, horario_fim, status, nome, servidor, quadras);
            }
        } catch (MongoException e) {
            throw new SelectException("Evento");
        }
        return null;
    }

  
    public List<Evento> selectAll() throws SelectException {
        List<Evento> lista = new LinkedList<>();
        try {
            eventos = conexao.getCollection("evento");

            // Executa a busca de todos os eventos
            FindIterable<Document> resultado = eventos.find();

            // Percorre os documentos encontrados
            for (Document documento : resultado) {
                // Obtém os dados do evento do documento
                int id_evento = documento.getInteger("id_evento");
                String data = documento.getString("data");
                String horario_inicio = documento.getString("horario_inicio");
                String horario_fim = documento.getString("horario_fim");
                String status = documento.getString("status");
                String nome = documento.getString("nome");
                String cpf = documento.getString("cpf");

                // Busca o servidor pelo CPF
                Admin servidor = servidorDAO.select(cpf);

                // Obtém a lista de quadras do evento
                List<Quadra> quadras = selectQuadraEvento(id_evento);

                // Adiciona o evento à lista
                lista.add(new Evento(id_evento, data, horario_inicio, horario_fim, status, nome, servidor, quadras));
            }
        } catch (MongoException e) {
            throw new SelectException("Evento");
        }
        return lista;
    }

  
    public void insertQuadraEvento(Evento evento, Quadra quadra) throws InsertException {
        try {
            quadraeventos = conexao.getCollection("quadraevento");

            Document documento = new Document();
            documento.append("id_evento", evento.getId_evento())
            		.append("id_quadra", quadra.getId_quadra());
            
            quadraeventos.insertOne(documento);
        } catch (MongoException e) {
            throw new InsertException("Quadras para evento: " + e.getMessage());
        }
    }



  
  public void deleteQuadraEvento(Evento evento, Quadra quadra) throws DeleteException{
	  try {
		  quadraeventos = conexao.getCollection("quadraevento");
		  Bson filtro = eq("id_evento", evento.getId_evento());
		  quadraeventos.deleteOne(filtro);
		  
	  }catch(MongoException e) {
		  throw new DeleteException("QuadraEvento");
	  }
  }
    
    	
    
    

}
