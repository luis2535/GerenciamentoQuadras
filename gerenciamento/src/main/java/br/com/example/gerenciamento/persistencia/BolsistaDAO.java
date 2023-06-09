package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;


public class BolsistaDAO {
    private static BolsistaDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    
    private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement isBolsista;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;

    public static BolsistaDAO getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new BolsistaDAO();
        }
        return instance;
    }

    private BolsistaDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        
        selectNewId = conexao.prepareStatement("SELECT nextval('bolsista_id_seq');");
        insert = conexao.prepareStatement("INSERT INTO Bolsista (cpf, pnome, unome, email, senha, status, funcao, id_bolsista) VALUES (?,?,?,?,?,?,?,?);");
        isBolsista = conexao.prepareStatement("SELECT COUNT(*) FROM Bolsista WHERE cpf = ?");
        delete = conexao.prepareStatement("DELETE FROM Bolsista WHERE cpf = ?;");
        update = conexao.prepareStatement(
                "UPDATE Bolsista SET pnome = ?, unome = ?, email = ?, senha = ?, status = ?, funcao = ?, id_bolsista = ? WHERE cpf = ?;");
        select = conexao.prepareStatement("SELECT * FROM Bolsista WHERE cpf = ?;");
        selectAll = conexao.prepareStatement("SELECT * FROM Bolsista;");
    
        usuarioDAO = UsuarioDAO.getInstance();
    }
    
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela Bolsista");
        }
        return 0;
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

            // Insere os dados específicos do bolsista
            insert.setString(1, bolsista.getCpf());
            insert.setString(2, bolsista.getPnome());
            insert.setString(3, bolsista.getUnome());
            insert.setString(4, bolsista.getEmail());
            insert.setString(5, bolsista.getSenha());
            insert.setString(6, bolsista.getStatus());
            insert.setString(7, bolsista.getFuncao());
            insert.setInt(8, selectNewId());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("Bolsista");
        }
    }
    public boolean isBolsista(String cpf) throws SelectException {
    	
    	try {
    	isBolsista.setString(1, cpf);
    	
    	ResultSet rs = isBolsista.executeQuery();
    		
    	if(rs.next()) {
    		int count = rs.getInt(1);
    		return count > 0;
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Bolsista");
    	}
    	return false;
    }
    public void delete(Bolsista bolsista) throws DeleteException {
    	try {
    		delete.setString(1, bolsista.getCpf());
    		delete.executeUpdate();
    	}catch(SQLException e) {
    		throw new DeleteException("BOlsista");
    	}
    }
    public void update(Bolsista bolsista) throws UpdateException{
    	try {
    		update.setString(1, bolsista.getPnome());
        	update.setString(2, bolsista.getUnome());
        	update.setString(3, bolsista.getEmail());
        	update.setString(4, bolsista.getSenha());
        	update.setString(5, bolsista.getStatus());
        	update.setString(6, bolsista.getFuncao());
        	update.setInt(7, bolsista.getId_bolsista());
        	update.setString(8, bolsista.getCpf());
        	update.executeUpdate();
    	} catch(SQLException e) {
    		throw new UpdateException("Bolsista");
    	}
    }
    public Bolsista select(String cpf) throws SelectException{
    	try {
            select.setString(1, cpf);
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                String funcao = rs.getString(7);
                int id_bolsista = rs.getInt(8);
                
                
                

                return new Bolsista(cpf, pnome, unome, email, senha, status, funcao, id_bolsista);
            }

        } catch (SQLException e) {
            throw new SelectException("Usuario");
        }

        return null;

    }
    
    public List<Bolsista> selectAll() throws SelectException {

        List<Bolsista> lista = new LinkedList<>();

        try {
            ResultSet rs = selectAll.executeQuery();

            while(rs.next()){
                String cpf = rs.getString(1);
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                String funcao = rs.getString(7);
                int id_bolsista = rs.getInt(8);
                
                

                lista.add(new Bolsista(cpf, pnome, unome, email, senha, status, funcao, id_bolsista));
            }
        } catch (SQLException e) {
            throw new SelectException("Usuario");
        }

        return lista;
    }
    

    	
   


           
}
