package br.univel.telas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.Profissional;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.builder.ProfissionalBuilder;
import br.univel.classes.dao.DaoProfissional;
import br.univel.funcoes.Funcoes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroProfissional extends PanelAbstrato{
	
	private JTextField txtNome;
	private JTextField txtIdade;
	private JPasswordField txtSenhaOp;
	private JPasswordField txtSenhaConta;
	private JTextField txtUserName;
	private boolean editando = false; 
	private Profissional profissional;
	private PsqProfissionais telaPesquisa;


	public CadastroProfissional() {
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel label = new JLabel("Nome");
		
		JLabel label_1 = new JLabel("Idade");
		
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
		
		JLabel label_2 = new JLabel("Senha opera\u00E7\u00F5es");
		
		txtSenhaOp = new JPasswordField();
		
		txtSenhaConta = new JPasswordField();
		
		JLabel lblSenhaAcesso = new JLabel("Senha Acesso");
		
		JButton button = new JButton("Confirme");
		button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(validarCampos()){
						salvarProfissional();
						telaPesquisa.montarConsulta();
						getTelaPadrao().dispose();
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
		

	}
	
	public void carregarDados(){
		
		profissional =  new DaoProfissional().buscar(profissional.getUsername());	
		
		if(editando){
			txtNome.setText(profissional.getNome());
			txtIdade.setText(profissional.getIdade().toString());
			txtUserName.setText(profissional.getUsername());
		}		
	}
	
	private void salvarProfissional(){
	
		DaoProfissional daoP = new DaoProfissional();
		
		if(!editando){
			profissional = new Profissional();
			profissional.setId(0);
		}
		
		ProfissionalBuilder builder = new ProfissionalBuilder();
		
		builder.setId(profissional.getId()).
				setIdade(Integer.parseInt(txtIdade.getText())).
				setNome(txtNome.getText()).
				setSenhaAcesso(txtSenhaConta.getText()). 
				setSenhaOperacoes(txtSenhaOp.getText()). 
				setUsername(txtUserName.getText());				
				
		
		if(editando){
			daoP.atualizar(builder.build());
		}else{
			daoP.salvar(builder.build());
		}
	}

		
	public boolean validarCampos(){  
		boolean temNumero = false;
		boolean temLetra = false;
		
		//verifica se tem letra e numero		
		if(!txtSenhaConta.getText().isEmpty()){
			
			char[] caracter = txtSenhaConta.getText().toCharArray();

			for(int x = 0; x < caracter.length; x++){
				
				if((!temNumero) && (Character.isDigit(caracter[x]))){
					temNumero = true;
				}
				
				if((!temLetra) && (Character.isAlphabetic(caracter[x]))){
					temLetra = true;
				}
				
				if(temLetra && temNumero){
					break;
				}
			}
		}
		
		
		if(txtNome.getText().equals("")){
			txtNome.setFocusable(true);
			Funcoes.msgAviso("Nome é obrigatório.");
			return false;
		}else if (txtIdade.getText().equals("")) {
			txtIdade.setFocusable(true);
			Funcoes.msgAviso("Idade é obrigatória.");
			return false;
		}else if (txtUserName.getText().equals("")) {
			txtUserName.setFocusable(true);
			Funcoes.msgAviso("UserName é obrigatório.");
			return false;
		}else if (DaoProfissional.getInstance().buscar(txtUserName.getText()) != null) {
			Funcoes.msgAviso("Username já se encontra em uso. Favor informar outro username.");
			txtUserName.setFocusable(true);
			return false;
		}else if (txtSenhaConta.getText().equals("")) {
			txtSenhaConta.setFocusable(true);
			Funcoes.msgAviso("Senha de acesso é obrigatório.");
			return false;
		}else if (!temNumero) {
			txtSenhaConta.setFocusable(true);
			Funcoes.msgAviso("Senha de acesso deve conter ao menos um número.");
			return false;
		}else if (!temLetra) {
			txtSenhaConta.setFocusable(true);
			Funcoes.msgAviso("Senha de acesso deve conter ao menos uma letra.");
			return false;
		}else if (txtSenhaOp.getText().length() != 8) {
			txtSenhaOp.setFocusable(true);
			Funcoes.msgAviso("Senha Operações deve conter 8 digitos.");
			return false;
		}else{
			return true;
		}
	}
		
	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}		
		
	public void setTelaPesquisa(PsqProfissionais telaPesquisa) {
		this.telaPesquisa = telaPesquisa;
	}	
}

