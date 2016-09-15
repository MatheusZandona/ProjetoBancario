package br.univel.classes;

import java.math.BigDecimal;
import java.util.Date;

import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;

public class Conta extends Pessoa{
	
	private TipoConta  tipoConta; // 0 = Corrente  1 = Poupança  2 = Eletronica   no Banco de Dados
	private Agencia    agencia;
	private String     numero;
	private Date       dtAbertura;
	private BigDecimal saldo;
	

	public Conta(){
		this.setTipoLogin(TipoLogin.CLIENTE);
		this.setSaldo(BigDecimal.ZERO);
	}
	
	
	public Conta(String nome2, Integer idade2, String cpf2, String senhaAcesso2, String senhaOperacoes2,
			Agencia agencia2, String numero2, Date dtAbertura2, TipoConta tipoConta2, BigDecimal saldo2) {

		this.tipoConta      = tipoConta2;
		this.agencia        = agencia2;
		this.numero         = numero2;
		this.dtAbertura     = dtAbertura2;
		this.saldo			= saldo2;
		this.setTipoLogin(TipoLogin.CLIENTE);
		this.setCpf(cpf2);
		this.setIdade(idade2);
		this.setNome(nome2);
		this.setSenhaAcesso(senhaAcesso2);
		this.setSenhaOperacoes(senhaOperacoes2);		
		
	}

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
