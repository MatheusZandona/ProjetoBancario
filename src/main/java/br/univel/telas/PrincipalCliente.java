package br.univel.telas;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalCliente extends JPanel{
	
	public PrincipalCliente() {
		setSize(558, 284);

		JButton btnSaque = new JButton("1 - Saques");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaPadrao telaSaque = new TelaPadrao(new SaqueCliente());
				telaSaque.setSize(600, 500);
				telaSaque.setLocationRelativeTo(null);
				telaSaque.setVisible(true);					
			}
		});
		btnSaque.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnSaldo = new JButton("2 - Saldo");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaSaldo = new TelaPadrao(new SaldoCliente());
				telaSaldo.setSize(800, 600);
				telaSaldo.setLocationRelativeTo(null);
				telaSaldo.setVisible(true);					
			}
		});
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnDepositos = new JButton("3 - Depósitos");
		btnDepositos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaDeposito = new TelaPadrao(new DepositoCliente());
				telaDeposito.setSize(600, 500);
				telaDeposito.setLocationRelativeTo(null);
				telaDeposito.setVisible(true);						
			}
		});
		btnDepositos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnTransf = new JButton("4 - Transferências");
		btnTransf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaTransferencia = new TelaPadrao(new TransferenciaCliente());
				telaTransferencia.setSize(600, 500);
				telaTransferencia.setLocationRelativeTo(null);
				telaTransferencia.setVisible(true);				
			}
		});
		btnTransf.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnPagamentos = new JButton("5 - Pagamentos");
		btnPagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaPagamentos = new TelaPadrao(new PagamentoCliente());
				telaPagamentos.setSize(600, 500);
				telaPagamentos.setLocationRelativeTo(null);
				telaPagamentos.setVisible(true);								
			}
		});
		btnPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnFinalizar = new JButton("6 - Finalizar");
		btnFinalizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSaque, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addComponent(btnTransf, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSaldo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addComponent(btnPagamentos, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDepositos, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSaque, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTransf, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSaldo, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPagamentos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDepositos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);

	}
	

}
