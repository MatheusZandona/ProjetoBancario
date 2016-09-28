package br.univel.command;

import java.math.BigDecimal;

import br.univel.classes.Conta;
import br.univel.classes.Movimentacao;
import br.univel.classes.dao.DaoConta;
import br.univel.classes.dao.DaoMovimentacao;
import br.univel.funcoes.Funcoes;
import br.univel.interfaces.MovimentacaoCommand;

public class TransferenciaCommand implements MovimentacaoCommand{

	
	private Movimentacao movimentacaoOrigem;
	private Movimentacao movimentacaoDestino;
	private BigDecimal valor;
	private DaoMovimentacao daoMov = DaoMovimentacao.getInstance();
	private Conta contaOrigem;
	private Conta contaDestino;
	
	
	public TransferenciaCommand(Conta contaOrigem, Conta contaDestino, BigDecimal valor){
		this.contaDestino = contaDestino;
		this.contaOrigem  = contaOrigem;
		this.valor = valor; 
	}
	
	@Override
	public void criarMovimentacao() {
		
		movimentacaoOrigem = new Movimentacao(contaOrigem, 
											"TS", 
											valor.negate(), 
											"Transferência para a conta ".concat(contaDestino.getNumero().concat(" agência ").concat(contaDestino.getAgencia().getNumero())));
		
		movimentacaoDestino = new Movimentacao(contaDestino, 
												"TE", 
												valor, 
												"Transferência recebida da conta ".concat(contaOrigem.getNumero().concat(" agência ").concat(contaOrigem.getAgencia().getNumero())));
	}

	@Override
	public String getDescricao() {
		return null;
	}

	@Override
	public String getSiglaMov() {
		return null;
	}

	
	@Override
	public Boolean execute() {
		Boolean resultado = false;
		DaoConta daoConta = DaoConta.getInstance(); 
		BigDecimal saldoAtual = daoConta.saldoAtual(contaOrigem, contaOrigem.getAgencia());
		
		if(!daoConta.existeConta(contaOrigem.getNumero())){
			Funcoes.msgAviso("Conta origem inexistente.");
			
		}else if(!daoConta.existeConta(contaDestino.getNumero())){
			Funcoes.msgAviso("Conta destino inexistente.");
			
		}else if(saldoAtual.compareTo(new BigDecimal(0.00)) <= 0){
			Funcoes.msgAviso("Não há saldo em conta.");	
			
		}else if(saldoAtual.compareTo(this.valor) < 0){
			Funcoes.msgAviso("Saldo insuficiente.");	

		}else{
			criarMovimentacao();
			daoMov.gravarMovimentacao(movimentacaoOrigem);
			daoMov.gravarMovimentacao(movimentacaoDestino);
			resultado = true;			
		}				
		
		return resultado;
	}

}
