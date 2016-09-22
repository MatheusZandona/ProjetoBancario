package br.univel.classes.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Hash;
import br.univel.classes.Profissional;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoLogin;
import br.univel.interfaces.Dao;
import br.univel.telas.PrincipalBancario;
import br.univel.telas.TelaPadrao;

public class DaoProfissional implements Dao<Profissional, String>{

	@Override
	public void salvar(Profissional t) {
		try {		
			
			PreparedStatement ps1 = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO PROFISSIONAIS(USERNAME, NOME, IDADE, SENHA_ACESSO, SENHA_OP) VALUES (?,?,?,?,?)");
			ps1.setString(1, t.getUsername());
			ps1.setString(2, t.getNome());
			ps1.setInt(3, t.getIdade());
			ps1.setString(4,  new Hash().hashMD5(t.getSenhaAcesso()));
			ps1.setString(5, t.getSenhaOperacoes());
			ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Profissional buscar(String k) {
		Profissional profissional = new Profissional();
		
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PROFISSIONAIS WHERE USERNAME = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				profissional.setUsername(result.getString("username"));
				profissional.setNome(result.getString("nome"));
				profissional.setIdade(result.getInt("idade"));
				profissional.setSenhaAcesso(result.getString("senha_acesso"));
				profissional.setSenhaOperacoes(result.getString("senha_op"));
				profissional.setId(result.getInt("id"));
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profissional;
	}

	@Override
	public void atualizar(Profissional t) {
		try {
			
			PreparedStatement ps1 = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("UPDATE PROFISSIONAIS SET NOME = ?, IDADE = ?, SENHA_ACESSO = ?, SENHA_OP = ?, USERNAME = ? WHERE ID = ?");
			
			ps1.setString(1, t.getNome());
			ps1.setInt(2, t.getIdade());		
			ps1.setString(3, new Hash().hashSHA256(t.getUsername().concat(t.getSenhaAcesso())));
			ps1.setString(4, t.getSenhaOperacoes());
			ps1.setString(5, t.getUsername());
			ps1.setInt(6, t.getId());
			
			ps1.executeUpdate();
			ps1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(String k) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("DELETE FROM PROFISSIONAIS WHERE ID = ?");
			
			ps.setString(1, k);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public List<Profissional> listarTodos() {
		ArrayList<Profissional> profissionais = new ArrayList<>();
		
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PROFISSIONAIS");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Profissional profissional = new Profissional();
				profissional.setUsername(result.getString("username"));
				profissional.setNome(result.getString("nome"));
				profissional.setIdade(result.getInt("idade"));
				profissional.setSenhaAcesso(result.getString("senha_acesso"));
				profissional.setSenhaOperacoes(result.getString("senha_op"));
				
				profissionais.add(profissional);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profissionais; 
	}
	
	public boolean validarLogin(String username, String senha){
		boolean resultado = false;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT * FROM PROFISSIONAIS WHERE USERNAME = ? AND SENHA_ACESSO = ?");
			ps.setString(1, username);
//			ps.setString(2, senha);
			ps.setString(2, new Hash().hashMD5(senha));
			
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				TelaPadrao.profissional = new Profissional();
				TelaPadrao.profissional.setUsername(result.getString("username"));
				TelaPadrao.profissional.setNome(result.getString("nome"));
				TelaPadrao.profissional.setIdade(result.getInt("idade"));
				TelaPadrao.profissional.setSenhaAcesso(result.getString("senha_acesso"));
				TelaPadrao.profissional.setSenhaOperacoes(result.getString("senha_op"));
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

}
