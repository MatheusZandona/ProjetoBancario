package br.univel.classes.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import br.univel.classes.Movimentacao;
import br.univel.classes.bd.ConexaoBD;
import br.univel.interfaces.DaoMov;
import br.univel.telas.TelaPadrao;

public class DaoMovimentacao implements DaoMov{
	
	private static DaoMovimentacao dao;
	
	public static DaoMovimentacao getInstance(){
		if (dao == null){
			dao = new DaoMovimentacao();
		}				
		return dao;
	}					

	@Override 
	public List<Movimentacao> listarOperacoesConta(Date dataInicial, Date dataFinal) {
		ArrayList<Movimentacao> listaOperacoes = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CONTAS_MOVIMENTO WHERE CONTA_NUMERO = ? AND DATA BETWEEN ? AND ?");
			ps.setString(1, TelaPadrao.conta.getNumero());
			ps.setDate(2, new java.sql.Date(dataInicial.getTime()));
			ps.setDate(3, new java.sql.Date(dataFinal.getTime()));
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Movimentacao mov = new Movimentacao();
				mov.setConta(new DaoConta().buscar(result.getString("conta_numero")));
				mov.setData(result.getDate("data"));
				mov.setHora(result.getTime("hora"));
				mov.setDescricao(result.getString("descricao"));
				mov.setValor(result.getBigDecimal("valor"));
				mov.setTipoM(result.getString("tipo_movimento"));

				listaOperacoes.add(mov);
			}
			
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaOperacoes;

	}

	@Override
	public List<Movimentacao> listarOperacoesAgencia(String agencia, Date dataInicial, Date dataFinal) {
		ArrayList<Movimentacao> listaOperacoes = new ArrayList<>();
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT CM.*, C.AGENCIA FROM CONTAS_MOVIMENTO CM INNER JOIN CONTAS C ON C.NUMERO = CM.CONTA_NUMERO WHERE AGENCIA = ? AND DATA BETWEEN ? AND ?");
			ps.setString(1, agencia);
			ps.setDate(2, new java.sql.Date(dataInicial.getTime()));
			ps.setDate(3, new java.sql.Date(dataFinal.getTime()));
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Movimentacao mov = new Movimentacao();
				mov.setConta(new DaoConta().buscar(result.getString("conta_numero")));
				mov.setData(result.getDate("data"));
				mov.setHora(result.getTime("hora"));
				mov.setDescricao(result.getString("descricao"));
				mov.setValor(result.getBigDecimal("valor"));
				mov.setTipoM(result.getString("tipo_movimento"));

				listaOperacoes.add(mov);
			}
			
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaOperacoes;

	}

	@Override
	public boolean gravarMovimentacao(Movimentacao movimentacao) {
		boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) ConexaoBD.getInstance().abrirConexao()
					.clientPrepareStatement("INSERT INTO CONTAS_MOVIMENTO (CONTA_NUMERO,TIPO_MOVIMENTO,DATA,HORA,VALOR,DESCRICAO) VALUES (?,?,CURRENT_DATE,CURRENT_TIME,?,?)");
			ps.setString(1, movimentacao.getConta().getNumero());
			ps.setString(2, movimentacao.getTipoM());
			ps.setBigDecimal(3, movimentacao.getValor());
			ps.setString(4, movimentacao.getDescricao());
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
}
