package br.univel.relatorios.datasource;

import java.util.Iterator;
import java.util.List;

import br.univel.classes.Movimentacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class BalancoJRDataSource implements JRDataSource{

	private List<Movimentacao> lista;
	private Movimentacao selecionado;
	private Iterator<Movimentacao> iterator;
	
	public BalancoJRDataSource(List<Movimentacao> lista){
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
		
		if("agencia".equals(jrField.getName())){
			return selecionado.getConta().getAgencia().getNumero();
			
		}else if("conta".equals(jrField.getName())){		
			return selecionado.getConta().getNumero();
			
		}else if("operacao".equals(jrField.getName())){
			
			if(selecionado.getTipoM().equals("D")){
				return "Depósito";
			}else if(selecionado.getTipoM().equals("S")){
				return "Saque";
			}else if(selecionado.getTipoM().equals("TS")){
				return "Transferência(Saída)";
			}else if(selecionado.getTipoM().equals("TE")){
				return "Transferência(Entrada)";
			}else if(selecionado.getTipoM().equals("PG")){
				return "Pagamento";
			}else{
				return selecionado.getTipoM();
			}		
		}else if("valor".equals(jrField.getName())){
			return selecionado.getValor();
		}
		
		return "Undeifined";
	}

}
