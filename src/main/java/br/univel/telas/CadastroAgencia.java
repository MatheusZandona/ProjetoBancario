package br.univel.telas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.Agencia;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.builder.AgenciaBuilder;
import br.univel.classes.dao.DaoAgencia;
import br.univel.funcoes.Funcoes;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroAgencia extends PanelAbstrato{
	private JTextField  txtNome;
	private JTextField  txtNumero;
	private JTextField  txtCidade;
	private Boolean     editando;
	private PsqAgencias telaPesquisa;
	
	
	public PsqAgencias getTelaPesquisa() {
		return telaPesquisa;
	}


	public void setTelaPesquisa(PsqAgencias telaPesquisa) {
		this.telaPesquisa = telaPesquisa;
	}


	public Boolean getEditando() {
		return editando;
	}


	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void carregarDados(String numero){
		DaoAgencia dao = new DaoAgencia();
		Agencia agencia = new Agencia();
		
		agencia = dao.buscar(numero);
		txtNome.setText(agencia.getNome());
		txtNumero.setText(agencia.getNumero());
		txtCidade.setText(agencia.getCidade());
		txtNumero.setEditable(false);		
	}
	
	
	private boolean validarCampos() {
		Boolean resultado = true;	
		
		//nome
		if(txtNome.getText().trim().isEmpty()){			
			Funcoes.msgAviso("Informe o nome da agência.");
			resultado = false;			
		}
		
		if(txtNumero.getText().trim().isEmpty()){								
			Funcoes.msgAviso("Informe o número da agência.");
			resultado = false;			
		}
		
		if(!editando){			
			DaoAgencia dao = new DaoAgencia();
			if(dao.buscar(txtNumero.getText().trim()) != null){
				Funcoes.msgAviso("Agência já existente com o número informado.");
				resultado = false;					
			}
		} 
		
		if(txtCidade.getText().trim().isEmpty()){			
			Funcoes.msgAviso("Informe a cidade da agência.");
			resultado = false;			
		}				
		
		return resultado;
	}
	
	
	public CadastroAgencia() {
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validarCampos()){
				
					DaoAgencia dao = new DaoAgencia();
					AgenciaBuilder builder = new AgenciaBuilder();
					
					
					builder.setNumero(txtNumero.getText().toUpperCase())
							.setNome(txtNome.getText().toUpperCase())
							.setCidade(txtCidade.getText().toUpperCase());
					builder.build();		
	
					if(editando){
						dao.atualizar(builder.build());					
					}else{
						dao.salvar(builder.build());										
					}
						

					//atualizar consulta		
					telaPesquisa.montarConsulta();
					
					//fechar tela
					getTelaPadrao().dispose();
				}
			}

		});	
		
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00FAmero");
		
		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321-";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}							
			}
		});
		txtNumero.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNumero)
							.addComponent(lblNome)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtCidade, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
							.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnConfirme))
					.addContainerGap(215, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumero)
						.addComponent(lblCidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnConfirme)
					.addGap(34))
		);
		setLayout(groupLayout);
	}
}
