package br.univel.classes.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.exolab.castor.types.Time;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Conta;
import br.univel.classes.bd.ConexaoBD;
import br.univel.enuns.TipoConta;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.DaoMov;

public class DaoMovimentacao implements DaoMov{
	
	@Override
	public void sacar(BigDecimal valor, String numConta, String agencia, String senha){
		
		DaoConta daoC = new DaoConta();
		Conta conta = new Conta(); 
		
		conta = daoC.buscar(numConta);
		if(conta.getTipoConta() == TipoConta.CORRENTE){
			if(daoC.existeConta(numConta)){			
				if(temSaldo(numConta, agencia)){
					BigDecimal saldoAtual = new BigDecimal(0.0);
					saldoAtual = saldoAtual(numConta, agencia);
					if(saldoAtual.compareTo(valor) >= 0){
						try {
							PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
							ps.setString(1, numConta);
							ps.setString(2, "S");
							Date d = new Date();
							ps.setDate(3, new java.sql.Date(d.getTime()));
							ps.setTime(4, new java.sql.Time(d.getTime()));
							valor = valor.subtract(valor.add(valor));  // kkkk adaptação técnica
							ps.setBigDecimal(5, valor);
							ps.setString(6, "SAQUE");
							ps.executeUpdate();
							Funcoes.msgConfirma("Saque efetuado com sucesso !");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						Funcoes.msgAviso("Saldo insuficiente !");
					}
				}else{
					Funcoes.msgAviso("Saldo insuficiente !");
				}
			}else{
				Funcoes.msgErro("Conta inexistente !");
			}
		}else if (conta.getTipoConta() == TipoConta.POUPANÇA) {
			Funcoes.msgAviso("Não é possivel efetuar saque em conta poupança.");
		}else if (conta.getTipoConta() == TipoConta.POUPANÇA) {
			Funcoes.msgAviso("Não é possivel efetuar saque em conta eletronica.");
		}
	}
	
	@Override
	public void depositar(BigDecimal valor, String conta, String agencia){
		try {
			DaoConta daoC = new DaoConta();
			if(daoC.existeConta(conta)){
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
						.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
				ps.setString(1, conta);
				ps.setString(2, "D");
				Date d = new Date();
				ps.setDate(3, new java.sql.Date(d.getTime()));
				ps.setTime(4, new java.sql.Time(d.getTime()));
				ps.setBigDecimal(5, valor);
				ps.setString(6, "DEPOSITO");
				ps.executeUpdate();
				Funcoes.msgConfirma("Depósito efetuado com sucesso !");
			}else{
				Funcoes.msgErro("Conta inexistente !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public BigDecimal saldoAtual(String k, String agencia){
		BigDecimal saldo = new BigDecimal(0.0);
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT SUM(VALOR) AS SALDO FROM CONTAS_MOVIMENTO WHERE CONTA_NUMERO = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				saldo = result.getBigDecimal("saldo");
				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saldo;
	}
	
	@Override
	public boolean temSaldo(String k, String agencia){
		boolean resultado = false;
		double saldo =  0;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT SUM(VALOR) AS SALDO FROM CONTAS_MOVIMENTO WHERE CONTA_NUMERO = ?");
			ps.setString(1, k);
			ResultSet result =  ps.executeQuery();
			if(result.next()){
				saldo = result.getDouble("saldo");
				if(saldo > 0){
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

	@Override
	public void transferir(BigDecimal valor, String contaDest, String agenciaDest, String contaOri, String agenciaOri, String senha, String tipo) {
		//sacar contaOrigem
		DaoConta daoContaOri = new DaoConta();
		Conta conta = new Conta(); 
		
		conta = daoContaOri.buscar(contaOri);
		if((conta.getTipoConta() == TipoConta.CORRENTE) || (conta.getTipoConta() == TipoConta.ELETRONICA)){
			if(daoContaOri.existeConta(contaOri)){			
				if(temSaldo(contaOri, contaOri)){
					BigDecimal saldoAtual = new BigDecimal(0.0);
					saldoAtual = saldoAtual(contaOri, contaOri);
					if(saldoAtual.compareTo(valor) >= 0){
						try {
							PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
							ps.setString(1, contaOri);
							ps.setString(2, "S");
							Date d = new Date();
							ps.setDate(3, new java.sql.Date(d.getTime()));
							ps.setTime(4, new java.sql.Time(d.getTime()));
							valor = valor.subtract(valor.add(valor));  // kkkk adaptação técnica
							ps.setBigDecimal(5, valor);
							ps.setString(6, "SAQUE");
							ps.executeUpdate();
							Funcoes.msgConfirma("Saque efetuado com sucesso !");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						Funcoes.msgAviso("Saldo insuficiente !");
					}
				}else{
					Funcoes.msgAviso("Saldo insuficiente !");
				}
			}else{
				Funcoes.msgErro("Conta inexistente !");
			}
		}else if (conta.getTipoConta() == TipoConta.POUPANÇA) {
			Funcoes.msgAviso("Não é possivel efetuar transferencia em conta poupança.");
		}
		
		//depositar contaDestino
		try {
			DaoConta daoContaDest = new DaoConta();
			if(daoContaDest.existeConta(contaDest)){
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
						.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
				ps.setString(1, contaDest);
				ps.setString(2, "D");
				Date d = new Date();
				ps.setDate(3, new java.sql.Date(d.getTime()));
				ps.setTime(4, new java.sql.Time(d.getTime()));
				ps.setBigDecimal(5, valor);
				ps.setString(6, "DEPOSITO");
				ps.executeUpdate();
				Funcoes.msgConfirma("Depósito efetuado com sucesso !");
			}else{
				Funcoes.msgErro("Conta inexistente !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
