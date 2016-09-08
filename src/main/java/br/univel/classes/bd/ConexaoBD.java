package br.univel.classes.bd;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import br.univel.classes.PropertiesSistema;

public class ConexaoBD {
	
	private static ConexaoBD conBD;
	private Connection con = null;
	
	private ConexaoBD(){
		
	}

	public Connection abrirConexao() throws SQLException {
		PropertiesSistema ps = new PropertiesSistema();
		
		
		String url = "jdbc:mysql://" + ps.ler("ip") +"/" + ps.ler("bd");
		String user = ps.ler("user");
		String pass = ps.ler("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conBD.con = (Connection) DriverManager.getConnection(url, user, pass);
	
		return conBD.con;
	}	
	
	public void fecharConexao() throws SQLException {
		con.close();
	}
	
	public static ConexaoBD getInstance(){
		if (conBD == null){
			conBD = new ConexaoBD();
		}
				
		return conBD;
	}
}
