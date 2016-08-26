package br.univel.telas;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;

public class PrincipalCliente extends JPanel{
	
	public PrincipalCliente() {
		setLayout(null);
		setSize(558, 284);

		JButton btnSaque = new JButton("1 - Saques");
		btnSaque.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaque.setBounds(30, 24, 160, 39);
		add(btnSaque);

		JButton btnSaldo = new JButton("2 - Saldo");
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaldo.setBounds(30, 122, 160, 39);
		add(btnSaldo);	
		
		JButton btnDepositos = new JButton("3 - Depósitos");
		btnDepositos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDepositos.setBounds(30, 217, 160, 39);
		add(btnDepositos);	
		
		JButton btnTransf = new JButton("4 - Transferências");
		btnTransf.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransf.setBounds(345, 24, 160, 39);
		add(btnTransf);

		JButton btnPagamentos = new JButton("5 - Pagamentos");
		btnPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPagamentos.setBounds(345, 122, 160, 39);
		add(btnPagamentos);	
		
		JButton btnFinalizar = new JButton("6 - Finalizar");
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFinalizar.setBounds(345, 217, 160, 39);
		add(btnFinalizar);	

	}
	

}
