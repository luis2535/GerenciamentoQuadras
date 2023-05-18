package negocios;

import java.sql.SQLException;
import java.util.List;

import dados.*;
import persistencia.*;
import excecoes.*;


public class Sistema {
	private static Sistema instance = null;
	private static UsuarioDAO usuarioDAO = null;
	
	public static Sistema getSistema() throws ClassNotFoundException, SQLException{
		if(instance == null) {
			return new Sistema();
		}
		
		return instance;
	}
}
