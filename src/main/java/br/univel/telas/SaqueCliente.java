package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.enuns.TipoLogin;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaqueCliente extends JPanel{
	
	
	private void Sacar( BigDecimal valor ){
		
		TecladoSenhaCliente teclado = null;
		teclado = new TecladoSenhaCliente();
		teclado.setSize(515, 200);
		teclado.setLocationRelativeTo(null);
		teclado.setVisible(true);	
		
		
		TelaPadrao telaSaldo = null;
		telaSaldo = new TelaPadrao(TipoLogin.CLIENTE, new ConfirmaOperacao(telaSaldo));
		telaSaldo.setSize(600, 400);
		telaSaldo.setLocationRelativeTo(null);
		telaSaldo.setVisible(true);				
		
	}
	
	
	
	public SaqueCliente() {
		
		JButton btn50reais = new JButton("R$ 50,00");
		btn50reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(50.00));
			}
		});
		btn50reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn100reais = new JButton("R$ 100,00");
		btn100reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(100.00));				
			}
		});
		btn100reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn500reais = new JButton("R$ 500,00");
		btn500reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(500.00));				
			}
		});
		btn500reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblInformeOutroValor = new JLabel("Informe outro valor");
		
		JFormattedTextField txtValor = new JFormattedTextField();
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setText("R$ 0,00");		
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(txtValor.getText()));				
			}
		});
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btn300reais = new JButton("R$ 300,00");
		btn300reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(300.00));				
			}
		});
		btn300reais.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btn200reais = new JButton("R$ 200,00");
		btn200reais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sacar(new BigDecimal(200.00));								
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
}
