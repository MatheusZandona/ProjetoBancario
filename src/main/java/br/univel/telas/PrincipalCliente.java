package br.univel.telas;

import javax.swing.JButton;

import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.command.DepositoCommand;
import br.univel.command.MovimentarConta;
import br.univel.command.SaqueCommand;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
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
	
	private void habilitarBotoes(){

		switch (TelaPadrao.conta.getTipoConta()) {
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
	
	public PrincipalCliente() {
		setSize(558, 284);

		btnSaque = new JButton("1 - Saques");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaqueCliente panel = new SaqueCliente();
				panel.setTelaMenu(getTelaPadrao());
				
				TelaPadrao telaSaque = new TelaPadrao(TipoLogin.CLIENTE, panel);
				telaSaque.setSize(600, 500);
				telaSaque.setLocationRelativeTo(null);
				telaSaque.setVisible(true);					
			}
		});
		btnSaque.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSaldo = new JButton("2 - Saldo");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaSaldo = new TelaPadrao(TipoLogin.CLIENTE, new SaldoCliente());
				telaSaldo.setSize(800, 600);
				telaSaldo.setLocationRelativeTo(null);
				telaSaldo.setVisible(true);					
			}
		});
		btnSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnDepositos = new JButton("3 - Depósitos");
		btnDepositos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DepositoCliente panel = new DepositoCliente();
				panel.setTelaMenu(getTelaPadrao());
				
				TelaPadrao telaDeposito = new TelaPadrao(TipoLogin.CLIENTE, panel);
				telaDeposito.setSize(600, 500);
				telaDeposito.setLocationRelativeTo(null);
				telaDeposito.setVisible(true);
			}
		});
		btnDepositos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnTransf = new JButton("4 - Transferências");
		btnTransf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TransferenciaCliente panel = new TransferenciaCliente();
				panel.setTelaMenu(getTelaPadrao());
			
				
				TelaPadrao telaTransferencia = new TelaPadrao(TipoLogin.CLIENTE, panel);
				telaTransferencia.setSize(600, 500);
				telaTransferencia.setLocationRelativeTo(null);
				telaTransferencia.setVisible(true);				
			}
		});
		btnTransf.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnPagamentos = new JButton("5 - Pagamentos");
		btnPagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PagamentoCliente panel = new PagamentoCliente();
				panel.setTelaMenu(getTelaPadrao());
				
				
				TelaPadrao telaPagamentos = new TelaPadrao(TipoLogin.CLIENTE, panel);
				telaPagamentos.setSize(600, 500);
				telaPagamentos.setLocationRelativeTo(null);
				telaPagamentos.setVisible(true);								
			}
		});
		btnPagamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnFinalizar = new JButton("6 - Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finalizar();
				
				Saldo saldo = new Saldo();
				saldo.addObservers(getTelaPadrao());
				saldo.alterarSaldo();
			}
		});
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
		habilitarBotoes();
		// $hide<<$			

	}

	protected void finalizar() {
		DaoConta daoC = DaoConta.getInstance();
		DaoMovimentacao daoM = DaoMovimentacao.getInstance();
		BigDecimal saldoAtual = DaoConta.getInstance().saldoAtual(TelaPadrao.conta, TelaPadrao.conta.getAgencia());
		
		if(saldoAtual.compareTo(new BigDecimal("0.00")) > 0){

			if(saldoAtual.compareTo(new BigDecimal("0.00")) > 0){
				if(Funcoes.msgConfirma("Você tem um crédito de ".concat(saldoAtual.toString()).concat(". Para "
						+ "finalizar sua conta é necessário ter um saldo de 0.00. Deseja sacar esse valor ?"))){
					
					new MovimentarConta(new SaqueCommand(saldoAtual)).executaAcao();
					
					if(Funcoes.msgConfirma("Tem certeza que deseja finalizar sua conta?")){
						daoC.excluir(TelaPadrao.conta.getNumero());
						getTelaPadrao().dispose();
					}
					
				}
			}
		}else{
			if(saldoAtual.compareTo(new BigDecimal("0.00")) < 0){
				if(Funcoes.msgConfirma("Você tem um défite de R$ ".concat(saldoAtual.toString()).concat(". Para "
						+ "finalizar sua conta é necessário ter um saldo de 0.00. Deseja depositar esse valor ?"))){

					saldoAtual = saldoAtual.negate();					
					new MovimentarConta(new DepositoCommand(TelaPadrao.conta, saldoAtual)).executaAcao();
					
					if(Funcoes.msgConfirma("Tem certeza que deseja finalizar sua conta?")){
						daoC.excluir(TelaPadrao.conta.getNumero());
						getTelaPadrao().dispose();
					}
				}
			}else{
				if(Funcoes.msgConfirma("Tem certeza que deseja finalizar sua conta?")){
					daoC.excluir(TelaPadrao.conta.getNumero());
					getTelaPadrao().dispose();
				}
			}
		}
	}
	
	
}
