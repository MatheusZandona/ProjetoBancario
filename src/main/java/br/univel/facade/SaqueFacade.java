package br.univel.facade;

import java.math.BigDecimal;

import br.univel.classes.Movimentacao;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.MovimentacaoFacade;
import br.univel.telas.TelaPadrao;

public class SaqueFacade implements MovimentacaoFacade{
	
	private Movimentacao movimentacao;	
	private BigDecimal valor;
	private DaoMovimentacao dao = DaoMovimentacao.getInstance();
	
	public SaqueFacade(BigDecimal valor) {
		this.valor = valor;
	}	
	
	@Override
	public void criarMovimentacao() {
		this.movimentacao = new Movimentacao(TelaPadrao.conta, getSiglaMov(), this.valor, getDescricao());
	}

	@Override
	public String getDescricao() {
		return TipoMovimentacao.SAQUE.getDescricao();
	}

	@Override
	public String getSiglaMov() {
		return TipoMovimentacao.SAQUE.getSigla();
	}

	@Override
	public Boolean execute() {
		Boolean resultado = false;
		BigDecimal saldoAtual = DaoConta.getInstance().saldoAtual(TelaPadrao.conta, TelaPadrao.conta.getAgencia());
		
		if(DaoConta.getInstance().existeConta(TelaPadrao.conta.getNumero())){
			if(saldoAtual.compareTo(new BigDecimal(0.00)) > 0){
				
				if(saldoAtual.compareTo(valor) >= 0){
					criarMovimentacao();
					resultado = dao.gravarMovimentacao(movimentacao);
				}else{
					Funcoes.msgAviso("Atenção: Saldo insuficiente.");
				}
			}else{
				Funcoes.msgAviso("Não consta saldo na sua conta.");
			}
		}else{
			Funcoes.msgErro("Conta inexistente para saque.");
		}
		
		
		return resultado;
	}

}
