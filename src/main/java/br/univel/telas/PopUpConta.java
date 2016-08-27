package br.univel.telas;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PopUpConta extends JFrame{
	private JTextField txtAgencia;
	private JTextField txtConta;
	private JTextField txtTitular;
	public PopUpConta() {
		
		txtAgencia = new JTextField();
		txtAgencia.setColumns(10);
		
		JLabel lblAgncia = new JLabel("Ag\u00EAncia");
		
		txtConta = new JTextField();
		txtConta.setColumns(10);
		
		JLabel lblConta = new JLabel("Conta");
		
		JComboBox cbbTipoConta = new JComboBox();
		
		JLabel lblTipoConta = new JLabel("Tipo Conta");
		
		txtTitular = new JTextField();
		txtTitular.setColumns(10);
		
		JButton btnConfirme = new JButton("Confirme");
		
		JLabel lblTitular = new JLabel("Titular");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoConta)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(cbbTipoConta, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAgncia))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblConta)
									.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnConfirme)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitular)
								.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAgncia)
						.addComponent(lblConta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTipoConta)
					.addGap(3)
					.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTitular)
					.addGap(2)
					.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnConfirme)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}