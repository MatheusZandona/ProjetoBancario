package br.univel.classes.abstratas;

import br.univel.telas.TelaPadrao;

public abstract class PanelFilhoMenu extends PanelAbstrato{
	private TelaPadrao telaMenu;

	public TelaPadrao getTelaMenu() {
		return telaMenu;
	}

	public void setTelaMenu(TelaPadrao telaMenu) {
		this.telaMenu = telaMenu;
	}
}
