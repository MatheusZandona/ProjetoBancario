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
import br.univel.enuns.TipoConta;
import br.univel.enuns.TipoLogin;

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
		
		JRadioButton RBFuncionario = new JRadioButton("Funcionario");
		buttonGroup.add(RBFuncionario);
		RBFuncionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement ps;
					ResultSet result;
					if(RBCliente.isSelected()){
						ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
								.clientPrepareStatement("SELECT * FROM CONTAS WHERE CPF = ? AND SENHA_ACESSO = ?");
						ps.setString(1, txtUsername.getText());
						ps.setString(2, txtSenha.getText());
						result =  ps.executeQuery();
						if(result.next()){
							TelaPadrao.conta = new Conta();
							TelaPadrao.conta.setNumero(result.getString("numero"));
							TelaPadrao.conta.setNome(result.getString("nome_titular"));
							TelaPadrao.conta.setIdade(result.getInt("idade"));
							TelaPadrao.conta.setSenhaAcesso(result.getString("senha_acesso"));
							TelaPadrao.conta.setSenhaOperacoes(result.getString("senha_op"));
							
							Agencia ag = new Agencia();
							DaoAgencia daoAG = new DaoAgencia();
							ag = daoAG.buscar(result.getString("agencia"));
							TelaPadrao.conta.setAgencia(ag);
							
							TelaPadrao.conta.setCpf(result.getString("cpf"));
							TelaPadrao.conta.setDtAbertura(result.getDate("dt_abertura"));
							TelaPadrao.conta.setSaldo(new BigDecimal(result.getDouble("saldo")));
							if(TelaPadrao.conta.getCpf().equals(txtUsername.getText()) &&
							   TelaPadrao.conta.getSenhaAcesso().equals(txtSenha.getText())){
								TelaPadrao painelCliente = new TelaPadrao(TipoLogin.CLIENTE, new PrincipalCliente(TipoConta.CORRENTE));
								painelCliente.setSize(600, 500);
								painelCliente.setLocationRelativeTo(null);
								painelCliente.setVisible(true);
							}else{
								JOptionPane.showInternalMessageDialog(null, "Usuário e senha inválidos.");
							}
						}
					}else{
						ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
												.clientPrepareStatement("SELECT * FROM PROFISSIONAIS WHERE USERNAME = ? AND SENHA_ACESSO = ?");
						ps.setString(1, txtUsername.getText());
						ps.setString(2, txtSenha.getText());
						
						result =  ps.executeQuery();
						if(result.next()){
							TelaPadrao.profissional = new Profissional();
							TelaPadrao.profissional.setUsername(result.getString("username"));
							TelaPadrao.profissional.setNome(result.getString("nome"));
							TelaPadrao.profissional.setIdade(result.getInt("idade"));
							TelaPadrao.profissional.setSenhaAcesso(result.getString("senha_acesso"));
							TelaPadrao.profissional.setSenhaOperacoes(result.getString("senha_op"));
							if(TelaPadrao.profissional.getUsername().equals(txtUsername.getText()) &&
							   TelaPadrao.profissional.getSenhaAcesso().equals(txtSenha.getText())){
								TelaPadrao painelBancario = new TelaPadrao(TipoLogin.BANCARIO, new PrincipalBancario());
								painelBancario.setSize(600, 500);
								painelBancario.setLocationRelativeTo(null);
								painelBancario.setVisible(true);
							}else{
								JOptionPane.showInternalMessageDialog(null, "Usuário e senha inválidos.");
							}
						}
					}
					
					ps.close();
					result.close();
				} catch (Exception e1) {
					e1.printStackTrace();
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
