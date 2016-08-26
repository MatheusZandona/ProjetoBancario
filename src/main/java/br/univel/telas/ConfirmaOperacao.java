package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ConfirmaOperacao extends JPanel{
	public ConfirmaOperacao() {
		
		JLabel lblOperacao = new JLabel("Opera\u00E7\u00E3o $opera\u00E7\u00E3o realizada com sucesso!");
		lblOperacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblValor = new JLabel("Valor: R$ $valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnRetornar = new JButton("Retornar");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValor)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRetornar))
						.addComponent(lblOperacao))
					.addContainerGap(237, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(lblOperacao)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblValor)
						.addComponent(btnRetornar))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
