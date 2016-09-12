package br.univel.classes.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.interfaces.Dao;

public class DaoConta implements Dao<Conta, String>{

	@Override
	public void salvar(Conta t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO CONTAS VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getNumero());
			ps.setInt(2, t.getTipoConta().ordinal());
			ps.setString(3, t.getNome());
			ps.setInt(4, t.getIdade());
			ps.setString(5, t.getCpf());
			ps.setString(6, t.getAgencia().getNumero());
			ps.setDate(7, new java.sql.Date(t.getDtAbertura().getTime()));
			ps.setString(8, t.getSenhaAcesso());
			ps.setString(9, t.getSenhaOperacoes());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Conta buscar(String k) {
		Conta conta = new Conta();
		DaoAgencia daoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTAS WHERE NUMERO = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				conta.setNumero(result.getString("numero"));
				conta.setNome(result.getString("nome_titular"));
				conta.setIdade(result.getInt("idade"));
				conta.setCpf(result.getString("cpf"));				

				ag = new Agencia();
				ag = daoAG.buscar(result.getString("agencia"));
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
			ps.setDate(5, new java.sql.Date(t.getDtAbertura().getTime()));
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
				conta.setNome(result.getString("nome_titular"));
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

	public String gerarProximoNumConta() {
		PreparedStatement ps;
		Integer numero = 1, digito = 0;
		
		try {
			ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT MAX(NUMERO) AS NUMERO FROM CONTAS");
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				if(result.getString("NUMERO") != null){
					numero = Integer.parseInt(result.getString("NUMERO").substring(0, 5));
					digito = Integer.parseInt(result.getString("NUMERO").substring(6, 8));
				}
			}
			
			ps.close();
			result.close();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//define próxima conta
		if(digito == 99){
			numero++;
			digito = 1;
		}else{
			digito++;
		}		
		
		return new DecimalFormat("00000").format(numero).concat("-").concat(new DecimalFormat("00").format(digito));
	}
}
