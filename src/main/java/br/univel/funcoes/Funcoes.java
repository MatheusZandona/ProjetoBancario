package br.univel.funcoes;

public class Funcoes {
	public static String removerCaracteresEspeciais(String text){
		String textLimpo = "";
		textLimpo = text.replaceAll("[^a-zZ-Z1-9 ]", "");
		return textLimpo;
	}
	
}
