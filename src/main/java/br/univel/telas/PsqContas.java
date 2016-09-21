package br.univel.telas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.Conta;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.dao.DaoConta;
import br.univel.modelos.ModeloConta;
import br.univel.relatorios.ExecRelatorio;
import br.univel.relatorios.datasource.ContaJRDataSource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PsqContas extends PanelAbstrato{
	
	private JTable tbGrid;
	private List<Conta> lista = new ArrayList<Conta>();
	private DaoConta dao  = new DaoConta();
	
	
	public PsqContas() {
		
		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExecRelatorio relatorio = new ExecRelatorio();
				relatorio.executarRelatorio("contas_report.jasper", new ContaJRDataSource(lista));
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(503, Short.MAX_VALUE)
					.addComponent(btnImprimir)
					.addContainerGap())
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnImprimir)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		// $hide>>$
		montarConsulta();
		// $hide<<$			
	}
	
	private void montarConsulta() {
		lista.clear();
		lista = dao.listarTodos();
		ModeloConta modelo = new ModeloConta(lista);
		
		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela			
		
	}

}
