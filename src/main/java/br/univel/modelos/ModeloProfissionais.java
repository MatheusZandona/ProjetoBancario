package br.univel.modelos;

import javax.swing.table.AbstractTableModel;

public class ModeloProfissionais extends AbstractTableModel {

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
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

}
