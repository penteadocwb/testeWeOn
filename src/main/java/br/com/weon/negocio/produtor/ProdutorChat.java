package br.com.weon.negocio.produtor;

import br.com.weon.modelo.chat.ChatModel;
import br.com.weon.negocio.fila.Fila;
import br.com.weon.persistencia.chat.ChatImpDAO;
import br.com.weon.persistencia.chat.IChatDAO;
import br.com.weon.persistencia.conexao.IConexao;

public class ProdutorChat extends ProdutorAbstract {

	private static boolean pararPersistencia = false;

	private Fila fila;
	long tempoDeExecucao;
	private IConexao conexao;

	public ProdutorChat(Fila fila, long tempoDeExecucaoEmMilessegundos, IConexao conexao) {
		this.fila = fila;
		tempoDeExecucao = tempoDeExecucaoEmMilessegundos;
		this.conexao = conexao;
	}

	@Override
	public void run() {
		IChatDAO dao = new ChatImpDAO(conexao);
		while (true) {
			fila.thredAguardarFilaVazia();

			long tempoParada = System.currentTimeMillis() + tempoDeExecucao;
			while (System.currentTimeMillis() < tempoParada) {
				ChatModel chat = GeradorUtils.gerarMsgChat();

				persistir(chat, dao);
			}
		}
	}

	private synchronized void persistir(ChatModel chat, IChatDAO dao) {
		while (pararPersistencia) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		pararPersistencia = true;

		try {
			conexao.iniciarTransacao();
			chat = dao.salvar(chat);
			conexao.comitarTransacao();

			fila.inserir(chat);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();
		}

		pararPersistencia = false;
		notifyAll();
	}

}
