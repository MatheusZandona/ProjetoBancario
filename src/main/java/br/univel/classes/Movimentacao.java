package br.univel.classes;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Movimentacao {

	private Conta      conta;
	private String     tipoM;   // S = SAQUE /  D = DEPOSITO  / TE, TS = TRANSDERENCIA
	private Date       data;
	private Time       hora;
	private BigDecimal valor;
	private String     descricao;
	
	public Conta getConta() {
		return conta;
	}
	public Movimentacao setConta(Conta conta) {
		this.conta = conta;
		return this;
	}
		
	public String getTipoM() {
		return tipoM;
	}
	public Movimentacao setTipoM(String tipoM) {
		this.tipoM = tipoM;
		return this;
	}
	public Date getData() {
		return data;
	}
	public Movimentacao setData(Date data) {
		this.data = data;
		return this;
	}
	public Time getHora() {
		return hora;
	}
	public Movimentacao setHora(Time hora) {
		this.hora = hora;
		return this;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public Movimentacao setValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	public String getDescricao() {
		return descricao;
	}
	public Movimentacao setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public Movimentacao(Conta conta, String tipoM, BigDecimal valor, String descricao) {
		this.conta = conta;
		this.valor = valor;
		this.descricao = descricao;
		this.tipoM     = tipoM;
	}
	
	public Movimentacao(){
		
	}
	
}
