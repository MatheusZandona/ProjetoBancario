package br.univel.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import br.univel.interfaces.SaldoObserver;

public class Saldo{
	
	final List<SaldoObserver> observers = new ArrayList<>();

	/**
	 * Possibilita a adi��o de um novo observador ao professor, pode ser
	 * qualquer observador, visto que basta implementar a interface
	 * ProfessorObserver
	 * 
	 * @param observer
	 */
	public void addObservers(SaldoObserver observer) {
		this.observers.add(observer);
	}
	
	protected void notifyObservers() {
		for (final SaldoObserver observer : observers) {
			observer.atualizaSaldo(this);
		}
	}
	
	public boolean saldoAtualizado(){
		notifyObservers(); // notificar os observadores
		return true;
	} 
}
