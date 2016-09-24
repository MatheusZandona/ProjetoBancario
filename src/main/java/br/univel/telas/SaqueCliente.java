package br.univel.telas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.abstratas.PanelFilhoMenu;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoLogin;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.observable.Saldo;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SaqueCliente extends PanelFilhoMenu{
	
	JFormattedTextField txtValor;
	
	public SaqueCliente() {
		
		JButton btn50reais = new JButton("R$ 50,00");
		btn50reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmaOperacao(new BigDecimal(50.00));
			}
		});
		btn50reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn100reais = new JButton("R$ 100,00");
		btn100reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmaOperacao(new BigDecimal(100.00));
			}
		});
		btn100reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn500reais = new JButton("R$ 500,00");
		btn500reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmaOperacao(new BigDecimal(500.00));
			}
		});
		btn500reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblInformeOutroValor = new JLabel("Informe outro valor");
		
		txtValor = new JFormattedTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caracteres="0987654321.";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
		});
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setText("0.00");		
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal valor = new BigDecimal(txtValor.getText());
				
				if(valor.compareTo(new BigDecimal(0.00)) > 0){
					confirmaOperacao(valor);
				}else{
					Funcoes.msgAviso("Valor inválido.");
				}
			}
		});
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btn300reais = new JButton("R$ 300,00");
		btn300reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmaOperacao(new BigDecimal(300.00));
			}
		});
		btn300reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn200reais = new JButton("R$ 200,00");
		btn200reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmaOperacao(new BigDecimal(200.00));
			}
		});
		btn200reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn50reais, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(155)
							.addComponent(btn200reais, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btn100reais, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn500reais, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
							.addGap(155)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btn300reais, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblInformeOutroValor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnConfirme, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn50reais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn200reais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn100reais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn300reais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn500reais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblInformeOutroValor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtValor)
								.addComponent(btnConfirme, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))))
					.addGap(44))
		);
		setLayout(groupLayout);
	}
	
	protected void limparCampos() {
		txtValor.setText("0.00");
	}
	
	private void confirmaOperacao(BigDecimal valor){
		TecladoSenhaCliente  teclado = new TecladoSenhaCliente(this);
		teclado.setSize(540, 200);
		teclado.setLocationRelativeTo(null);
		teclado.setVisible(true);
		
		if(isOperacaoAprovada()){
			if(Sacar(valor)){
				TelaPadrao telaConfirma = new TelaPadrao(TipoLogin.CLIENTE, new ConfirmaOperacao(TipoMovimentacao.SAQUE, valor));
				telaConfirma.setSize(600, 450);
				telaConfirma.setLocationRelativeTo(null);
				telaConfirma.setVisible(true);
			}
			
		}else{
			Funcoes.msgAviso("Não foi possível realizar o saque devido a falta de confirmação.");
		}
	}	

	private boolean Sacar( BigDecimal valor ){
		DaoMovimentacao daoMov = new DaoMovimentacao();
		boolean resultado = daoMov.sacar(valor, TelaPadrao.conta, TelaPadrao.conta.getAgencia());
					
		Saldo saldo = new Saldo();
		saldo.addObservers(getTelaPadrao());
		saldo.addObservers(getTelaMenu());
		saldo.alterarSaldo();
		
		return resultado;
	}
}
