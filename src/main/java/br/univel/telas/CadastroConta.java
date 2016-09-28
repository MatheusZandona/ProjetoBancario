package br.univel.telas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import br.univel.classes.Hash;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.builder.ContaBuilder;
import br.univel.classes.dao.DaoAgencia;
import br.univel.classes.dao.DaoConta;
import br.univel.enuns.TipoConta;
import br.univel.funcoes.Funcoes;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;

public class CadastroConta extends PanelAbstrato{
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtCPF;
	private JTextField txtAgencia;
	private JPasswordField txtSenhaOp;
	private JPasswordField txtSenhaConta;
	private JComboBox cbbTipoConta;
	
	
	private Boolean validarCampos(){		
		
		Boolean resultado = true;
		
		//nome
		if(txtNome.getText().trim().isEmpty()){			
			Funcoes.msgAviso("Informe o nome do titular da conta.");
			resultado = false;			
		}
		
		//idade
		if(txtIdade.getText().trim().isEmpty()){			
			Funcoes.msgAviso("Informe a idade do titular da conta.");
			resultado = false;			
		}		
		
		//cpf
		if(txtCPF.getText().trim().isEmpty()){								
			Funcoes.msgAviso("Informe o CPF do titular da conta.");
			resultado = false;			
		}
		
		//agência
		if(txtAgencia.getText().trim().isEmpty()){								
			Funcoes.msgAviso("Informe a agência da conta.");
			resultado = false;			
		}		
		
		if(DaoAgencia.getInstance().buscar(txtAgencia.getText().trim()) == null){
			Funcoes.msgAviso("Agência não encontrada.");
			resultado = false;					
		}		
		
		//tipo de conta
		if(cbbTipoConta.getSelectedIndex() < 0){
			Funcoes.msgAviso("Selecione um tipo de conta.");
			resultado = false;
		}
		
		//senha de acesso
		if(txtSenhaConta.getPassword().length == 0){								
			Funcoes.msgAviso("Informe a senha de acesso da conta.");
			resultado = false;			
		}			
		
		//senha de acesso
		if(txtSenhaOp.getPassword().length == 0){								
			Funcoes.msgAviso("Informe a senha de acesso de operações.");
			resultado = false;			
		} else {
			if(txtSenhaOp.getPassword().length != 6){								
				Funcoes.msgAviso("A senha de acesso deve conter 6 dígitos.");
				resultado = false;			
			}			
		}			
		
		
		return resultado;
	}
	
	
	public CadastroConta() {
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade");
		
		txtIdade = new JTextField();
		txtIdade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}							
			}
		});
		txtIdade.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321./-";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}							
			}
		});
		txtCPF.setColumns(10);
		
		JLabel lblUsername = new JLabel("CPF");
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validarCampos()){
					String numero = "";
					
					DaoConta dao = DaoConta.getInstance();
					ContaBuilder builder = new ContaBuilder();
					
					numero = dao.gerarProximoNumConta();
					
					
					try {
						builder.setNome(txtNome.getText().toUpperCase())
							.setIdade(Integer.parseInt(txtIdade.getText()))
							.setCpf(txtCPF.getText())
							.setNumero(numero)
							.setAgencia(DaoAgencia.getInstance().buscar(txtAgencia.getText()))
							.setDtAbertura(new Date())						
							.setSenhaAcesso(new Hash().hashSHA256(txtCPF.getText().concat(txtSenhaConta.getText())))
							.setSenhaOperacoes(txtSenhaOp.getText());
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					switch(cbbTipoConta.getSelectedIndex()){
						case 0:
							builder.setTipoConta(TipoConta.CORRENTE);
							break;
						case 1:
							builder.setTipoConta(TipoConta.POUPANÇA);
							break;
						case 2:
							builder.setTipoConta(TipoConta.ELETRONICA);
							break;
						default: 
							break;	
					}
	
					dao.salvar(builder.build());									
					
					
					Funcoes.msgInforma("Conta corrente: ".concat(numero));
					
					//fechar a tela
					getTelaPadrao().dispose();
					
				}
			}
		});
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel label = new JLabel("AG:");
		
		txtAgencia = new JTextField();
		txtAgencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321-";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}							
			}
		});
		txtAgencia.setColumns(10);
		
		JLabel label_1 = new JLabel("Tipo Conta");
		
		cbbTipoConta = new JComboBox();
		cbbTipoConta.setModel(new DefaultComboBoxModel(TipoConta.values()));
		cbbTipoConta.setSelectedIndex(0);
		cbbTipoConta.setMaximumRowCount(3);
		
		txtSenhaOp = new JPasswordField();
		
		txtSenhaConta = new JPasswordField();
		
		JLabel label_2 = new JLabel("Senha opera\u00E7\u00F5es");
		
		JLabel label_3 = new JLabel("Senha Conta");
		
		JLabel lblNome_1 = new JLabel("Nome");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome_1)
						.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIdade, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSenhaConta, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtSenhaOp, Alignment.LEADING)
									.addComponent(label_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnConfirme, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(lblNome_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdade)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUsername)
							.addComponent(label)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(label_3))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSenhaOp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSenhaConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirme))
					.addGap(21))
		);
		setLayout(groupLayout);
	}
}
