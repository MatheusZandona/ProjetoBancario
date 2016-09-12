package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import br.univel.modelos.ModeloBalanco;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Balanco extends JPanel{
	private JTextField txtDataIni;
	private JTextField txtDataFim;
	private JTable     tbGrid;
	private Date       dataInicial ;
	private Date       dataFinal;  
	private SimpleDateFormat format; 	
	private Calendar 		 calendar;
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
		txtDataIni.setText(format.format(this.dataInicial));
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {		
		this.dataFinal = dataFinal;
		txtDataFim.setText(format.format(this.dataFinal));		
	}

	public Balanco() {
		
		JButton btnRegressaAno = new JButton("|<");
		btnRegressaAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				try {
					calendar.setTime(format.parse(txtDataIni.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.YEAR, -1);
				
				dataInicial = calendar.getTime();				
				setDataInicial(dataInicial);			
			}
		});
		
		JButton btnRegressaMes = new JButton("<<");
		btnRegressaMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calendar.setTime(format.parse(txtDataIni.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.MONTH, -1);
				
				dataInicial = calendar.getTime();				
				setDataInicial(dataInicial);						
			}
		});
		
		JButton btnRegressaDia = new JButton("<");
		btnRegressaDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calendar.setTime(format.parse(txtDataIni.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.DAY_OF_MONTH, -1);
				
				dataInicial = calendar.getTime();				
				setDataInicial(dataInicial);								
		
			}
		});
						
		
		txtDataIni = new JTextField();
		txtDataIni.setEditable(false);
		txtDataIni.setColumns(10);
		
		txtDataFim = new JTextField();
		txtDataFim.setEditable(false);
		txtDataFim.setColumns(10);
		
		JButton btnAvancaDia = new JButton(">");
		btnAvancaDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					calendar.setTime(format.parse(txtDataFim.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.DAY_OF_MONTH, 1);
				
				dataFinal = calendar.getTime();				
				setDataFinal(dataFinal);								
			}
		});
		
		JButton btnAvancaMes = new JButton(">>");
		btnAvancaMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					calendar.setTime(format.parse(txtDataFim.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.MONTH, 1);
				
				dataFinal = calendar.getTime();				
				setDataFinal(dataFinal);								
			}
		});
		
		JButton btnAvancaAno = new JButton(">|");
		btnAvancaAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calendar.setTime(format.parse(txtDataFim.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				calendar.add(calendar.YEAR, 1);
				
				dataFinal = calendar.getTime();				
				setDataFinal(dataFinal);								
			
			}
		});
		
		JButton btnImprimir = new JButton("Imprimir");
		
		JLabel lblsaldoPerodoR = new JLabel("(=)Saldo Per\u00EDodo R$");
		lblsaldoPerodoR.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblSaldo = new JLabel("0,00");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);
		
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
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
									.addGap(35)
									.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTotalDepositos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSaldo, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotalSaques, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))))
							.addGap(76))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
							.addComponent(lblAg)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbbAgencia, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbbAgencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAg)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
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
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAvancaAno)
							.addComponent(btnImprimir)))
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
	
		
		// $hide>>$		
		format    = new SimpleDateFormat("dd/MM/yyyy"); 		
		calendar  = Calendar.getInstance();
		
		setDataInicial(new Date(System.currentTimeMillis()));
		setDataFinal(new Date(System.currentTimeMillis()));						
		
		montarConsulta();
		// $hide<<$			
	}
	
	private void montarConsulta() {
		//lista.clear();
		//lista = dp.listarTodos();
		//ModeloProduto modelo = new ModeloProduto(lista);//instancia um modelo de tabela
		ModeloBalanco modelo = new ModeloBalanco();
		
		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela			
		
	}
}
