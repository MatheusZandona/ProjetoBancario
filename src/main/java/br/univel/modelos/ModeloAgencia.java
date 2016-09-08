package br.univel.modelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.classes.Agencia;


public class ModeloAgencia extends AbstractTableModel{
	
	private List<Agencia> lista;
	
	public ModeloAgencia(List<Agencia> lista){
		this.lista = lista;
	}	
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Nome";
			case 1:
				return "Número";
			case 2:
				return "Cidade";
			default:
				return super.getColumnName(column);
		}
	}	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Agencia agencia = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return agencia.getNome();
		case 1:
			return agencia.getNumero();
		case 2:
			return agencia.getCidade();
		default:
			return "erro";
		}
	}

}
