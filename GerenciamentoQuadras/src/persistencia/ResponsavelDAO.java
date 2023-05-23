package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Responsavel;
import dados.Usuario;
import excecoes.DeleteException;
import excecoes.InsertException;
import excecoes.SelectException;
import excecoes.UpdateException;

public class ResponsavelDAO {
	private static ResponsavelDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    
    private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;

    public static ResponsavelDAO getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new ResponsavelDAO();
        }
        return instance;
    }
    
    private ResponsavelDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        
        selectNewId = conexao.prepareStatement("SELECT nextval('responsavel_id_seq');");
        insert = conexao.prepareStatement("INSERT INTO Responsavel (cpf, pnome, unome, email, senha, id_responsavel) VALUES (?,?,?,?,?,?);");
        delete = conexao.prepareStatement("DELETE FROM Responsavel WHERE cpf = ?;");
        update = conexao.prepareStatement(
                "UPDATE Responsavel SET pnome = ?, unome = ?, email = ?, senha = ?, id_responsavel= ? WHERE cpf = ?;");
        select = conexao.prepareStatement("SELECT * FROM Responsavel WHERE cpf = ?;");
        selectAll = conexao.prepareStatement("SELECT * FROM Responsavel;");
    
        usuarioDAO = UsuarioDAO.getInstance();
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela Responsavel");
        }
        return 0;
    }



    public void insert(Responsavel responsavel) throws SelectException, InsertException, UpdateException {
        try {
            // Verifica se o usuário já existe
            Usuario usuarioExistente = usuarioDAO.select(responsavel.getCpf());
            if (usuarioExistente == null) {
                // Se o usuário não existir, insere o novo usuário
                usuarioDAO.insert(responsavel);
            } else {
                // Se o usuário já existir, atualiza os dados do usuário existente
                usuarioDAO.update(responsavel);
            }

            // Insere os dados específicos do responsavel
            insert.setString(1, responsavel.getCpf());
            insert.setString(2, responsavel.getPnome());
            insert.setString(3, responsavel.getUnome());
            insert.setString(4, responsavel.getEmail());
            insert.setString(5, responsavel.getSenha());
            insert.setInt(6, selectNewId());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("Responsavel");
        }
    }
    public void delete(Responsavel responsavel) throws DeleteException {
    	try {
    		delete.setString(1, responsavel.getCpf());
    		delete.executeUpdate();
    	}catch(SQLException e) {
    		throw new DeleteException("Responsavel");
    	}
    }
    public void update(Responsavel responsavel) throws UpdateException{
    	try {
    		update.setString(1, responsavel.getPnome());
        	update.setString(2, responsavel.getUnome());
        	update.setString(3, responsavel.getEmail());
        	update.setString(4, responsavel.getSenha());
        	update.setInt(5, responsavel.getId_responsavel());
        	update.setString(6, responsavel.getCpf());
        	update.executeUpdate();
    	} catch(SQLException e) {
    		throw new UpdateException("responsavel");
    	}
    }
    public Responsavel select(String cpf) throws SelectException{
    	try {
            select.setString(1, cpf);
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                int id_responsavel = rs.getInt(6);
                
                

                return new Responsavel(cpf, pnome, unome, email, senha, id_responsavel);
            }

        } catch (SQLException e) {
            throw new SelectException("Responsavel");
        }

        return null;

    }
    
    public List<Responsavel> selectAll() throws SelectException {

        List<Responsavel> lista = new LinkedList<>();

        try {
            ResultSet rs = selectAll.executeQuery();

            while(rs.next()){
                String cpf = rs.getString(1);
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                int id_responsavel = rs.getInt(6);
                
                

                lista.add(new Responsavel(cpf, pnome, unome, email, senha, id_responsavel));
            }
        } catch (SQLException e) {
            throw new SelectException("Responsavel");
        }

        return lista;
    }

}
