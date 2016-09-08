package br.univel.enuns;

public enum TipoConta {

	CORRENTE("CC"),
	POUPANÇA("CP"),
	ELETRONICA("CE");
	
	private final String sigla;
	
	private TipoConta(String sigla){
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}
	
	@Override
	public String toString() {
		return getSigla();
	}	
}
