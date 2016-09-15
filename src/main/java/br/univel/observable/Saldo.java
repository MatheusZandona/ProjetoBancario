package br.univel.observable;

import java.util.ArrayList;
import java.util.List;

import br.univel.interfaces.SaldoObserver;

public class Saldo{
		
	final List<SaldoObserver> observers = new ArrayList<>();

	public void addObservers(SaldoObserver observer) {
		this.observers.add(observer);
	}
	
	protected void notifyObservers() {
		for (final SaldoObserver observer : observers) {
			observer.atualizaSaldo();
		}
	}
		
	public void alterarSaldo(){
		notifyObservers();
	}
	
}
