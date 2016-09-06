package br.univel.classes.dao;

import java.sql.ResultSet;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
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
									.clientPrepareStatement("INSERT INTO USUARIO VALUES (?,?,?)");
			ps.setString(2, t.getUser());
			ps.setString(3, t.getSenha());
			ResultSet result =  ps.executeQuery();
			if(daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("pessoa")))) != null){
				pessoa = new Pessoa();
				pessoa = daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia"))));
				t.setPessoa(pessoa);
			}
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
				if(daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia")))) != null){
					pessoa = new Pessoa();
					pessoa = daoPessoa.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia"))));
					us.setPessoa(pessoa);
				}				
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int proximoID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
