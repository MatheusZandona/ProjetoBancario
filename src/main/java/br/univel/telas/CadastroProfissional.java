package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.Profissional;
import br.univel.classes.dao.DaoProfissional;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProfissional extends JPanel{
	private static int edit = 0;   // 0 = inserindo  1 = alterando
	
	private JTextField txtNome;
	private JTextField txtIdade;
	private JPasswordField txtSenhaOp;
	private JPasswordField txtSenhaConta;
	private JTextField txtUserName;
	private static Profissional p;
	
	
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
					if(!validarCampos()){
						salvarProfissional();
						limparCampos();
					}
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
		
		if(edit == 1){
			txtNome.setText(p.getNome());
			txtIdade.setText(p.getIdade().toString());
			txtUserName.setText(p.getUsername());
			txtSenhaConta.setText(p.getSenhaAcesso());
			txtSenhaOp.setText(p.getSenhaOperacoes());
		}
	}
	
	private void salvarProfissional(){
	
		DaoProfissional daoP = new DaoProfissional();
		
		if(edit == 0){
			p = new Profissional();
		}
		
		p.setNome(txtNome.getText());
		p.setIdade(Integer.parseInt(txtIdade.getText()));
		p.setSenhaAcesso(txtSenhaConta.getText());
		p.setSenhaOperacoes(txtSenhaOp.getText());
		p.setUsername(txtUserName.getText());
		
		if(edit == 0){
			daoP.salvar(p);
		}else{
			daoP.atualizar(p);
		}
	}

	public static void setEdit(int edit1) {
		edit = edit1;
	}
	
	public boolean validarCampos(){  // true = erro
		if(txtNome.getText().equals("")){
			txtNome.setFocusable(true);
			JOptionPane.showMessageDialog(null, "Nome deve conter ao menos 1 caracter.");
			return true;
		}else if (txtIdade.getText().equals("")) {
			txtIdade.setFocusable(true);
			JOptionPane.showMessageDialog(null, "Idade deve conter ao menos 1 caracter.");
			return true;
		}else if (txtUserName.getText().equals("")) {
			txtUserName.setFocusable(true);
			JOptionPane.showMessageDialog(null, "UserName deve conter ao menos 1 caracter.");
			return true;
		}else if (txtSenhaConta.getText().equals("")) {
			txtSenhaConta.setFocusable(true);
			JOptionPane.showMessageDialog(null, "Senha Conta deve conter ao menos 1 caracter.");
			return true;
		}else if (txtSenhaOp.getText().equals("")) {
			txtSenhaOp.setFocusable(true);
			JOptionPane.showMessageDialog(null, "Senha Op deve conter ao menos 1 caracter.");
			return true;
		}else{
			return false;
		}
	}
	
	public void limparCampos(){
		txtNome.setText("");
		txtIdade.setText("");
		txtUserName.setText("");
		txtSenhaConta.setText("");
		txtSenhaOp.setText("");
	}
	
	public static void setProfissional(Profissional p1){
		p = new Profissional();
		p = p1;
	}
}

