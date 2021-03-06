package br.univel.telas;


import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;

import br.univel.classes.Movimentacao;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.modelos.ModeloSaldoCliente;
import br.univel.relatorios.ExecRelatorio;
import br.univel.relatorios.datasource.SaldoClienteJRDataSource;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class SaldoCliente extends PanelAbstrato{
	private JTable tbGrid;
	private JTextField txtDataIni;
	private JTextField txtDataFim;
	private Date       dataInicial ;
	private Date       dataFinal;  
	private SimpleDateFormat format; 	
	private Calendar   calendar;
	private List<Movimentacao> lista = new ArrayList<Movimentacao>();
	private DaoMovimentacao dao  = DaoMovimentacao.getInstance();
	private NumberFormat formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	private JLabel lblVlrSaldo;
	
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

	public SaldoCliente() {
			
		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);		
		
		JLabel lblSaldo = new JLabel("Saldo do Cliente");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
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
				montarConsulta();
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
				montarConsulta();
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
				montarConsulta();
			}
		});
		
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
				montarConsulta();
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
				montarConsulta();
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
				montarConsulta();
							
			}
		});
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExecRelatorio relatorio = new ExecRelatorio();
				Map parameters = new HashMap();
				parameters.put("periodo", "Período ".concat(txtDataIni.getText()).concat(" a ").concat(txtDataFim.getText()));
				parameters.put("agencia", "Agência: ".concat(TelaPadrao.conta.getAgencia().getNumero()));
				parameters.put("conta", "Conta: ".concat(TelaPadrao.conta.getNumero()));
				parameters.put("saldo", "Saldo: ".concat(TelaPadrao.conta.getSaldo().toString()));
				
				relatorio.executarRelatorio("saldoCliente_report.jasper", parameters, new SaldoClienteJRDataSource(lista));				
			}
		});
		
		JLabel lblSaldoPerodo = new JLabel("Saldo Per\u00EDodo ");
		lblSaldoPerodo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblVlrSaldo = new JLabel("0,00");
		lblVlrSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVlrSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtDataIni = new JTextField();
		txtDataIni.setEditable(false);
		txtDataIni.setColumns(10);
		
		txtDataFim = new JTextField();
		txtDataFim.setEditable(false);
		txtDataFim.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSaldo)
					.addContainerGap(546, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRegressaAno)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegressaMes)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegressaDia, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataIni, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataFim, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAvancaDia, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnAvancaMes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnAvancaAno, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(374)
							.addComponent(lblSaldoPerodo)))
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
							.addComponent(txtDataIni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAvancaDia)
							.addComponent(txtDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		format    = new SimpleDateFormat("dd/MM/yyyy"); 		
		calendar  = Calendar.getInstance();
		
		setDataInicial(new Date(System.currentTimeMillis()));
		setDataFinal(new Date(System.currentTimeMillis()));								
		
		montarConsulta();
		// $hide<<$				
	}
	
	public void montarConsulta(){	
		lista.clear();
		lista = dao.listarOperacoesConta(this.dataInicial, this.dataFinal);		
		ModeloSaldoCliente modelo = new ModeloSaldoCliente(lista);//instancia um modelo de tabela

		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela
		
		//atualiza total
		BigDecimal saldo = BigDecimal.ZERO;
		for (Movimentacao movimentacao : lista) {
			saldo = saldo.add(movimentacao.getValor());
		}
		
		lblVlrSaldo.setText(formatNumber.format(saldo));
	}
		
}
