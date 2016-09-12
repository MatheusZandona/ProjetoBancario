package br.univel.enuns;

public enum TipoConta {

	CORRENTE("Conta Corrente", "CC"),
	POUPANÇA("Conta Poupança", "CP"),
	ELETRONICA("Conta Eletrônica", "CE");
	
	private final String descricao;
	private final String sigla;
	
	private TipoConta(String descricao, String sigla){
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public String getDescricao(){
		return descricao;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}	
}
