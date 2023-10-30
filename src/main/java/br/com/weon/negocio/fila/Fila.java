package br.com.weon.negocio.fila;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.modelo.chat.ChatModel;
import br.com.weon.modelo.email.EmailModel;
import br.com.weon.modelo.voz.VozModel;

public class Fila {

	private int tamanhoFila;
	private Queue<BaseModel> fila;
	private boolean jaEncheuFila;

	private long totalProduzido;
	private long totalConsumido;

	public Fila(int tamanhoFila) {
		this.tamanhoFila = tamanhoFila;
		fila = new LinkedList<BaseModel>();
		jaEncheuFila = false;
		totalProduzido = 0l;
		totalConsumido = 0l;
	}

	public synchronized boolean filaVazia() {
		return fila.isEmpty();
	}

	public synchronized void thredAguardarFilaVazia() {
		while (!fila.isEmpty() && jaEncheuFila) {
			jaEncheuFila = false;
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		notifyAll();
	}

	public synchronized boolean filaCheia() {
		return fila.size() == tamanhoFila;
	}

	public synchronized void inserir(BaseModel novo) {
		while (fila.size() == tamanhoFila) {
			jaEncheuFila = true;
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		fila.add(novo);
		totalProduzido++;
		notifyAll();
	}

	public synchronized BaseModel remover() {
		while (fila.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		notifyAll();
		totalConsumido++;
		return fila.remove();
	}

	public long getTotalProduzido() {
		return totalProduzido;
	}

	public long gettotalConsumido() {
		return totalConsumido;
	}

	public List<BaseModel> listarMensagemAgardandoProcessamento(MensagemType tipo) {
		List<BaseModel> lista = new ArrayList<>();

		for (BaseModel elemento : fila) {
			if (MensagemType.CHAT.equals(tipo) && elemento instanceof ChatModel)
				lista.add(elemento);
			else if (MensagemType.EMAIL.equals(tipo) && elemento instanceof EmailModel)
				lista.add(elemento);
			else if (MensagemType.VOZ.equals(tipo) && elemento instanceof VozModel)
				lista.add(elemento);
		}

		return lista;
	}

}
