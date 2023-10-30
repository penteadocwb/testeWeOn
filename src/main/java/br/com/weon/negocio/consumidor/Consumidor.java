package br.com.weon.negocio.consumidor;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.negocio.fila.Fila;

public class Consumidor extends Thread {

	private Fila fila;

	public Consumidor(Fila fila) {
		this.fila = fila;
	}

	@Override
	public void run() {
		while (true) {
			BaseModel msg = fila.remover();

			System.out.println("Consumidor-" + this.getName() + " " + msg.toString());
		}
	}

}
