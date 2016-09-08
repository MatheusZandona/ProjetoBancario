package br.univel.classes.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Pessoa;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.Dao;

public class DaoPessoa implements Dao<Pessoa, Integer>{

	@Override
	public void salvar(Pessoa t) {
		if(t.getId() == 0){ // esta inserindo
			try {
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
										.clientPrepareStatement("INSERT INTO PESSOA VALUES (?,?,?,?,?)");
				ps.setString(2, t.getNome());
				ps.setInt(3, t.getIdade());
				ps.setString(4, t.getSenha());
				ps.setString(5, t.getCpf());
				ps.setInt(3, t.getTipoLogin().ordinal());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{ // esta alterando
			atualizar(t); 
		}
	}

	@Override
	public Pessoa buscar(Integer k) {
		Pessoa pessoa = new Pessoa();
		DaoAgencia DaoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PESSOA WHERE ID = ?");
			ps.setInt(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setIdade(result.getInt("idade"));
				pessoa.setSenha(result.getString("senha"));
				pessoa.setCpf(result.getString("cpf"));
				
				switch(result.getInt("tipo")){
					case 0:
						pessoa.setTipoLogin(TipoLogin.BANCARIO);
						break;
						
					case 1:
						pessoa.setTipoLogin(TipoLogin.CLIENTE);
						break;
				}
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoa;
	}

	@Override
	public void atualizar(Pessoa t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("UPDATE PESSOA SET NOME = ?, IDADE = ?, SENHA = ?, CPF = ?, TIPO = ? WHERE ID = ?");
			
			ps.setString(1, t.getNome());
			ps.setInt(2, t.getIdade());
			ps.setString(3, t.getSenha());
			ps.setString(4, t.getCpf());
			ps.setInt(5, t.getTipoLogin().ordinal());
			
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
					.clientPrepareStatement("DELETE FROM PESSOA WHERE ID = ?");
			
			ps.setInt(1, k);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public List<Pessoa> listarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PESSOA");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setIdade(result.getInt("idade"));
				pessoa.setSenha(result.getString("senha"));
				pessoa.setCpf(result.getString("cpf"));
				switch(result.getInt("tipo")){
					case 0:
						pessoa.setTipoLogin(TipoLogin.BANCARIO);
						break;
						
					case 1:
						pessoa.setTipoLogin(TipoLogin.CLIENTE);
						break;
				}
				
				pessoas.add(pessoa);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoas;
	}

	@Override
	public int proximoID() {
		int proxId = 0;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT MAX(ID) FROM PESSOA");
			ResultSet result = ps.executeQuery();
			proxId = 1 + result.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxId;
	}

}
