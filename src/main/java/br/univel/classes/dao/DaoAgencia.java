package br.univel.classes.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.bd.ConexaoBD;
import br.univel.interfaces.Dao;

public class DaoAgencia implements Dao<Agencia, String>{

	@Override
	public void salvar(Agencia t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO AGENCIAS VALUES (?,?,?)");
			ps.setString(1, t.getNumero());
			ps.setString(2, t.getNome());
			ps.setString(3, t.getCidade());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Agencia buscar(String k) {
		Agencia ag = new Agencia();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM AGENCIAS WHERE ID = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				ag.setNumero(result.getString("numero"));
				ag.setNome(result.getString("nome"));
				ag.setCidade(result.getString("cidade"));
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ag;
	}

	@Override
	public void atualizar(Agencia t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("UPDATE AGENCIAS SET NOME = ?, CIDADE = ? WHERE NUMERO = ?");
			
			ps.setString(1, t.getNome());
			ps.setString(2, t.getCidade());
			ps.setString(3, t.getNumero());			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void excluir(String k) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("DELETE FROM AGENCIAS WHERE numero = ?");
			
			ps.setString(1, k);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public List<Agencia> listarTodos() {
		ArrayList<Agencia> agencias = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM AGENCIAS");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Agencia ag = new Agencia();
				ag.setNumero(result.getString("numero"));
				ag.setNome(result.getString("nome"));
				ag.setCidade(result.getString("cidade"));
				agencias.add(ag);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencias;
	}
	
}
