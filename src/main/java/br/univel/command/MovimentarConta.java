package br.univel.command;

import br.univel.interfaces.MovimentacaoCommand;

public class MovimentarConta {
	
	private MovimentacaoCommand command;

	public MovimentarConta(MovimentacaoCommand command) {
		this.command = command;
	}
	
	public boolean executaAcao(){
		return this.command.execute();
	}

}
