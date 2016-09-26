package br.univel.enuns;

public enum TipoMovimentacao {

	SAQUE("Saque", "S"),
	DEPOSITO("Depósito", "D"),
	TRANSFERENCIA("Transferência", "T"),
	PAGAMENTO("Pagamento", "PG");
	
	private final String descricao;
	private final String sigla;
	
	private TipoMovimentacao(String descricao, String sigla){
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSigla() {
		return sigla;
	}

}
