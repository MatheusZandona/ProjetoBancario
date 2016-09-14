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

import br.univel.classes.abstratas.PanelAbstrato;

import javax.swing.JButton;

public class PagamentoCliente extends PanelAbstrato{
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
		
		JLabel lblValor = new JLabel("Valor a ser pago");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblValor)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtValor, Alignment.LEADING)
								.addComponent(txtCodigoBarras, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(lblCdigoDeBarras, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirme)))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblCdigoDeBarras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCodigoBarras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblValor)
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirme))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
