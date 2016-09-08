package br.univel.telas;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import br.univel.enuns.TipoLogin;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.CardLayout;

public class TelaPadrao extends JFrame{
	
	private JLabel lblData;
	private JLabel lblNroAgencia;
	private JLabel lblTipoConta;
	private JLabel lblNroConta;
	private JLabel lblVlrSaldo;
	private JLabel lblAg;
	private JLabel lblSaldo;
	private TipoLogin tipoLogin;
	
	public TelaPadrao(TipoLogin tipoLogin, JPanel panel) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Banco Tads");
		
		JLabel lblBancoTads = new JLabel("BANCO TADS");
		lblBancoTads.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblSlogan = new JLabel("Aqui sua nota n\u00E3o vai ser zero");
		
		JPanel pnlSeparador = new JPanel();
		pnlSeparador.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		lblNroAgencia = new JLabel("0000-00");
		
		lblTipoConta = new JLabel("Tipo Conta:");
		
		lblNroConta = new JLabel("00000-00");
		
		lblVlrSaldo = new JLabel("R$ 0,00");
		
		lblSaldo = new JLabel("Saldo:");
		
		lblAg = new JLabel("AG:");
		
		lblData = new JLabel("01/08/2016 - 20:00");
		
		JLabel lblBt = new JLabel("BT");
		lblBt.setBackground(Color.LIGHT_GRAY);
		lblBt.setHorizontalAlignment(SwingConstants.CENTER);
		lblBt.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 33));
		
		JPanel pnlPrincipal = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlPrincipal, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(pnlSeparador, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBt, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSlogan)
								.addComponent(lblBancoTads))
							.addPreferredGap(ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblData)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSaldo)
										.addComponent(lblTipoConta)
										.addComponent(lblAg))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNroAgencia, Alignment.TRAILING)
										.addComponent(lblNroConta, Alignment.TRAILING)
										.addComponent(lblVlrSaldo, Alignment.TRAILING))))
							.addGap(66))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNroAgencia)
								.addComponent(lblAg))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNroConta)
								.addComponent(lblTipoConta)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblBt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblBancoTads)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblSlogan))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVlrSaldo)
						.addComponent(lblSaldo))
					.addGap(8)
					.addComponent(pnlSeparador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlPrincipal, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addContainerGap())
		);
		pnlPrincipal.setLayout(new CardLayout(0, 0));
		pnlPrincipal.add(panel);
		getContentPane().setLayout(groupLayout);
		
		setTipoLogin(tipoLogin);
	}

	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(TipoLogin tipoLogin) {
		this.tipoLogin = tipoLogin;
		
		if(this.tipoLogin == TipoLogin.BANCARIO){
			lblData.setVisible(true); 
			lblAg.setVisible(false);
			lblNroAgencia.setVisible(false);
			lblNroConta.setVisible(false);
			lblSaldo.setVisible(false);
			lblTipoConta.setVisible(false);
			lblVlrSaldo.setVisible(false);
		}else{
			lblData.setVisible(true); 
			lblAg.setVisible(true);
			lblNroAgencia.setVisible(true);
			lblNroConta.setVisible(true);
			lblSaldo.setVisible(true);
			lblTipoConta.setVisible(true);
			lblVlrSaldo.setVisible(true);			
		}
		
		lblData.setText(new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date(System.currentTimeMillis())));
	}
}
