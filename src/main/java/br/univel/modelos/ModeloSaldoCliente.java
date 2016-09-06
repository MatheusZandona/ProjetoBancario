package br.univel.modelos;

import javax.swing.table.AbstractTableModel;

public class ModeloSaldoCliente extends AbstractTableModel{

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Operação";
			case 1:
				return "Data";
			case 2:
				return "Valor";				
			default:
				return super.getColumnName(column);
		}
	}	

}
