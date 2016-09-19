package br.univel.modelos;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import br.univel.classes.Movimentacao;

public class ModeloSaldoCliente extends AbstractTableModel{
	
	private List<Movimentacao> lista;
	private SimpleDateFormat formatDate;
	private NumberFormat formatNumber;

	
	public ModeloSaldoCliente(List<Movimentacao> lista) {
		this.lista = lista;
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
		formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		Movimentacao mov = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return mov.getDescricao();
		case 1:
			if(mov.getData() != null){
				return formatDate.format(mov.getData());
			}else{
				return null;
			}
		case 2:
			return formatNumber.format(mov.getValor());			
		default:
			return "erro";
		}
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
