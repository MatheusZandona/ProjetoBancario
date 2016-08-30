package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalBancario extends JPanel{
	public PrincipalBancario() {
		
		JButton btnInfo = new JButton("1 - Info. de Contas");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUpConta popup = new PopUpConta();
				popup.setSize(400, 350);
				popup.setLocationRelativeTo(null);
				popup.setVisible(true);					
			}
		});
		btnInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNova = new JButton("2 - Nova Conta");
		btnNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaNovaConta = new TelaPadrao(new CadastroConta());
				telaNovaConta.setSize(600, 500);
				telaNovaConta.setLocationRelativeTo(null);
				telaNovaConta.setVisible(true);			
			}
		});
		btnNova.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnClientes = new JButton("3 - Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaClientes = new TelaPadrao(new PsqContas());
				telaClientes.setSize(600, 500);
				telaClientes.setLocationRelativeTo(null);
				telaClientes.setVisible(true);						
			}
		});
		btnClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAgncias = new JButton("6 - Ag\u00EAncias");
		btnAgncias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaAgencias = new TelaPadrao(new PsqAgencias());
				telaAgencias.setSize(600, 500);
				telaAgencias.setLocationRelativeTo(null);
				telaAgencias.setVisible(true);					
			}
		});
		btnAgncias.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnBalanos = new JButton("5 - Balan\u00E7os");
		btnBalanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaBalanco = new TelaPadrao(new Balanco());
				telaBalanco.setSize(600, 500);
				telaBalanco.setLocationRelativeTo(null);
				telaBalanco.setVisible(true);					
				
			}
		});
		btnBalanos.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnProfissionais = new JButton("4 - Profissionais");
		btnProfissionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPadrao telaBancarios = new TelaPadrao(new PsqUsuarios());
				telaBancarios.setSize(600, 500);
				telaBancarios.setLocationRelativeTo(null);
				telaBancarios.setVisible(true);			
			}
		});
		btnProfissionais.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnInfo, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(btnNova, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(124)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgncias, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBalanos, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnProfissionais, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnProfissionais, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInfo, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBalanos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNova, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgncias, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClientes, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
