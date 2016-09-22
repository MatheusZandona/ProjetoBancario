package br.univel.relatorios.datasource;

import java.util.Iterator;
import java.util.List;

import br.univel.classes.Conta;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ContaJRDataSource  implements JRDataSource{
	
	private List<Conta> lista;
	private Conta selecionado;
	private Iterator<Conta> iterator;	

	public ContaJRDataSource(List<Conta> lista) {
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
		
		if("TIPO".equals(jrField.getName())){
			return selecionado.getTipoConta().getSigla();
		}else if("AGENCIA".equals(jrField.getName())){		
			return selecionado.getAgencia().getNumero();
		}else if("NUMERO".equals(jrField.getName())){
			return selecionado.getNumero();	
		}else if("NOME_TITULAR".equals(jrField.getName())){
			return selecionado.getNome();
		}else if("DT_ABERTURA".equals(jrField.getName())){
			return selecionado.getDtAbertura();
		}else if("SALDO".equals(jrField.getName())){
			return selecionado.getSaldo();
		}
		
		return "Undeifined";
	}

}
