package br.univel.interfaces;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Movimentacao;
import br.univel.classes.bd.ConexaoBD;
import br.univel.funcoes.Funcoes;

public interface DaoMov {

	public boolean sacar(BigDecimal valor, Conta conta, Agencia agencia);
	public boolean depositar(BigDecimal valor, Conta conta, Agencia agencia);
	public BigDecimal saldoAtual(String conta, String agencia);
	public boolean temSaldo(String conta,  String agencia);
	public boolean transferir(BigDecimal valor, String contaDest, String agenciaDest, String contaOri, String agenciaOri);
	public List<Movimentacao> listarOperacoesConta(Date dataInicial, Date dataFinal);
	public List<Movimentacao> listarOperacoesAgencia(String agencia, Date dataInicial, Date dataFinal);
	public boolean pagar(String codigoBarras, BigDecimal valor);
}
