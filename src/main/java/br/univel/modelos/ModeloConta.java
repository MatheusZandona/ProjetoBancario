package br.univel.modelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import br.univel.classes.Conta;

public class ModeloConta extends AbstractTableModel{
	
	private List<Conta> lista;

	public ModeloConta(List<Conta> lista){
		this.lista = lista;
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
			return conta.getDtAbertura().toString();	
		case 5:
			return conta.getSaldo().toString();			
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
