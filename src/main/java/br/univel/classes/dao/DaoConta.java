package br.univel.classes.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.Dao;

public class DaoConta implements Dao<Conta, String>{

	@Override
	public void salvar(Conta t) {
		if(t.getNumero().isEmpty()){ // esta inserindo
			try {
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
										.clientPrepareStatement("INSERT INTO CONTAS VALUES (?,?,?,?,?,?,?,?)");
				ps.setString(1, t.getNumero());
				ps.setString(2, t.getNome());
				ps.setInt(3, t.getIdade());
				ps.setString(4, t.getCpf());
				ps.setString(5, t.getAgencia().getNumero());
				ps.setDate(6, (Date) t.getDtAbertura());
				ps.setString(7, t.getSenhaAcesso());
				ps.setString(8, t.getSenhaOperacoes());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{ // esta alterando
			atualizar(t); 
		}
	}

	@Override
	public Conta buscar(String k) {
		Conta conta = new Conta();
		DaoAgencia DaoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTAS WHERE NUMERO = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				conta.setNumero(result.getString("numero"));
				conta.setNome(result.getString("nome"));
				conta.setIdade(result.getInt("idade"));
				conta.setCpf(result.getString("cpf"));				

				ag = new Agencia();
				ag = DaoAG.buscar(result.getString("agencia"));
				conta.setAgencia(ag);

				conta.setDtAbertura(result.getDate("dt_abertura"));
				
				switch (result.getInt("tipo")) {
				case 0:
					conta.setTipoConta(TipoConta.CORRENTE);
					break;
				case 1:
					conta.setTipoConta(TipoConta.POUPANÇA);
					break;
				case 2:
					conta.setTipoConta(TipoConta.ELETRONICA);
					break;

				default:
					break;
				}
				
				conta.setSenhaAcesso(result.getString("senha_acesso"));	
				conta.setSenhaOperacoes(result.getString("senha_op"));	
				
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conta;
	}

	@Override
	public void atualizar(Conta t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("UPDATE CONTAS SET NOME_TITULAR = ?, IDADE = ?, CPF = ?, AGENCIA = ?, DT_ABERTURA = ?, SENHA_ACESSO = ?, SENHA_OP = ? WHERE NUMERO = ?");
			
			
			ps.setString(1, t.getNome());
			ps.setInt(2, t.getIdade());
			ps.setString(3, t.getCpf());
			ps.setString(4, t.getAgencia().getNumero());
			ps.setDate(5, (Date) t.getDtAbertura());
			ps.setString(6, t.getSenhaAcesso());
			ps.setString(7, t.getSenhaOperacoes());
			ps.setString(8, t.getNumero());
			ps.executeUpdate();			
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(String k) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("DELETE FROM CONTAS WHERE NUMERO = ?");
			
			ps.setString(1, k);
			
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public List<Conta> listarTodos() {
		ArrayList<Conta> contas = new ArrayList<>();
		DaoAgencia DaoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTAS");
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Conta conta = new Conta();
				conta.setNumero(result.getString("numero"));
				conta.setNome(result.getString("nome"));
				conta.setIdade(result.getInt("idade"));
				conta.setCpf(result.getString("cpf"));				

				ag = new Agencia();
				ag = DaoAG.buscar(result.getString("agencia"));
				conta.setAgencia(ag);

				conta.setDtAbertura(result.getDate("dt_abertura"));
				
				switch (result.getInt("tipo")) {
				case 0:
					conta.setTipoConta(TipoConta.CORRENTE);
					break;
				case 1:
					conta.setTipoConta(TipoConta.POUPANÇA);
					break;
				case 2:
					conta.setTipoConta(TipoConta.ELETRONICA);
					break;

				default:
					break;
				}
				
				conta.setSenhaAcesso(result.getString("senha_acesso"));	
				conta.setSenhaOperacoes(result.getString("senha_op"));	

				contas.add(conta);
			}
			
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}
}
