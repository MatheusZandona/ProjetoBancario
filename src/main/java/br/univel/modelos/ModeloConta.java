package br.univel.modelos;

import javax.swing.table.AbstractTableModel;

public class ModeloConta extends AbstractTableModel{

	@Override
	public int getColumnCount() {
		return 6;
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
				return "Tipo Conta";
			case 1:
				return "AG";
			case 2:
				return "Número";
			case 3:
				return "Titular";
			case 4:
				return "Aberta em";
			case 5:
				return "Saldo";
			default:
				return super.getColumnName(column);
		}
	}		

}
