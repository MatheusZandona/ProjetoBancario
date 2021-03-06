package br.univel.telas;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import br.univel.classes.Conta;
import br.univel.classes.abstratas.PanelFilhoMenu;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.command.MovimentarConta;
import br.univel.command.TransferenciaCommand;
import br.univel.enuns.TipoLogin;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.MovimentacaoCommand;
import br.univel.observable.Saldo;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransferenciaCliente extends PanelFilhoMenu{
	private JTextField txtAgencia;
	private JTextField txtConta;
	private JTextField txtTitular;
	private JFormattedTextField txtValor;
	
	
	public TransferenciaCliente() {
		
		JLabel lblContaDeDestinocrdito = new JLabel("Conta de destino/Cr\u00E9dito");
		lblContaDeDestinocrdito.setFont(new Font("Tahoma", Font.BOLD, 12));
		
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
		
		JComboBox cbbTipoConta = new JComboBox();
		cbbTipoConta.setModel(new DefaultComboBoxModel(new String[] {"Conta Corrente", "Conta Poupan\u00E7a", "Conta Eletr\u00F4nica"}));
		cbbTipoConta.setSelectedIndex(0);
		cbbTipoConta.setMaximumRowCount(3);
		
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
		txtConta.setColumns(10);
		
		JLabel label_3 = new JLabel("Titular");
		
		txtTitular = new JTextField();
		txtTitular.setColumns(10);
		
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
		txtValor.setText("0.00");
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblValor = new JLabel("Valor");
		
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
		
		JLabel lblConta = new JLabel("Conta");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConfirme, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblContaDeDestinocrdito, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addGap(303))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblConta)
										.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblContaDeDestinocrdito, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(lblConta)
						.addComponent(label_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtTitular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(lblValor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbbTipoConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnConfirme)
					.addGap(20))
		);
		setLayout(groupLayout);
	}
		
	public void limparCampos(){
		txtAgencia.setText("");
		txtConta.setText("");
		txtValor.setText("0.00");
		txtTitular.setText("");
		setOperacaoAprovada(false);
	}
	
	private void confirmaOperacao(){
		TecladoSenhaCliente  teclado = new TecladoSenhaCliente(this);
		teclado.setSize(540, 200);
		teclado.setLocationRelativeTo(null);
		teclado.setVisible(true);
		
		if(isOperacaoAprovada()){
			if(transferir()){
				TelaPadrao telaConfirma = new TelaPadrao(TipoLogin.CLIENTE, new ConfirmaOperacao(this.getTelaPadrao(), TipoMovimentacao.TRANSFERENCIA, new BigDecimal(txtValor.getText())));
				telaConfirma.setSize(600, 450);
				telaConfirma.setLocationRelativeTo(null);
				telaConfirma.setVisible(true);
				
			}			
		}else{
			Funcoes.msgAviso("Não foi possível realizar a transferência devido a falta de confirmação.");
		}
	}		
	
	private boolean transferir(){
		DaoMovimentacao daoMov =  DaoMovimentacao.getInstance();
		boolean resultado = false;
		Conta contaDest = DaoConta.getInstance().buscar(txtConta.getText());
		
		MovimentacaoCommand command = new TransferenciaCommand(TelaPadrao.conta, contaDest, new BigDecimal(txtValor.getText()));
		resultado = new MovimentarConta(command).executaAcao();
		
		
		Saldo saldo = new Saldo();
		saldo.addObservers(getTelaPadrao());
		saldo.addObservers(getTelaMenu());
		saldo.alterarSaldo();
		
		return resultado;
	}		
}
