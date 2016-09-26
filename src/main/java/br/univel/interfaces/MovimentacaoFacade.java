package br.univel.interfaces;

public interface MovimentacaoFacade {

	void criarMovimentacao();
	
	String getDescricao();
	
	String getSiglaMov();
	
	Boolean execute();
}
