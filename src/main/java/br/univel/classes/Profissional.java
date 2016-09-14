package br.univel.classes;

import br.univel.enuns.TipoLogin;

public class Profissional extends Pessoa{
	
	private Integer id;
	private String username;
	
	
	public Profissional(){
		
	}

	public Profissional(Integer id2, String nome, Integer idade, String senhaAcesso, String senhaOperacoes,
			String username2, TipoLogin tipoLogin) {
		
		this.id = id2;
		this.username = username2;
		setIdade(idade);
		setNome(nome);
		setSenhaAcesso(senhaAcesso);
		setSenhaOperacoes(senhaOperacoes);
		setTipoLogin(tipoLogin);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int getID) {
		this.id = getID;
	}

}
