package br.univel.classes.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Pessoa;
import br.univel.classes.Usuario;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.Dao;

public class DaoUsuario implements Dao<Usuario, Integer>{

	@Override
	public void salvar(Usuario t) {
		DaoPessoa daoPessoa = new DaoPessoa();
		Pessoa pessoa;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO USUARIO VALUES (?,?,?,?)");
			ps.setString(1, t.getUser());
			ps.setString(2, t.getSenha());
			ResultSet result =  ps.executeQuery();
			if(daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa")))) != null){
				pessoa = new Pessoa();
				pessoa = daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa"))));
				t.setPessoa(pessoa);
			}
			ps.setInt(3, t.getPessoa().getId());
			ps.setString(4, t.getSenhaOperacao());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario buscar(Integer k) {
		Usuario us = new Usuario();
		DaoPessoa daoPessoa = new DaoPessoa();
		Pessoa pessoa;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM USUARIO WHERE ID = ?");
			ps.setInt(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				us.setId(result.getInt("id"));
				us.setUser(result.getString("nome"));
				us.setSenha(result.getString("senha"));
				if(daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa")))) != null){
					pessoa = new Pessoa();
					pessoa = daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa"))));
					us.setPessoa(pessoa);
				}
				us.setSenhaOperacao(result.getString("senhaop"));
			}
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public void atualizar(Usuario t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("UPDATE USUARIO SET NOME = ?, SENHA = ?, PESSOA = ?, SENHAOP = ? WHERE ID = ?");
			
			ps.setString(1, t.getUser());
			ps.setString(2, t.getSenha());
			ps.setInt(3, t.getPessoa().getId());
			ps.setString(4, t.getSenhaOperacao());
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer k) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("DELETE FROM USUARIO WHERE ID = ?");
			
			ps.setInt(1, k);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public List<Usuario> listarTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		DaoPessoa daoPessoa = new DaoPessoa();
		Pessoa pessoa;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM USUARIO");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Usuario us = new Usuario();
				us.setId(result.getInt("id"));
				us.setUser(result.getString("nome"));
				if(daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa")))) != null){
					pessoa = new Pessoa();
					pessoa = daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa"))));
					us.setPessoa(pessoa);
				}
				us.setSenha(result.getString("senha"));
				us.setSenha(result.getString("senhaop"));
				usuarios.add(us);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public int proximoID() {
		int proxId = 0;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT MAX(ID) FROM USUARIO");
			ResultSet result = ps.executeQuery();
			proxId = 1 + result.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxId;
	}

}
