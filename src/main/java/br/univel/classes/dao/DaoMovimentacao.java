package br.univel.classes.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Movimentacao;
import br.univel.classes.bd.ConexaoBD;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.DaoMov;
import br.univel.telas.TelaPadrao;

public class DaoMovimentacao implements DaoMov{
	
	private static DaoMovimentacao dao;
	
	public static DaoMovimentacao getInstance(){
		if (dao == null){
			dao = new DaoMovimentacao();
		}				
		return dao;
	}			
	
	@Override
	public boolean sacar(BigDecimal valor, Conta conta, Agencia agencia){
		
		boolean resultado = false;
		
		if(DaoConta.getInstance().existeConta(conta.getNumero())){			
			if(temSaldo(conta.getNumero(), agencia.getNumero())){
				
				BigDecimal saldoAtual = BigDecimal.ZERO;
				saldoAtual = saldoAtual(conta.getNumero(), agencia.getNumero());
				
				if(saldoAtual.compareTo(valor) >= 0){
					try {
						PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
								.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
						ps.setString(1, conta.getNumero());
						ps.setString(2, "S");
						Date d = new Date();
						ps.setDate(3, new java.sql.Date(d.getTime()));
						ps.setTime(4, new java.sql.Time(d.getTime()));
						valor = valor.subtract(valor.add(valor));  
						ps.setBigDecimal(5, valor);
						ps.setString(6, "Saque");
						ps.executeUpdate();
						
						resultado = true;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					Funcoes.msgAviso("Saldo insuficiente.");
				}
			}else{
				Funcoes.msgAviso("Saldo insuficiente.");
			}
		}else{
			Funcoes.msgErro("Conta inexistente.");
		}
		
		return resultado;
	}
	
	@Override
	public boolean depositar(BigDecimal valor, Conta conta, Agencia agencia){
		boolean resultado = false;
		
		try {
			if(DaoConta.getInstance().existeConta(conta.getNumero())){
				PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
						.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
				ps.setString(1, conta.getNumero());
				ps.setString(2, "D");
				Date d = new Date();
				ps.setDate(3, new java.sql.Date(d.getTime()));
				ps.setTime(4, new java.sql.Time(d.getTime()));
				ps.setBigDecimal(5, valor);
				ps.setString(6, "Depósito");
				ps.executeUpdate();
				resultado = true;
			}else{
				Funcoes.msgErro("Conta inexistente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	@Override
	public BigDecimal saldoAtual(String k, String agencia){
		BigDecimal saldo = BigDecimal.ZERO;
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("SELECT SUM(CONTAS_MOVIMENTO.VALOR) AS SALDO FROM CONTAS_MOVIMENTO "
							+ "INNER JOIN CONTAS ON CONTAS.NUMERO = CONTAS_MOVIMENTO.CONTA_NUMERO "
							+ "WHERE CONTAS_MOVIMENTO.CONTA_NUMERO = ? AND CONTAS.AGENCIA = ?");
			ps.setString(1, k);
			ps.setString(2, agencia);
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
					.clientPrepareStatement("SELECT SUM(CONTAS_MOVIMENTO.VALOR) AS SALDO FROM CONTAS_MOVIMENTO "
							+ "INNER JOIN CONTAS ON CONTAS.NUMERO = CONTAS_MOVIMENTO.CONTA_NUMERO "
							+ "WHERE CONTAS_MOVIMENTO.CONTA_NUMERO = ? AND CONTAS.AGENCIA = ?");
			ps.setString(1, k);
			ps.setString(2, agencia);
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
	public boolean transferir(BigDecimal valor, String contaDest, String agenciaDest, String contaOri, String agenciaOri) {
		//sacar contaOrigem
		DaoConta daoConta = DaoConta.getInstance();
		boolean resultado = false;
		
		if(daoConta.existeConta(contaOri) && daoConta.existeConta(contaDest)){			
			if(temSaldo(contaOri, agenciaOri)){
				BigDecimal saldoAtual = new BigDecimal(0.0);
				saldoAtual = saldoAtual(contaOri, agenciaOri);
				if(saldoAtual.compareTo(valor) >= 0){
					try {
						PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
								.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
						ps.setString(1, contaOri);
						ps.setString(2, "TS");
						Date d = new Date();
						ps.setDate(3, new java.sql.Date(d.getTime()));
						ps.setTime(4, new java.sql.Time(d.getTime()));
						ps.setBigDecimal(5, valor.subtract(valor.add(valor)));
						ps.setString(6, "Transferencia para conta ".concat(contaDest).concat(" agencia ").concat(agenciaDest));
						ps.executeUpdate();
						
						//depositar contaDestino
						ps.setString(1, contaDest);
						ps.setString(2, "TE");
						ps.setDate(3, new java.sql.Date(d.getTime()));
						ps.setTime(4, new java.sql.Time(d.getTime()));
						ps.setBigDecimal(5, valor);
						ps.setString(6, "Transferencia recebida da conta ".concat(contaOri).concat(" agencia ").concat(agenciaOri));
						ps.executeUpdate();
						
						resultado = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					Funcoes.msgAviso("Saldo insuficiente.");
				}
			}else{
				Funcoes.msgAviso("Saldo insuficiente.");
			}
		}else{
			Funcoes.msgErro("Conta inexistente.");
		}		
		
		return resultado;
	}

	@Override 
	public List<Movimentacao> listarOperacoesConta(Date dataInicial, Date dataFinal) {
		ArrayList<Movimentacao> listaOperacoes = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTAS_MOVIMENTO WHERE CONTA_NUMERO = ? AND DATA BETWEEN ? AND ?");
			ps.setString(1, TelaPadrao.conta.getNumero());
			ps.setDate(2, new java.sql.Date(dataInicial.getTime()));
			ps.setDate(3, new java.sql.Date(dataFinal.getTime()));
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Movimentacao mov = new Movimentacao();
				mov.setConta(new DaoConta().buscar(result.getString("conta_numero")));
				mov.setData(result.getDate("data"));
				mov.setHora(result.getTime("hora"));
				mov.setDescricao(result.getString("descricao"));
				mov.setValor(result.getBigDecimal("valor"));
				mov.setTipoM(result.getString("tipo_movimento"));

				listaOperacoes.add(mov);
			}
			
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaOperacoes;

	}

	@Override
	public List<Movimentacao> listarOperacoesAgencia(String agencia, Date dataInicial, Date dataFinal) {
		ArrayList<Movimentacao> listaOperacoes = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT CM.*, C.AGENCIA FROM CONTAS_MOVIMENTO CM INNER JOIN CONTAS C ON C.NUMERO = CM.CONTA_NUMERO WHERE AGENCIA = ? AND DATA BETWEEN ? AND ?");
			ps.setString(1, agencia);
			ps.setDate(2, new java.sql.Date(dataInicial.getTime()));
			ps.setDate(3, new java.sql.Date(dataFinal.getTime()));
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Movimentacao mov = new Movimentacao();
				mov.setConta(new DaoConta().buscar(result.getString("conta_numero")));
				mov.setData(result.getDate("data"));
				mov.setHora(result.getTime("hora"));
				mov.setDescricao(result.getString("descricao"));
				mov.setValor(result.getBigDecimal("valor"));
				mov.setTipoM(result.getString("tipo_movimento"));

				listaOperacoes.add(mov);
			}
			
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaOperacoes;

	}

	@Override
	public boolean pagar(String codigoBarras, BigDecimal valor) {
		boolean resultado = false;
		
		try {
			if(temSaldo(TelaPadrao.conta.getNumero(), TelaPadrao.conta.getAgencia().getNumero())){
				BigDecimal saldoAtual = new BigDecimal(0.0);
				saldoAtual = saldoAtual(TelaPadrao.conta.getNumero(), TelaPadrao.conta.getAgencia().getNumero());
				
				if(saldoAtual.compareTo(valor) >= 0){
					PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
							.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,?,?,?,?)");
					ps.setString(1, TelaPadrao.conta.getNumero());
					ps.setString(2, "PG");
					Date d = new Date();
					ps.setDate(3, new java.sql.Date(d.getTime()));
					ps.setTime(4, new java.sql.Time(d.getTime()));
					valor = valor.subtract(valor.add(valor)); 
					ps.setBigDecimal(5, valor);
					ps.setString(6, "Pagamento do documento ".concat(codigoBarras));
					ps.executeUpdate();
					resultado = true;
				}else{
					Funcoes.msgAviso("Saldo insuficiente.");
				}
			}else{
				Funcoes.msgAviso("Não consta saldo na sua conta.");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
}
