package br.univel.classes.abstratas;

import javax.swing.JPanel;

import br.univel.telas.TelaPadrao;

public abstract class PanelAbstrato extends JPanel{

	private TelaPadrao telaPadrao;
	
	public TelaPadrao getTelaPadrao() {
		return telaPadrao;
	}

	public void setTelaPadrao(TelaPadrao telaPadrao) {
		this.telaPadrao = telaPadrao;
	}
}


