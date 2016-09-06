package br.univel.classes;

public class Usuario {
	
	private Integer id;
	private String  user;
	private String  senha;
	private Pessoa  pessoa;
	
	public Integer getId() {
		return id;
	}
	public Usuario setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getUser() {
		return user;
	}
	public Usuario setUser(String user) {
		this.user = user;
		return this;
	}
	public String getSenha() {
		return senha;
	}
	public Usuario setSenha(String senha) {
		this.senha = senha;
		return this;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public Usuario setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		return this;
	}
	
}
 