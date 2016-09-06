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

public class DaoConta implements Dao<Conta, Integer>{

	@Override
	public void salvar(Conta t) {
		if(t.getId() == 0){ // esta inserindo
			try {
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
										.clientPrepareStatement("INSERT INTO CONTA VALUES (?,?,?,?,?)");
				ps.setString(2, t.getNumero());
				ps.setInt(3, t.getTipoConta().ordinal());
				ps.setDate(4, (Date) t.getDtAbertura());
				ps.setBigDecimal(5, t.getSaldo());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{ // esta alterando
			atualizar(t); 
		}
	}

	@Override
	public Conta buscar(Integer k) {
		Conta conta = new Conta();
		DaoAgencia DaoAG = new DaoAgencia();
		Agencia ag;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTA WHERE ID = ?");
			ps.setInt(1, k);
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				conta.setId(result.getInt("id"));
				conta.setNumero(result.getString("numero"));
				if(DaoAG.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia")))) != null){
					ag = new Agencia();
					ag = DaoAG.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia"))));
					conta.setAgencia(ag);
				}
				conta.setDtAbertura(result.getDate("dt_abertura"));
				if(result.getInt("tipo") == 0){
					conta.setTipoConta(TipoConta.CORRENTE);
				}else if(result.getInt("tipo") == 1){
					conta.setTipoConta(TipoConta.POUPANÇA);
				}else{
					conta.setTipoConta(TipoConta.ELETRONICA);
				}
				
				conta.setSaldo(result.getBigDecimal("saldo"));
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
					.clientPrepareStatement("UPDATE CONTA SET NUMERO = ?, AGENCIA = ?, DT_ABERTURA = ?, TIPO = ?, SALDO = ? WHERE ID = ?");
			
			ps.setString(1, t.getNumero());
			ps.setString(2, t.getAgencia().getNumero());
			ps.setDate(3, (Date) t.getDtAbertura());
			ps.setInt(4, t.getTipoConta().ordinal());
			ps.setBigDecimal(5, t.getSaldo());
			
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
					.clientPrepareStatement("DELETE FROM CONTA WHERE ID = ?");
			
			ps.setInt(1, k);
			
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
									.clientPrepareStatement("SELECT * FROM CONTA");
			ResultSet result =  ps.executeQuery();
			while(result.next()){
				Conta conta = new Conta();
				conta.setId(result.getInt("id"));
				conta.setNumero(result.getString("numero"));
				if(DaoAG.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia")))) != null){
					ag = new Agencia();
					ag = DaoAG.buscar(Integer.parseInt(Funcoes.removerCaracteresEspeciais(result.getString("agencia"))));
					conta.setAgencia(ag);
				}
				conta.setDtAbertura(result.getDate("dt_abertura"));
				if(result.getInt("tipo") == 0){
					conta.setTipoConta(TipoConta.CORRENTE);
				}else if(result.getInt("tipo") == 1){
					conta.setTipoConta(TipoConta.POUPANÇA);
				}else{
					conta.setTipoConta(TipoConta.ELETRONICA);
				}
				
				conta.setSaldo(result.getBigDecimal("saldo"));
				contas.add(conta);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contas;
	}

	@Override
	public int proximoID() {
		int proxId = 0;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT MAX(ID) FROM CONTA");
			ResultSet result = ps.executeQuery();
			proxId = 1 + result.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxId;
	}
}
