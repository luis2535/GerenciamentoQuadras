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


public class AgendamentoDAO {
    private static AgendamentoDAO instance = null;
    private static QuadraDAO quadraDAO = null;
    private static UsuarioDAO usuarioDAO = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> agendamentos = null;
    private MongoCollection<Document> controle = null;
    
    public static AgendamentoDAO getInstance() throws  MongoException{
        if(instance == null) {
            instance = new AgendamentoDAO();
        }
        return instance;
    }
    
    private AgendamentoDAO() throws MongoException{
        conexao = Conexao.getConexao();

        usuarioDAO = UsuarioDAO.getInstance();
        quadraDAO = QuadraDAO.getInstance();
        controle = conexao.getCollection("controle");
    }

    public int insert(Agendamento agendamento) throws SelectException, InsertException {
        try {
            agendamentos = conexao.getCollection("agendamento");

            // Busca o último valor serial utilizado
            Document filtro = new Document("_id", "ultimoIdAgendamento");
            Document documentoControle = controle.find(filtro).first();
            int ultimoIdAgendamento = 0;
            if (documentoControle != null) {
                ultimoIdAgendamento = documentoControle.getInteger("valor");
            }

            // Gera um novo valor serial
            int novoIdAgendamento = ultimoIdAgendamento + 1;

            Document documento = new Document();
            documento.append("id_agendamento", novoIdAgendamento)
                    .append("horario_inicio", agendamento.getHorario_inicio())
                    .append("horario_fim", agendamento.getHorario_fim())
                    .append("data", agendamento.getData())
                    .append("quadra", agendamento.getQuadra().getId_quadra())
                    .append("usuario", agendamento.getUsuario().getCpf())
                    .append("status", agendamento.getStatus());

            agendamentos.insertOne(documento);

            // Atualiza o último valor serial utilizado no controle
            Document update = new Document("$set", new Document("valor", novoIdAgendamento));
            controle.updateOne(filtro, update, new UpdateOptions().upsert(true));

            return novoIdAgendamento;
        } catch (MongoException e) {
            throw new InsertException("Agendamento");
        }
    }
    
    public void delete(Agendamento agendamento) throws DeleteException {
        try {
            agendamentos = conexao.getCollection("agendamento");

            Bson filtro = eq("id_agendamento", agendamento.getId_agendamento());

            agendamentos.deleteOne(filtro);
        } catch (MongoException e) {
            throw new DeleteException("Agendamento");
        }
    }

    
    public void update(Agendamento agendamento) throws UpdateException {
        try {
            agendamentos = conexao.getCollection("agendamento");

            Bson filtro = eq("id_agendamento", agendamento.getId_agendamento());

            Document atualizacao = new Document("$set", new Document()
                    .append("horario_inicio", agendamento.getHorario_inicio())
                    .append("horario_fim", agendamento.getHorario_fim())
                    .append("data", agendamento.getData())
                    .append("status", agendamento.getStatus())
                    .append("usuario", agendamento.getUsuario().getCpf())
                    .append("quadra", agendamento.getQuadra().getId_quadra()));

            agendamentos.updateOne(filtro, atualizacao);
        } catch (MongoException e) {
            throw new UpdateException("Agendamento");
        }
    }


    public Agendamento select(int id_agendamento) throws SelectException {
        try {
        	agendamentos = conexao.getCollection("agendamento");

            Bson filtro = eq("id_agendamento", id_agendamento);

            FindIterable<Document> resultados = agendamentos.find(filtro);

            if (resultados.first() != null) {
                String horario_inicio = resultados.first().getString("horario_inicio");
                String horario_fim = resultados.first().getString("horario_fim");
                String data = resultados.first().getString("data");
                String status = resultados.first().getString("status");
                String cpf = resultados.first().getString("usuario");
                int id_quadra = resultados.first().getInteger("quadra");

                Quadra quadra = quadraDAO.select(id_quadra);
                Usuario usuario = usuarioDAO.select(cpf);

                return new Agendamento(id_agendamento, horario_inicio, horario_fim, data, status, usuario, quadra);
            }
        } catch (MongoException e) {
            throw new SelectException("Agendamento");
        }
        return null;
    }

    
    public List<Agendamento> selectAll() throws SelectException {
        List<Agendamento> lista = new LinkedList<>();

        try {
        	agendamentos = conexao.getCollection("agendamento");

            FindIterable<Document> resultados = agendamentos.find();

            for (Document documento : resultados) {
                int id_agendamento = documento.getInteger("id_agendamento");
                String horario_inicio = documento.getString("horario_inicio");
                String horario_fim = documento.getString("horario_fim");
                String data = documento.getString("data");
                String status = documento.getString("status");
                String cpf = documento.getString("usuario");
                int id_quadra = documento.getInteger("quadra");

                // Realiza as consultas aos DAOs para obter o usuário e a quadra
                Quadra quadra = quadraDAO.select(id_quadra);
                Usuario usuario = usuarioDAO.select(cpf);

                // Cria e adiciona o objeto Agendamento à lista
                lista.add(new Agendamento(id_agendamento, horario_inicio, horario_fim, data, status, usuario, quadra));
            }
        } catch (MongoException e) {
            throw new SelectException("Agendamento");
        }
        return lista;
    }

    


	

}
