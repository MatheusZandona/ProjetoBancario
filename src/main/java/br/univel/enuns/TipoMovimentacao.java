package br.univel.enuns;

public enum TipoMovimentacao {

	SAQUE("Saque"),
	DEPOSITO("Depósito"),
	TRANSFERENCIA("Transferência"),
	PAGAMENTO("Pagamento");
	
	private final String descricao;
	
	private TipoMovimentacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
