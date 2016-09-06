package br.univel.modelos;

import javax.swing.table.AbstractTableModel;


public class ModeloAgencia extends AbstractTableModel{
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return 3;
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
	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

}
