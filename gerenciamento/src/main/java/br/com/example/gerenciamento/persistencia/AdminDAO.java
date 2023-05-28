package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;


public class AdminDAO {
	private static AdminDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    
    private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement isAdmin;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;

    public static AdminDAO getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new AdminDAO();
        }
        return instance;
    }
    
    private AdminDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        
        selectNewId = conexao.prepareStatement("SELECT nextval('admin_id_seq');");
        insert = conexao.prepareStatement("INSERT INTO Admin (cpf, pnome, unome, email, senha, status, funcao, id_admin) VALUES (?,?,?,?,?,?,?,?);");
        isAdmin = conexao.prepareStatement("SELECT COUNT(*) FROM Admin WHERE cpf = ?");
        delete = conexao.prepareStatement("DELETE FROM Admin WHERE cpf = ?;");
        update = conexao.prepareStatement(
                "UPDATE Admin SET pnome = ?, unome = ?, email = ?, senha = ?, status = ?, funcao = ?, id_admin = ? WHERE cpf = ?;");
        select = conexao.prepareStatement("SELECT * FROM Admin WHERE cpf = ?;");
        selectAll = conexao.prepareStatement("SELECT * FROM Admin;");
    
        usuarioDAO = UsuarioDAO.getInstance();
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela Servidor");
        }
        return 0;
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

            // Insere os dados específicos do bolsista
            insert.setString(1, servidor.getCpf());
            insert.setString(2, servidor.getPnome());
            insert.setString(3, servidor.getUnome());
            insert.setString(4, servidor.getEmail());
            insert.setString(5, servidor.getSenha());
            insert.setString(6, servidor.getStatus());
            insert.setString(7, servidor.getFuncao());
            insert.setInt(8, selectNewId());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("servidor");
        }
    }
    public boolean isAdmin(String cpf) throws SelectException {
    	
    	try {
    	isAdmin.setString(1, cpf);
    	
    	ResultSet rs = isAdmin.executeQuery();
    		
    	if(rs.next()) {
    		int count = rs.getInt(1);
    		return count > 0;
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Admin");
    	}
    	return false;
    }
    public void delete(Admin servidor) throws DeleteException {
    	try {
    		delete.setString(1, servidor.getCpf());
    		delete.executeUpdate();
    	}catch(SQLException e) {
    		throw new DeleteException("servidor");
    	}
    }
    public void update(Admin servidor) throws UpdateException{
    	try {
    		update.setString(1, servidor.getPnome());
        	update.setString(2, servidor.getUnome());
        	update.setString(3, servidor.getEmail());
        	update.setString(4, servidor.getSenha());
        	update.setString(5, servidor.getStatus());
        	update.setString(6, servidor.getFuncao());
        	update.setInt(7, servidor.getId_admin());
        	update.setString(8, servidor.getCpf());
        	update.executeUpdate();
    	} catch(SQLException e) {
    		throw new UpdateException("servidor");
    	}
    }
    public Admin select(String cpf) throws SelectException{
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
                int id_admin = rs.getInt(8);
                
                

                return new Admin(cpf, pnome, unome, email, senha, status, funcao, id_admin);
            }

        } catch (SQLException e) {
            throw new SelectException("servidor");
        }

        return null;

    }
    
    public List<Admin> selectAll() throws SelectException {

        List<Admin> lista = new LinkedList<>();

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
                int id_admin = rs.getInt(8);
                
                

                lista.add(new Admin(cpf, pnome, unome, email, senha, status, funcao, id_admin));
            }
        } catch (SQLException e) {
            throw new SelectException("servidor");
        }

        return lista;
    }

}
