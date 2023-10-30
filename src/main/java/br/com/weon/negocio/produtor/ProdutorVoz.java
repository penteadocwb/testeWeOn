package br.com.weon.negocio.produtor;

import br.com.weon.modelo.voz.VozModel;
import br.com.weon.negocio.fila.Fila;
import br.com.weon.persistencia.conexao.IConexao;
import br.com.weon.persistencia.voz.IVozDAO;
import br.com.weon.persistencia.voz.VozImpDAO;

public class ProdutorVoz extends ProdutorAbstract {

	private static boolean pararPersistencia = false;

	private Fila fila;
	long tempoDeExecucao;
	private IConexao conexao;

	public ProdutorVoz(Fila fila, long tempoDeExecucaoEmMilessegundos, IConexao conexao) {
		this.fila = fila;
		tempoDeExecucao = tempoDeExecucaoEmMilessegundos;
		this.conexao = conexao;
	}

	@Override
	public void run() {
		IVozDAO dao = new VozImpDAO(conexao);
		while (true) {
			fila.thredAguardarFilaVazia();

			long tempoParada = System.currentTimeMillis() + tempoDeExecucao;
			while (System.currentTimeMillis() < tempoParada) {
				VozModel voz = GeradorUtils.gerarMsgVoz();

				persistir(voz, dao);
			}
		}
	}

	private synchronized void persistir(VozModel voz, IVozDAO dao) {
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
			voz = dao.salvar(voz);
			conexao.comitarTransacao();

			fila.inserir(voz);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();
		}

		pararPersistencia = false;
		notifyAll();
	}

}
