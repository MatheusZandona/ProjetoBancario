package br.univel.telas;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mysql.jdbc.PreparedStatement;

import br.univel.classes.Agencia;
import br.univel.classes.Conta;
import br.univel.classes.Profissional;
import br.univel.classes.bd.ConexaoBD;
import br.univel.classes.dao.DaoAgencia;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.classes.dao.DaoProfissional;
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.DaoMov;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;

public class Login extends JFrame{
	private JTextField txtUsername;
	private JPasswordField txtSenha;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public Login() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Login");
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("");
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		
		JLabel lblSenha = new JLabel("Senha");
		
		JRadioButton RBCliente = new JRadioButton("Cliente");
		buttonGroup.add(RBCliente);
		RBCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		RBCliente.setSelected(true);
		
		JRadioButton RBFuncionario = new JRadioButton("Funcionário");
		buttonGroup.add(RBFuncionario);
		RBFuncionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(RBCliente.isSelected()){
					
					DaoConta daoC = DaoConta.getInstance();
					
					if(daoC.validarLogin(txtUsername.getText(), txtSenha.getText())){
						
						TelaPadrao.profissional = null;
						TelaPadrao.conta = daoC.buscarLogin(txtUsername.getText(), txtSenha.getText());	
						
						TelaPadrao painelCliente = new TelaPadrao(TipoLogin.CLIENTE, new PrincipalCliente());						
						painelCliente.setSize(600, 500);
						painelCliente.setLocationRelativeTo(null);
						painelCliente.setVisible(true);							

						dispose();
					}else{
						Funcoes.msgAviso("Usuário/senha não encontrados.");
					}
					
				}else{
					
					DaoProfissional daoP = DaoProfissional.getInstance();
					
					if(daoP.validarLogin(txtUsername.getText(), txtSenha.getText())){
						
						TelaPadrao.conta = null;
						TelaPadrao.profissional = daoP.buscar(txtUsername.getText());
						
						TelaPadrao painelBancario = new TelaPadrao(TipoLogin.BANCARIO, new PrincipalBancario());
						painelBancario.setSize(600, 500);
						painelBancario.setLocationRelativeTo(null);
						painelBancario.setVisible(true);

						dispose();
					}else{
						Funcoes.msgAviso("Usuário/senha não encontrados.");
					}
					
				}
					
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(127)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsurio)
								.addComponent(lblSenha)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(RBCliente)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(RBFuncionario))
									.addComponent(txtSenha, Alignment.LEADING, 157, 157, 157))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(119, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblUsurio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSenha)
					.addGap(2)
					.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(RBCliente)
						.addComponent(RBFuncionario))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnFechar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
