package br.univel.telas;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.abstratas.PanelFilhoMenu;
import br.univel.classes.dao.DaoMovimentacao;
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

public class DepositoCliente extends PanelFilhoMenu{
	private JTextField txtAgencia;
	private JTextField txtConta;
	private JTextField txtTitular;
	private JComboBox cbbTipoConta;
	JFormattedTextField txtValor;
	
	private TelaPadrao tp;
	
	public void setTp(TelaPadrao tp) {
		this.tp = tp;
	}
	
	public DepositoCliente() {
		
		JLabel lblNewLabel = new JLabel("Informe o valor a ser depositado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JCheckBox chkContaLogada = new JCheckBox("Conta logada");
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
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setText("0,00");
		
		JLabel lblAg = new JLabel("AG:");
		
		txtAgencia = new JTextField();
		txtAgencia.setEditable(false);
		txtAgencia.setColumns(10);
		
		txtConta = new JTextField();
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
				DaoMovimentacao daoMov =  new DaoMovimentacao();
				if(chkContaLogada.isSelected()){					
					daoMov.depositar(new BigDecimal(txtValor.getText()), TelaPadrao.conta.getNumero(), TelaPadrao.conta.getAgencia().getNumero());
					
				}else{
					if(!txtConta.getText().equals("") && !txtAgencia.getText().equals("")){
						daoMov.depositar(new BigDecimal(txtValor.getText()), txtConta.getText(), txtAgencia.getText());
						getTelaPadrao().dispose();
						
						Saldo saldo = new Saldo();
						saldo.addObservers(getTelaPadrao());
						saldo.addObservers(getTelaMenu());
						saldo.alterarSaldo();
					}else{
						Funcoes.msgAviso("É necessário informar conta e agência.");
					}
				}
				
				limparCampos();
				
				Saldo saldo = new Saldo();
				saldo.addObservers(getTelaPadrao());
				saldo.addObservers(getTelaMenu());
				saldo.alterarSaldo();
				
				if(!Funcoes.msgConfirma("Deseja efetuar outro depósito ?")){
					getTelaPadrao().dispose();
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
	}
	
	private void limparCampos(){
		txtAgencia.setText("");
		txtConta.setText("");
		txtTitular.setText("");
		txtValor.setText("0.00");
		cbbTipoConta.setSelectedIndex(-1);
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
	
}
