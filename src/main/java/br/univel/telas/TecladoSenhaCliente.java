package br.univel.telas;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TecladoSenhaCliente extends JFrame{
	private JPasswordField txtSenha;
	public TecladoSenhaCliente() {
		
		txtSenha = new JPasswordField();
		txtSenha.setEditable(false);
		
		JLabel lblDigiteSuaSenha = new JLabel("Digite sua senha utilizando o teclado virtual");
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDigiteSuaSenha)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnConfirmar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblDigiteSuaSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnConfirmar))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		
		JButton btn0 = new JButton("0");
		
		JButton btn1 = new JButton("1");
		
		JButton btn2 = new JButton("2");
		
		JButton btn4 = new JButton("4");
		
		JButton btn3 = new JButton("3");
		
		JButton btn5 = new JButton("5");
		
		JButton btn6 = new JButton("6");
		
		JButton btn7 = new JButton("7");
		
		JButton btn8 = new JButton("8");
		
		JButton btn9 = new JButton("9");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn0)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn7, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn8, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn9, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn3)
						.addComponent(btn4)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btn0)
							.addComponent(btn1)
							.addComponent(btn2)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn5)
						.addComponent(btn6)
						.addComponent(btn7)
						.addComponent(btn8)
						.addComponent(btn9))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
