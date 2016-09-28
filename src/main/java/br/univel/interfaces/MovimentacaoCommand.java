package br.univel.interfaces;

public interface MovimentacaoCommand {

	void criarMovimentacao();
	
	String getDescricao();
	
	String getSiglaMov();
	
	Boolean execute();
}
