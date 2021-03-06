package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import br.univel.classes.Profissional;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.dao.DaoProfissional;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
import br.univel.modelos.ModeloProfissionais;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PsqProfissionais extends PanelAbstrato{
	
	private JTable tbGrid;
	private List<Profissional> lista = new ArrayList<Profissional>();
	private DaoProfissional dao  = DaoProfissional.getInstance();
	private CadastroProfissional telaCadastro = null;
	
	public CadastroProfissional getTelaCadastro() {		
		
		if(telaCadastro == null){
			telaCadastro = new CadastroProfissional();
			telaCadastro.setTelaPesquisa(this);			
		}		
		
		return telaCadastro;
	}
	
	public PsqProfissionais() {
		
		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastroProfissional panel = getTelaCadastro();
				panel.setEditando(false);
				
				
				TelaPadrao cadProfissional = new TelaPadrao(TipoLogin.BANCARIO, panel);
				cadProfissional.setSize(550, 450);
				cadProfissional.setLocationRelativeTo(null);
				cadProfissional.setVisible(true);					
			}
		});
		
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lista.isEmpty()){
					Funcoes.msgAviso("Nenhum registro a ser alterado.");					
				}else{		
				
					int selec = tbGrid.getSelectedRow();	
				
				
					if(selec != -1){
						Profissional prof = ((ModeloProfissionais) tbGrid.getModel()).getProfissional(selec);
						
						CadastroProfissional panel = getTelaCadastro();
						panel.setEditando(true);
						panel.setProfissional(prof);
						panel.carregarDados();
						
						TelaPadrao cadProfissional = new TelaPadrao(TipoLogin.BANCARIO, panel);						
						cadProfissional.setSize(550, 450);
						cadProfissional.setLocationRelativeTo(null);
						cadProfissional.setVisible(true);
						
					}else{
						Funcoes.msgAviso("Selecione um registro para ser alterado.");
					}
				}
			}
		});
		
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(385, Short.MAX_VALUE))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnEditar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		// $hide>>$
		montarConsulta();
		// $hide<<$				
	}
	
	public void montarConsulta() {
		lista.clear();
		lista = dao.listarTodos();
		ModeloProfissionais modelo = new ModeloProfissionais(lista);
		
		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela				
	}
}
