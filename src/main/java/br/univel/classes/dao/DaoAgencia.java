package br.univel.classes.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.bd.ConexaoBD;
import br.univel.interfaces.Dao;

public class DaoAgencia implements Dao<Agencia, Integer>{

	@Override
	public void salvar(Agencia t) {
		if(t.getId() == 0){ // esta inserindo
			try {
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
										.clientPrepareStatement("INSERT INTO AGENCIA VALUES (?,?,?,?)");
				ps.setInt(1, t.getId());
				ps.setString(2, t.getNumero());
				ps.setString(3, t.getNome());
				ps.setString(4, t.getCidade());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{	// esta alterando
			atualizar(t); 
		}
	}

	@Override
	public Agencia buscar(Integer k) {
		Agencia ag = new Agencia();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM AGENCIA WHERE ID = ?");
			ps.setInt(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				ag.setId(result.getInt("id"));
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
					.clientPrepareStatement("UPDATE AGENCIA SET NUMERO = ?, NOME = ?, CIDADE = ? WHERE ID = ?");
			
			ps.setString(1, t.getNumero());
			ps.setString(2, t.getNome());
			ps.setString(3, t.getCidade());
			ps.setInt(4, t.getId());
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void excluir(Integer k) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("DELETE FROM AGENCIA WHERE ID = ?");
			
			ps.setInt(1, k);
			
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
									.clientPrepareStatement("SELECT * FROM AGENCIA");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Agencia ag = new Agencia();
				ag.setId(result.getInt("id"));
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

	@Override
	public int proximoID() {
		int proxId = 0;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT MAX(ID) FROM AGENCIA");
			ResultSet result = ps.executeQuery();
			proxId = 1 + result.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxId;
	}

	
}
