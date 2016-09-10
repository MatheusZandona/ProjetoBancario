package br.univel.classes;

public class Agencia {

	private String numero;
	private String nome;
	private String cidade;
	 
	public String getNome() {
		return nome;
	}
	
	public Agencia setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public Agencia setCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public String getNumero() {
		return numero;
	}

	public Agencia setNumero(String numero) {
		this.numero = numero;
		return this;
	}
	
}
