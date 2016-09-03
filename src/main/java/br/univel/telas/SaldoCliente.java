package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.univel.modelos.ModeloSaldoCliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SaldoCliente extends JPanel{
	private JTable tbGrid;
	private JTextField txtDataIini;
	private JTextField txtDataFinal;
	
	
	public SaldoCliente() {
			
		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);		
		
		JLabel lblSaldo = new JLabel("Saldo do Cliente");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnRegressaAno = new JButton("|<");
		
		JButton btnRegressaMes = new JButton("<<");
		
		JButton btnRegressaDia = new JButton("<");
		
		JButton btnAvancaDia = new JButton(">");
		
		JButton btnAvancaMes = new JButton(">>");
		
		JButton btnAvancaAno = new JButton(">|");
		
		JButton btnImprimir = new JButton("Imprimir");
		
		JLabel lblSaldoPerodo = new JLabel("Saldo Per\u00EDodo R$");
		lblSaldoPerodo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblVlrSaldo = new JLabel("0,00");
		lblVlrSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVlrSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtDataIini = new JTextField();
		txtDataIini.setEditable(false);
		txtDataIini.setText("25/08/2016");
		txtDataIini.setColumns(10);
		
		txtDataFinal = new JTextField();
		txtDataFinal.setText("25/08/2016");
		txtDataFinal.setEditable(false);
		txtDataFinal.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSaldo)
					.addContainerGap(546, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRegressaAno)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegressaMes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegressaDia, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataIini, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAvancaDia, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnAvancaMes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnAvancaAno, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSaldoPerodo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnImprimir)
						.addComponent(lblVlrSaldo, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(109))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSaldo)
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnRegressaAno)
							.addComponent(btnRegressaMes)
							.addComponent(btnRegressaDia)
							.addComponent(txtDataIini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAvancaDia)
							.addComponent(txtDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAvancaMes)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAvancaAno)
							.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVlrSaldo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSaldoPerodo))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
		// $hide>>$
		montarConsulta();
		// $hide<<$				
	}
	
	public void montarConsulta(){	
		//lista.clear();
		//lista = dp.listarTodos();		
		ModeloSaldoCliente modelo = new ModeloSaldoCliente();//instancia um modelo de tabela
		
		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela			
	}
		
}
