package br.univel.classes.abstratas;

import br.univel.telas.TelaPadrao;

public abstract class PanelFilhoMenu extends PanelAbstrato{
	private TelaPadrao telaMenu;
	private boolean operacaoAprovada = false;	
	
	
	public TelaPadrao getTelaMenu() {
		return telaMenu;
	}

	public void setTelaMenu(TelaPadrao telaMenu) {
		this.telaMenu = telaMenu;
	}

	public boolean isOperacaoAprovada() {
		return operacaoAprovada;
	}

	public void setOperacaoAprovada(boolean operacaoAprovada) {
		this.operacaoAprovada = operacaoAprovada;
	}
}
