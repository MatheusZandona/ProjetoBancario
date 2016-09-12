package br.univel.classes.builder;

import java.math.BigDecimal;
import java.util.Date;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;

public class ContaBuilder {

	private String     nome;	
	private Integer    idade; 
	private String     cpf;
	private String     senhaAcesso;
	private String     senhaOperacoes;
	private TipoConta  tipoConta; // 0 = Corrente  1 = Poupança  2 = Eletronica   no Banco de Dados
	private Agencia    agencia;
	private String     numero;
	private Date       dtAbertura;
	private BigDecimal saldo;
	
	public ContaBuilder(){
		this.saldo = new BigDecimal(0.00);
	}	
	
	public String getNome() {
		return nome;
	}
	
	public ContaBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public ContaBuilder setIdade(Integer idade) {
		this.idade = idade;
		return this;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public ContaBuilder setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public String getSenhaAcesso() {
		return senhaAcesso;
	}
	
	public ContaBuilder setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
		return this;
	}
	
	public String getSenhaOperacoes() {
		return senhaOperacoes;
	}
	
	public ContaBuilder setSenhaOperacoes(String senhaOperacoes) {
		this.senhaOperacoes = senhaOperacoes;
		return this;
	}
	
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	
	public ContaBuilder setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
		return this;
	}
	
	public Agencia getAgencia() {
		return agencia;
	}
	
	public ContaBuilder setAgencia(Agencia agencia) {
		this.agencia = agencia;
		return this;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public ContaBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}
	
	public Date getDtAbertura() {
		return dtAbertura;
	}
	
	public ContaBuilder setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
		return this;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public ContaBuilder setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
		return this;
	}	
	
	public Conta build() {
		return new Conta(this.getNome(),
						 this.getIdade(),
						 this.getCpf(),
						 this.getSenhaAcesso(),
						 this.getSenhaOperacoes(),
						 this.getAgencia(),
						 this.getNumero(),
						 this.getDtAbertura(),
						 this.getTipoConta(),
						 this.getSaldo());
	}		
}
