package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class TransferenciaCliente extends JPanel{
	private JTextField txtAgencia;
	private JTextField txtConta;
	private JTextField txtTitular;
	public TransferenciaCliente() {
		
		JLabel lblContaDeDestinocrdito = new JLabel("Conta de destino/Cr\u00E9dito");
		lblContaDeDestinocrdito.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel label = new JLabel("AG:");
		
		txtAgencia = new JTextField();
		txtAgencia.setColumns(10);
		
		JLabel label_1 = new JLabel("Tipo Conta");
		
		JComboBox cbbTipoConta = new JComboBox();
		cbbTipoConta.setSelectedIndex(0);
		cbbTipoConta.setMaximumRowCount(3);
		
		txtConta = new JTextField();
		txtConta.setColumns(10);
		
		JLabel label_2 = new JLabel("Conta");
		
		JLabel label_3 = new JLabel("Titular");
		
		txtTitular = new JTextField();
		txtTitular.setColumns(10);
		
		JFormattedTextField txtValor = new JFormattedTextField();
		txtValor.setText("R$ 0,00");
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblValor = new JLabel("Valor");
		
		JButton button = new JButton("Confirme");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(74)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblContaDeDestinocrdito)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))))
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblContaDeDestinocrdito)
					.addGap(18)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_2)
						.addComponent(label_3))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblValor)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
