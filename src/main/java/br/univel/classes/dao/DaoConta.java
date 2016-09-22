package br.univel.classes.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Hash;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.Dao;
import br.univel.telas.PrincipalCliente;
import br.univel.telas.TelaPadrao;

public class DaoConta implements Dao<Conta, String>{

	@Override
	public void salvar(Conta t) {
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO CONTAS (NUMERO, TIPO, NOME_TITULAR, IDADE, CPF, AGENCIA, DT_ABERTURA, SENHA_ACESSO, SENHA_OP, STATUS) VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getNumero());
			ps.setInt(2, t.getTipoConta().ordinal());
			ps.setString(3, t.getNome());
			ps.setInt(4, t.getIdade());
			ps.setString(5, t.getCpf());
			ps.setString(6, t.getAgencia().getNumero());
			ps.setDate(7, new java.sql.Date(t.getDtAbertura().getTime()));
			ps.setString(8,new Hash().hashSHA256(t.getCpf().concat(t.getSenhaAcesso())));
			ps.setString(9, t.getSenhaOperacoes());
			ps.setInt(10, 0);
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
				conta.setStatus(result.getInt("status"));
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
			
			
			if(!t.getSenhaAcesso().equals("")){
				ps.setString(6, new Hash().hashMD5(t.getSenhaAcesso()));
			}
			
			if(!t.getSenhaOperacoes().equals("")){
				ps.setString(7, t.getSenhaOperacoes());
			}
			
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
					.clientPrepareStatement("UPDATE CONTAS SET STATUS = ? WHERE NUMERO = ?");
			
			ps.setInt(1, 1);
			ps.setString(2, k);
			
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
									.clientPrepareStatement("SELECT "
															.concat("	C.*, ") 
															.concat("   COALESCE(SUM(VALOR), 0) AS SALDO ")
															.concat("FROM CONTAS C ")
															.concat("LEFT JOIN CONTAS_MOVIMENTO CM ON CM.CONTA_NUMERO = C.NUMERO ")
															.concat("WHERE STATUS = 0 ")
															.concat("GROUP BY 1,2,3,4,5 ")
															.concat("ORDER BY DT_ABERTURA, C.NUMERO "));
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Conta conta = new Conta();
				conta.setNumero(result.getString("numero"));
				conta.setNome(result.getString("nome_titular"));
				conta.setIdade(result.getInt("idade"));
				conta.setCpf(result.getString("cpf"));				
				conta.setSaldo(result.getBigDecimal("SALDO"));
				
				
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
				conta.setStatus(result.getInt("status"));
				
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
	
	public boolean validarLogin(String username, String senha){
		boolean resultado = false;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT * FROM CONTAS WHERE CPF = ? AND SENHA_ACESSO = ? "
							+ " AND STATUS = 0");
			ps.setString(1, username);
			ps.setString(2, new Hash().hashSHA256(username.concat(senha)));
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean existeConta(String conta){
		boolean resultado = false;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT * FROM CONTAS WHERE NUMERO = ? AND STATUS = 0");
			ps.setString(1, conta);
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				resultado = true;
			}
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean validarSenhaOP(String senha){
		boolean resultado = false;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT * FROM CONTAS WHERE SENHA_OP = ?");
			ps.setString(1, senha);
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				if(senha.equals(result.getString("SENHA_OP"))){
					resultado = true;
				}
			}
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Conta buscarLogin(String user, String password){
		Conta conta = new Conta();
		DaoAgencia daoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT * FROM CONTAS WHERE CPF = ? AND SENHA_ACESSO = ? "
							+ " AND STATUS = 0");
			ps.setString(1, user);
			ps.setString(2, new Hash().hashSHA256(user.concat(password)));
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
				conta.setStatus(result.getInt("status"));
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conta;
	}
}
