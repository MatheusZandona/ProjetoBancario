package br.univel.relatorios.datasource;

import java.util.Iterator;
import java.util.List;

import br.univel.classes.Movimentacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class SaldoClienteJRDataSource implements JRDataSource{
	
	private List<Movimentacao> lista;
	private Movimentacao selecionado;
	private Iterator<Movimentacao> iterator;	
	
	public SaldoClienteJRDataSource(List<Movimentacao> lista) {
		this.lista    = lista;
		this.iterator = lista.iterator();		
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
			return selecionado.getData();
		}else if("valor".equals(jrField.getName())){
			return selecionado.getValor();			
		}
		return "Undeifined";
		
	}

}
