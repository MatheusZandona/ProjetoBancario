package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.Profissional;
import br.univel.classes.dao.DaoProfissional;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProfissional extends JPanel{
	public static int status = 0;   // 0 = inserindo  1 = alterando
	
	private JTextField txtNome;
	private JTextField txtIdade;
	private JPasswordField txtSenhaOp;
	private JPasswordField txtSenhaConta;
	private JTextField txtUserName;
	
	
	public CadastroProfissional() {
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel label = new JLabel("Nome");
		
		JLabel label_1 = new JLabel("Idade");
		
		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		
		JLabel label_2 = new JLabel("Senha opera\u00E7\u00F5es");
		
		txtSenhaOp = new JPasswordField();
		
		txtSenhaConta = new JPasswordField();
		
		JLabel lblSenhaAcesso = new JLabel("Senha Acesso");
		
		JButton button = new JButton("Confirme");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblUsername = new JLabel("UserName: ");
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtSenhaOp, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblSenhaAcesso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtSenhaConta, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblUsername)
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtUserName)))
					.addContainerGap(161, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(label_1)
					.addGap(6)
					.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(lblSenhaAcesso))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSenhaOp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSenhaConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(button)
					.addGap(31))
		);
		setLayout(groupLayout);
	}
	
	private void salvarProfissional(){
		
		//inserir usuario
		Profissional p = new Profissional();
		DaoProfissional daoP = new DaoProfissional();
		
		p.setNome(txtNome.getText());
		p.setIdade(Integer.parseInt(txtIdade.getText()));
		p.setSenhaAcesso(txtSenhaConta.getText());
		p.setSenhaOperacoes(txtSenhaOp.getText());
		p.setUsername(txtUserName.getText());
		
		daoP.salvar(p);
	}
}
