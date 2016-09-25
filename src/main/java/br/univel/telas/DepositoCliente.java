package br.univel.telas;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.abstratas.PanelFilhoMenu;
import br.univel.classes.dao.DaoAgencia;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoLogin;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.observable.Saldo;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DepositoCliente extends PanelFilhoMenu{
	private JTextField txtAgencia;
	private JTextField txtConta;
	private JTextField txtTitular;
	private JComboBox cbbTipoConta;
	private JFormattedTextField txtValor;
	private JCheckBox chkContaLogada;
	
	public DepositoCliente() {
		
		JLabel lblNewLabel = new JLabel("Informe o valor a ser depositado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		chkContaLogada = new JCheckBox("Conta logada");
		chkContaLogada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chkContaLogada.isSelected()){
					setDestLogado();
					desabilitaCampos();
				}else{
					limparCampos();
					habilitaCampos();
				}
			}
		});
		chkContaLogada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkContaLogada.setSelected(true);
		
		txtValor = new JFormattedTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321.";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}				
			}
		});
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setText("0.00");
		
		JLabel lblAg = new JLabel("AG:");
		
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
		txtAgencia.setEditable(false);
		txtAgencia.setColumns(10);
		
		txtConta = new JTextField();
		txtConta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres="0987654321-";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}						
			}
		});
		txtConta.setEditable(false);
		txtConta.setColumns(10);
		
		JLabel lblConta = new JLabel("Conta");
		
		JLabel lblTipoConta = new JLabel("Tipo Conta");
		
		cbbTipoConta = new JComboBox();
		cbbTipoConta.setMaximumRowCount(3);
		cbbTipoConta.setModel(new DefaultComboBoxModel(new String[] {"Conta Corrente", "Conta Poupan\u00E7a", "Conta Eletr\u00F4nica"}));
		cbbTipoConta.setSelectedIndex(0);
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BigDecimal valor = new BigDecimal(txtValor.getText());
				
				if(valor.compareTo(new BigDecimal(0.00)) > 0){
					confirmaOperacao();
				}else{
					Funcoes.msgAviso("Valor inválido.");
				}				
			}
		});
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblTitular = new JLabel("Titular");
		
		txtTitular = new JTextField();
		txtTitular.setEditable(false);
		txtTitular.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(chkContaLogada)
						.addComponent(lblTipoConta)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(cbbTipoConta, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAg))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblConta, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtTitular, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
									.addComponent(btnConfirme)))
							.addGap(58))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitular)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirme))
					.addGap(18)
					.addComponent(chkContaLogada)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAg)
						.addComponent(lblConta)
						.addComponent(lblTitular))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTipoConta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setDestLogado();
		desabilitaCampos();
	}
	
	private void limparCampos(){
		txtAgencia.setText("");
		txtConta.setText("");
		txtTitular.setText("");
		txtValor.setText("0.00");
		cbbTipoConta.setSelectedIndex(-1);
		this.setOperacaoAprovada(false);
	}
	
	private void setDestLogado(){
		txtAgencia.setText(TelaPadrao.conta.getAgencia().getNumero());
		txtConta.setText(TelaPadrao.conta.getNumero());
		txtTitular.setText(TelaPadrao.conta.getNome());
		cbbTipoConta.setSelectedIndex(TelaPadrao.conta.getTipoConta().ordinal());
	}
	
	private void desabilitaCampos(){
		txtAgencia.setEditable(false);
		txtConta.setEditable(false);
		txtTitular.setEditable(false);
		cbbTipoConta.setEnabled(false);
	}
	
	private void habilitaCampos(){
		txtAgencia.setEditable(true);
		txtConta.setEditable(true);
		txtTitular.setEditable(true);
		cbbTipoConta.setEnabled(true);
	}
	
	private void confirmaOperacao(){
		TecladoSenhaCliente  teclado = new TecladoSenhaCliente(this);
		teclado.setSize(540, 200);
		teclado.setLocationRelativeTo(null);
		teclado.setVisible(true);
		
		if(isOperacaoAprovada()){
			if(depositar()){
				TelaPadrao telaConfirma = new TelaPadrao(TipoLogin.CLIENTE, new ConfirmaOperacao(this.getTelaPadrao(), TipoMovimentacao.DEPOSITO, new BigDecimal(txtValor.getText())));
				telaConfirma.setSize(600, 450);
				telaConfirma.setLocationRelativeTo(null);
				telaConfirma.setVisible(true);								
			}
			
		}else{
			Funcoes.msgAviso("Não foi possível realizar o depósito devido a falta de confirmação.");
		}
	}		
	
	private boolean depositar(){
		DaoMovimentacao daoMov =  new DaoMovimentacao();
		boolean resultado = false;
		
		if(chkContaLogada.isSelected()){					
			resultado = daoMov.depositar(new BigDecimal(txtValor.getText()), TelaPadrao.conta, TelaPadrao.conta.getAgencia());
			
		}else{
			if(!txtConta.getText().equals("") && !txtAgencia.getText().equals("")){
				resultado = daoMov.depositar(new BigDecimal(txtValor.getText()), new DaoConta().buscar(txtConta.getText()), new DaoAgencia().buscar(txtAgencia.getText()));
			}else{
				Funcoes.msgAviso("É necessário informar conta e agência.");
			}
		}
		
		Saldo saldo = new Saldo();
		saldo.addObservers(getTelaPadrao());
		saldo.addObservers(getTelaMenu());
		saldo.alterarSaldo();
		
		return resultado;
	}
	
}
