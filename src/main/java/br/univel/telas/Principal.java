package br.univel.telas;

import java.awt.EventQueue;

import com.mysql.jdbc.ConnectionPropertiesTransform;

import br.univel.classes.PropertiesSistema;
import br.univel.classes.bd.ConexaoBD;

public class Principal {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				conectarBD();				
				
				try {
					Login login = new Login();
					login.setSize(427, 250);
					login.setLocationRelativeTo(null);
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void conectarBD(){
		PropertiesSistema properties = new PropertiesSistema();
		properties.escrever();
		
		if(ConexaoBD.getInstance() != null){
			System.out.println("Conectou");
		};		
	}

}
