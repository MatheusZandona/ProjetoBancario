package br.univel.facade;

import java.math.BigDecimal;

import br.univel.classes.Conta;
import br.univel.classes.Movimentacao;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoMovimentacao;
import br.univel.interfaces.MovimentacaoFacade;

public class DepositoFacade implements MovimentacaoFacade {
	
	private Movimentacao movimentacao;	
	private BigDecimal valor;
	private DaoMovimentacao dao = DaoMovimentacao.getInstance();
	private Conta conta;
		

	public DepositoFacade(Conta conta, BigDecimal valor) {
		this.valor = valor;
		this.conta = conta;
	}

	@Override
	public void criarMovimentacao() {
		this.movimentacao = new Movimentacao(conta, getSiglaMov(), this.valor, getDescricao());
	}

	@Override
	public String getDescricao() {
		return TipoMovimentacao.DEPOSITO.getDescricao();
	}

	@Override
	public String getSiglaMov() {
		return TipoMovimentacao.DEPOSITO.getSigla();
	}

	@Override
	public Boolean execute() {		
		boolean resultado = false;

		if(DaoConta.getInstance().existeConta(conta.getNumero())){		
			criarMovimentacao();
			resultado = dao.gravarMovimentacao(movimentacao);
		}
		
		return resultado;	
	}
	
}
