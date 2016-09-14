package br.univel.telas;

import javax.swing.JButton;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;
import br.univel.observable.Saldo;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class PrincipalCliente extends PanelAbstrato{
	
	private JButton btnSaque;
	private JButton btnSaldo;
	private JButton btnDepositos;
	private JButton btnTransf;
	private JButton btnPagamentos;
	private JButton btnFinalizar;
	
	private void habilitarBotoes(TipoConta tipo){

		switch (tipo) {
			case CORRENTE:
				btnSaque.setEnabled(true);
				btnSaldo.setEnabled(true);
				btnDepositos.setEnabled(true);
				btnTransf.setEnabled(true);
				btnPagamentos.setEnabled(true);
				btnTransf.setEnabled(true);
				break;
	
			case ELETRONICA:
				btnSaque.setEnabled(false);
				btnSaldo.setEnabled(true);
				btnDepositos.setEnabled(false);
				btnTransf.setEnabled(true);
				btnPagamentos.setEnabled(true);
				btnTransf.setEnabled(true);
				break;
	
			case POUPANÇA:
				btnSaque.setEnabled(true);
				btnSaldo.setEnabled(true);
				btnDepositos.setEnabled(true);
				btnTransf.setEnabled(true);
				btnPagamentos.setEnabled(false);
				btnTransf.setEnabled(true);
				break;			
				
			default:
				break;
		}
	}
	
	public PrincipalCliente(TipoConta tipo) {
		setSize(558, 284);

		btnSaque = new JButton("1 - Saques");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaPadrao telaSaque = setTelaPadrao(new TelaPadrao(TipoLogin.CLIENTE, new SaqueCliente())).getTelaPadrao();
				telaSaque.setSize(600, 500);
				telaSaque.setLocationRelativeTo(null);
				telaSaque.setVisible(true);					
			}
		});
		btnSaque.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSaldo = new JButton("2 - Saldo");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaSaldo = setTelaPadrao(new TelaPadrao(TipoLogin.CLIENTE, new SaldoCliente())).getTelaPadrao();
				telaSaldo.setSize(800, 600);
				telaSaldo.setLocationRelativeTo(null);
				telaSaldo.setVisible(true);					
			}
		});
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnDepositos = new JButton("3 - Depósitos");
		btnDepositos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaDeposito = setTelaPadrao(new TelaPadrao(TipoLogin.CLIENTE, new DepositoCliente())).getTelaPadrao();
				telaDeposito.setSize(600, 500);
				telaDeposito.setLocationRelativeTo(null);
				telaDeposito.setVisible(true);
			}
		});
		btnDepositos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnTransf = new JButton("4 - Transferências");
		btnTransf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaTransferencia = setTelaPadrao(new TelaPadrao(TipoLogin.CLIENTE, new TransferenciaCliente())).getTelaPadrao();
				telaTransferencia.setSize(600, 500);
				telaTransferencia.setLocationRelativeTo(null);
				telaTransferencia.setVisible(true);				
			}
		});
		btnTransf.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPagamentos = new JButton("5 - Pagamentos");
		btnPagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaPagamentos = setTelaPadrao(new TelaPadrao(TipoLogin.CLIENTE, new PagamentoCliente())).getTelaPadrao();
				telaPagamentos.setSize(600, 500);
				telaPagamentos.setLocationRelativeTo(null);
				telaPagamentos.setVisible(true);								
			}
		});
		btnPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnFinalizar = new JButton("6 - Finalizar");
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
		
		// $hide>>$
		habilitarBotoes(tipo);
		// $hide<<$			

	}
	

}
