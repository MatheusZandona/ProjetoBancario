package br.univel.command;

import java.math.BigDecimal;

import br.univel.classes.Movimentacao;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.enuns.TipoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.MovimentacaoCommand;
import br.univel.telas.TelaPadrao;

public class PagamentoCommand implements MovimentacaoCommand{
	
	private Movimentacao movimentacao;
	private String codBarra;
	private BigDecimal valor;
	private DaoMovimentacao daoMov = DaoMovimentacao.getInstance();


	public PagamentoCommand(String codBarra, BigDecimal valor) {
		this.codBarra = codBarra;
		this.valor = valor;
	}
	
	@Override
	public void criarMovimentacao() {
		this.movimentacao = new Movimentacao(TelaPadrao.conta, getSiglaMov(), this.valor.negate(), getDescricao());
	}

	@Override
	public String getDescricao() {
		return "Pagamento do documento ".concat(this.codBarra);
	}

	@Override
	public String getSiglaMov() {
		return TipoMovimentacao.PAGAMENTO.getSigla();
	}	
	

	@Override
	public Boolean execute() {		
		
		boolean resultado = false;
		BigDecimal saldoAtual = new BigDecimal(0.0);
		
		saldoAtual = DaoConta.getInstance().saldoAtual(TelaPadrao.conta, TelaPadrao.conta.getAgencia());
		
		if(saldoAtual.compareTo(new BigDecimal(0.00)) > 0){
			
			if(saldoAtual.compareTo(valor) >= 0){
				criarMovimentacao();
				resultado = daoMov.gravarMovimentacao(movimentacao);
			}else{
				Funcoes.msgAviso("Atenção: Saldo insuficiente.");
			}
		}else{
			Funcoes.msgAviso("Não consta saldo na sua conta.");
		}
		
		return resultado;	
	}	
	


}
