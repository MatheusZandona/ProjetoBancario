package br.univel.relatorios.datasource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import br.univel.classes.Movimentacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class SaldoClienteJRDataSource implements JRDataSource{
	
	private List<Movimentacao> lista;
	private Movimentacao selecionado;
	private Iterator<Movimentacao> iterator;	
	private SimpleDateFormat formatDate;
	private NumberFormat formatNumber;
	
	public SaldoClienteJRDataSource(List<Movimentacao> lista) {
		this.lista    = lista;
		this.iterator = lista.iterator();	
		this.formatDate = new SimpleDateFormat("dd/MM/yyyy");
		this.formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));	
	}

	@Override
	public boolean next() throws JRException {
		if(iterator.hasNext()){
			selecionado = iterator.next();
			return true;			
		}		
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		
		if("descricao".equals(jrField.getName())){
			return selecionado.getDescricao();			
		}else if("data".equals(jrField.getName())){		
			return formatDate.format(selecionado.getData());
		}else if("telefone".equals(jrField.getName())){
			return formatNumber.format(selecionado.getValor());			
		}
		return "Undeifined";
		
	}

}
