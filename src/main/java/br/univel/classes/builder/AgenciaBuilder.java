package br.univel.classes.builder;

import br.univel.classes.Agencia;

public class AgenciaBuilder {
	
	private String numero;
	private String nome;
	private String cidade;
	
	public String getNumero() {
		return numero;
	}
	public AgenciaBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public AgenciaBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public String getCidade() {
		return cidade;
	}
	public AgenciaBuilder setCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}	
	
	public Agencia build() {
		return new Agencia(this.numero, this.nome, this.cidade);
	}	

}
