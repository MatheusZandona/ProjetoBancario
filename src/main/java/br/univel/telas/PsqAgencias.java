package br.univel.telas;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import br.univel.classes.Agencia;
import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.classes.dao.DaoAgencia;
import br.univel.enuns.TipoLogin;
import br.univel.funcoes.Funcoes;
import br.univel.modelos.ModeloAgencia;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PsqAgencias extends PanelAbstrato{
	
	private JTable tbGrid;
	private List<Agencia> lista = new ArrayList<Agencia>();
	private DaoAgencia dao  = DaoAgencia.getInstance();
	private CadastroAgencia telaCadastro = null;
	
	public CadastroAgencia getTelaCadastro() {
		
		if(telaCadastro == null){
			telaCadastro = new CadastroAgencia();
			telaCadastro.setTelaPesquisa(this);
			
		}
		
		return telaCadastro;
	}

	public PsqAgencias() {

		JScrollPane scrollPane = new JScrollPane();
		tbGrid = new JTable();
		tbGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tbGrid);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lista.isEmpty()){
					Funcoes.msgAviso("Nenhum registro a ser alterado.");					
				}else{					
					if(tbGrid.getSelectedRow() == -1){
						Funcoes.msgAviso("Selecione um registro para ser alterado.");
					}else{					
						CadastroAgencia panel = getTelaCadastro();
						panel.setEditando(true);						
						panel.carregarDados((String) tbGrid.getModel().getValueAt(tbGrid.getSelectedRow(), 1));
						
						TelaPadrao cadAgencia = new TelaPadrao(TipoLogin.BANCARIO, panel);
						cadAgencia.setSize(550, 450);
						cadAgencia.setLocationRelativeTo(null);
						cadAgencia.setVisible(true);
					}
				}
								
			}
		});
		
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroAgencia panel = getTelaCadastro();
				panel.setEditando(false);
				
				TelaPadrao cadAgencia = new TelaPadrao(TipoLogin.BANCARIO, panel);
				cadAgencia.setSize(550, 450);
				cadAgencia.setLocationRelativeTo(null);
				cadAgencia.setVisible(true);						
			}
		});
		
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(btnAdicionar)
							.addGap(27)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(2, Short.MAX_VALUE))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnEditar))
					.addGap(15))
		);
		
		setLayout(groupLayout);
		
		// $hide>>$
		montarConsulta();
		// $hide<<$				
	}

	public void montarConsulta() {
		lista.clear();
		lista = dao.listarTodos();
		ModeloAgencia modelo = new ModeloAgencia(lista);
		
		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela				
	}
}
