package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PagamentoCliente extends JPanel{
	private JTextField txtCodigoBarras;
	public PagamentoCliente() {
		
		JLabel lblCdigoDeBarras = new JLabel("C\u00F3digo de Barras");
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setColumns(10);
		
		JFormattedTextField txtValor = new JFormattedTextField();
		txtValor.setText("R$ 0,00");
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblValorASer = new JLabel("Valor a ser pago");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtCodigoBarras, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
							.addComponent(lblCdigoDeBarras)
							.addComponent(txtValor))
						.addComponent(lblValorASer, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnConfirme)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(81)
									.addComponent(lblValorASer))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addComponent(lblCdigoDeBarras)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCodigoBarras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(0)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnConfirme))))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
