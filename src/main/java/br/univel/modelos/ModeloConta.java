package br.univel.modelos;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;
import br.univel.classes.Conta;

public class ModeloConta extends AbstractTableModel{
	
	private List<Conta> lista;
	private SimpleDateFormat formatDate;
	private NumberFormat formatNumber;

	public ModeloConta(List<Conta> lista){
		this.lista = lista;
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
		formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
	}		
	
	
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Conta conta = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return conta.getTipoConta().getSigla();
		case 1:
			return conta.getAgencia().getNumero();
		case 2:
			return conta.getNumero();
		case 3:
			return conta.getNome();
		case 4:
			if(conta.getDtAbertura() != null){
				return formatDate.format(conta.getDtAbertura());
			}else{
				return null;
			}
		case 5:
			return formatNumber.format(conta.getSaldo());			
		default:
			return "erro";
		}
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
