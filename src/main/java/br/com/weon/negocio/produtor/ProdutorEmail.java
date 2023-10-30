package br.com.weon.negocio.produtor;

import br.com.weon.modelo.email.EmailModel;
import br.com.weon.negocio.fila.Fila;
import br.com.weon.persistencia.conexao.IConexao;
import br.com.weon.persistencia.email.EmailImpDAO;
import br.com.weon.persistencia.email.IEmailDAO;

public class ProdutorEmail extends ProdutorAbstract {

	private static boolean pararPersistencia = false;

	private Fila fila;
	long tempoDeExecucao;
	private IConexao conexao;

	public ProdutorEmail(Fila fila, long tempoDeExecucaoEmMilessegundos, IConexao conexao) {
		this.fila = fila;
		tempoDeExecucao = tempoDeExecucaoEmMilessegundos;
		this.conexao = conexao;
	}

	@Override
	public void run() {
		IEmailDAO dao = new EmailImpDAO(conexao);
		while (true) {
			fila.thredAguardarFilaVazia();

			long tempoParada = System.currentTimeMillis() + tempoDeExecucao;
			while (System.currentTimeMillis() < tempoParada) {
				EmailModel email = GeradorUtils.gerarMsgEmail();

				persistir(email, dao);
			}
		}
	}

	private synchronized void persistir(EmailModel email, IEmailDAO dao) {
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
			email = dao.salvar(email);
			conexao.comitarTransacao();

			fila.inserir(email);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();
		}

		pararPersistencia = false;
		notifyAll();
	}

}
