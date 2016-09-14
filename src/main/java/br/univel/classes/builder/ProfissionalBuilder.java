package br.univel.classes.builder;

import br.univel.classes.Profissional;
import br.univel.enuns.TipoLogin;

public class ProfissionalBuilder {
	
	private String  nome;	
	private Integer idade; 
	private String senhaAcesso;
	private String senhaOperacoes;
	private TipoLogin tipoLogin;
	private String username;	
	private Integer id;

	
	public String getNome() {
		return nome;
	}
	
	public ProfissionalBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public ProfissionalBuilder setIdade(Integer idade) {
		this.idade = idade;
		return this;
	}
	
	public String getSenhaAcesso() {
		return senhaAcesso;
	}
	
	public ProfissionalBuilder setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
		return this;
	}
	
	public String getSenhaOperacoes() {
		return senhaOperacoes;
	}
	
	public ProfissionalBuilder setSenhaOperacoes(String senhaOperacoes) {
		this.senhaOperacoes = senhaOperacoes;
		return this;
	}
	
	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}
	
	public ProfissionalBuilder setTipoLogin(TipoLogin tipoLogin) {
		this.tipoLogin = tipoLogin;
		return this;
	}
	
	public String getUsername() {
		return username;
	}

	public ProfissionalBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public ProfissionalBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	public Profissional build() {
		return new Profissional(this.getId(),
								this.getNome(),
								this.getIdade(),
								this.getSenhaAcesso(),
								this.getSenhaOperacoes(),
								this.getUsername(),
								this.getTipoLogin());
	}		
}
