package br.univel.interfaces;

import java.util.Date;
import java.util.List;
import br.univel.classes.Movimentacao;

public interface DaoMov {
	public boolean gravarMovimentacao(Movimentacao movimentacao);
	public List<Movimentacao> listarOperacoesConta(Date dataInicial, Date dataFinal);
	public List<Movimentacao> listarOperacoesAgencia(String agencia, Date dataInicial, Date dataFinal);
}
