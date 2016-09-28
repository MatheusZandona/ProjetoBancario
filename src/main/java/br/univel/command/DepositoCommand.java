package br.univel.command;

import java.math.BigDecimal;

import br.univel.classes.Conta;
import br.univel.classes.Movimentacao;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.MovimentacaoCommand;

public class DepositoCommand implements MovimentacaoCommand {
	
	private Movimentacao movimentacao;	
	private BigDecimal valor;
	private DaoMovimentacao dao = DaoMovimentacao.getInstance();
	private Conta conta;
		

	public DepositoCommand(Conta conta, BigDecimal valor) {
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
		DaoConta daoConta = DaoConta.getInstance();
		
		if((conta != null) && (daoConta.existeConta(conta.getNumero()))){		
			criarMovimentacao();
			resultado = dao.gravarMovimentacao(movimentacao);
		}else{
			Funcoes.msgAviso("Conta para depósito inexistente.");
		}
		
		return resultado;	
	}
	
}
