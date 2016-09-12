package br.univel.modelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.classes.Profissional;

public class ModeloProfissionais extends AbstractTableModel {
	
	List<Profissional> lista;

	public ModeloProfissionais(List<Profissional> lista) {
		this.lista = lista;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Profissional profissional = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return profissional.getNome();
		case 1:
			return profissional.getUsername();	
		default:
			return "erro";
		}
	}
	

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Nome";
			case 1:
				return "Username";
			default:
				return super.getColumnName(column);
		}
	}

	public Profissional getProfissional(int selecionado){
		return lista.get(selecionado);
	}
	
}
