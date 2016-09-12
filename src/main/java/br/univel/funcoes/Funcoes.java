package br.univel.funcoes;

import java.awt.Container;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.univel.telas.TelaPadrao;

public class Funcoes {
	
	/*
	 * Função para remover caracteres especiais de uma string
	 */
	public static String removerCaracteresEspeciais(String text){
	
		String textLimpo = "";
		textLimpo = text.replaceAll("[^a-zZ-Z1-9 ]", "");
		return textLimpo;
	}
	
	/*
	 * Função para mostrar uma mensagem formatada com ícone de aviso
	 */
	public static void msgAviso(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);		
	}

	/*
	 * Função para mostrar uma mensagem formatada com ícone de informação
	 */
	public static void msgInforma(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	/*
	 * Função para mostrar uma mensagem formatada com ícone de erro
	 */
	public static void msgErro(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);		
	}
	
	/*
	 *Função para mostrar uma pergunta formatada com ícone de interrogação
	 *
	 * Retorna true se o usuário clicar em "Sim"	
	 */
	public static boolean msgConfirma(String mensagem){
		int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Pergunta", JOptionPane.YES_NO_OPTION);
		return opcao == 0;
	}
	
	/*
	 * Função para fechar o JFrame pai de um Jpanel
	 */
	public static void fecharTelaPadrao(Container tela){
		
		while(!tela.getClass().getSimpleName().equals("TelaPadrao")){
			tela = tela.getParent();
		}
		
		((TelaPadrao) tela).dispose();		
	}
	
}
