package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class Balanco extends JPanel{
	private JTextField txtDataIni;
	private JTextField txtDataFim;
	private JTable tbGrid;
	public Balanco() {
		
		JButton btnRegressaAno = new JButton("|<");
		
		JButton btnRegressaMes = new JButton("<<");
		
		JButton btnRegressaDia = new JButton("<");
		
		txtDataIni = new JTextField();
		txtDataIni.setText("25/08/2016");
		txtDataIni.setEditable(false);
		txtDataIni.setColumns(10);
		
		txtDataFim = new JTextField();
		txtDataFim.setText("25/08/2016");
		txtDataFim.setEditable(false);
		txtDataFim.setColumns(10);
		
		JButton btnAvancaDia = new JButton(">");
		
		JButton btnAvancaMes = new JButton(">>");
		
		JButton btnAvancaAno = new JButton(">|");
		
		JButton btnImprimir = new JButton("Imprimir");
		
		JLabel lblsaldoPerodoR = new JLabel("(=)Saldo Per\u00EDodo R$");
		lblsaldoPerodoR.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblSaldo = new JLabel("0,00");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tbGrid = new JTable();
		
		JLabel lblTotalDepositos = new JLabel("0,00");
		lblTotalDepositos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalDepositos.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblTotalDepsitos = new JLabel("(+) Total Dep\u00F3sitos R$");
		lblTotalDepsitos.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblTotalSaques = new JLabel("0,00");
		lblTotalSaques.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSaques.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lbltotalSaquesR = new JLabel("(-)Total Saques R$");
		lbltotalSaquesR.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel = new JLabel("Balan\u00E7o");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JComboBox cbbAgencia = new JComboBox();
		
		JLabel lblAg = new JLabel("AG:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tbGrid, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRegressaAno, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRegressaMes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRegressaDia, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(txtDataIni, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(txtDataFim, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnAvancaDia, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTotalDepsitos)
								.addComponent(lblsaldoPerodoR)
								.addComponent(lbltotalSaquesR))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAvancaMes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnAvancaAno, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(24)
									.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTotalDepositos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSaldo, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotalSaques, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(124, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
							.addComponent(lblAg)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbbAgencia, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbbAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAg))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tbGrid, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegressaAno)
						.addComponent(btnRegressaMes)
						.addComponent(btnRegressaDia)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(txtDataIni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(txtDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAvancaDia)
						.addComponent(btnAvancaMes)
						.addComponent(btnAvancaAno)
						.addComponent(btnImprimir))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalDepsitos, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalDepositos, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbltotalSaquesR, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalSaques, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblsaldoPerodoR, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaldo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		setLayout(groupLayout);
	}
}
