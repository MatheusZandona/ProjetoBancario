package br.univel.telas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.classes.abstratas.PanelAbstrato;
import br.univel.enuns.TipoMovimentacao;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class ConfirmaOperacao extends PanelAbstrato{
	
	private TipoMovimentacao tipoMovimentacao;
	private BigDecimal valor;			
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ConfirmaOperacao(TipoMovimentacao tipoMovimentacao, BigDecimal valor) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.valor			  = valor;
		NumberFormat formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		
		JLabel lblOperacao = new JLabel("Opera\u00E7\u00E3o ".concat(this.tipoMovimentacao.getDescricao()).concat(" realizada com sucesso!"));
		lblOperacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblValor = new JLabel("Valor: ".concat(formatNumber.format(valor)));
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTelaPadrao().dispose();
			}
		});
		
		JButton btnImprimir = new JButton("Imprimir");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblOperacao)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblValor)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnImprimir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRetornar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addComponent(lblOperacao)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblValor)
						.addComponent(btnRetornar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImprimir)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
