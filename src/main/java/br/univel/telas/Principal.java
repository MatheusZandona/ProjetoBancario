package br.univel.telas;

import java.awt.Frame;

public class Principal{
	
	public static void main(String[] args) {
		TelaPadrao principal = new TelaPadrao();
		principal.setExtendedState(Frame.MAXIMIZED_BOTH);
		principal.setLocationRelativeTo(null); //centraliza na tela
		principal.setVisible(true);
	}

}
