package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.abstratas.PanelFilhoMenu;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.observable.Saldo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PagamentoCliente extends PanelFilhoMenu{
	private JTextField txtCodigoBarras;
	private JFormattedTextField txtValor;
	public PagamentoCliente() {
		
		JLabel lblCdigoDeBarras = new JLabel("C\u00F3digo de Barras");
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setColumns(10);
		
		txtValor = new JFormattedTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caracteres="0987654321.";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}	
			}
		});
		txtValor.setText("0.00");
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnConfirme = new JButton("Confirme");
		btnConfirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pagar();
				if(!Funcoes.msgConfirma("Deseja efetuar outro pagamento ?")){
					getTelaPadrao().dispose();
				}
			}
		});
		btnConfirme.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblValor = new JLabel("Valor a ser pago");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblValor)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtValor, Alignment.LEADING)
								.addComponent(txtCodigoBarras, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(lblCdigoDeBarras, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirme)))
					.addContainerGap(184, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblCdigoDeBarras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCodigoBarras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblValor)
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirme))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	protected void pagar() {
		DaoMovimentacao daoM = new DaoMovimentacao();
		daoM.pagar(txtCodigoBarras.getText(), new BigDecimal(txtValor.getText()));
		
		Saldo saldo = new Saldo();
		saldo.addObservers(getTelaPadrao());
		saldo.addObservers(getTelaMenu());
		saldo.alterarSaldo();
	}
	
	private void limparCampos(){
		txtCodigoBarras.setText("");
		txtValor.setText("0.00");
	}
}
