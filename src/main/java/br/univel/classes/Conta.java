package br.univel.classes;

import java.math.BigDecimal;
import java.util.Date;

import br.univel.enuns.TipoConta;

public class Conta extends Pessoa{
	
	private TipoConta  tipoConta; // 0 = Corrente  1 = Poupança  2 = Eletronica   no Banco de Dados
	private Agencia    agencia;
	private String     numero;
	private Date       dtAbertura;
	private BigDecimal saldo;
	

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}
	
}
