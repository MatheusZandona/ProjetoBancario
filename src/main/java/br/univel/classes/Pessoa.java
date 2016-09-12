package br.univel.classes;

import br.univel.enuns.TipoLogin;

public class Pessoa {
	
	private String  nome;	
	private Integer idade; 
	private String  cpf;
	private String senhaAcesso;
	private String senhaOperacoes;
	private TipoLogin tipoLogin;
	

	public Integer getIdade() {
		return idade;
	}

	public Pessoa setIdade(Integer idade) {
		this.idade = idade;
		return this;
	}
	
	public String getNome() {
		return nome;
	}

	public Pessoa setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getSenhaAcesso() {
		return senhaAcesso;
	}

	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}

	public String getSenhaOperacoes() {
		return senhaOperacoes;
	}

	public void setSenhaOperacoes(String senhaOperacoes) {
		this.senhaOperacoes = senhaOperacoes;
	}

	public String getCpf() {
		return cpf;
	}

	public Pessoa setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}

	public Pessoa setTipoLogin(TipoLogin tipoLogin) {
		this.tipoLogin = tipoLogin;
		return this;
	}

}
