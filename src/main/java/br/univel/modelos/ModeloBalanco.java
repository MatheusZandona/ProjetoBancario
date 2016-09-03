package br.univel.modelos;

import javax.swing.table.AbstractTableModel;

public class ModeloBalanco extends AbstractTableModel{

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "AG";
			case 1:
				return "Conta";
			case 2:
				return "Operação";
			case 3:
				return "Valor";
			default:
				return super.getColumnName(column);
		}
	}
	

}
