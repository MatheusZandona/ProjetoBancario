package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class CadastroAgencia extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public CadastroAgencia() {
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNome = new JLabel("Nome:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00FAmero");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(72, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNumero)
								.addPreferredGap(ComponentPlacement.RELATED, 267, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED, 273, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnConfirme)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 218, GroupLayout.PREFERRED_SIZE)))
					.addGap(69))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(49, Short.MAX_VALUE)
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumero)
						.addComponent(lblCidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnConfirme)
					.addGap(33))
		);
		setLayout(groupLayout);
	}
}
