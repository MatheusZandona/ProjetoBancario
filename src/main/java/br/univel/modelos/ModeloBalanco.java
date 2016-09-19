package br.univel.modelos;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import br.univel.classes.Movimentacao;

public class ModeloBalanco extends AbstractTableModel{
	
	private List<Movimentacao> lista;
	private NumberFormat formatNumber;
	

	public ModeloBalanco(List<Movimentacao> lista) {
		this.lista = lista;
		formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	}		
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Movimentacao mov = lista.get(rowIndex);
		
		switch(columnIndex) {
			case 0:
				return mov.getConta().getAgencia().getNumero();
			case 1:
				return mov.getConta().getNumero();
			case 2:
				if(mov.getTipoM().equals("D")){
					return "Depósito";
				}else if(mov.getTipoM().equals("S")){
					return "Saque";
				}else if(mov.getTipoM().equals("TS")){
					return "Transferência(Saída)";
				}else if(mov.getTipoM().equals("TE")){
					return "Transferência(Entrada)";
				}else if(mov.getTipoM().equals("PG")){
					return "Pagamento";
				}else{
					return mov.getTipoM();
				}
			case 3:
				return formatNumber.format(mov.getValor());
			default:
				return "";
		}
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
