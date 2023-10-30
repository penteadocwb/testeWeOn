package br.com.weon.negocio.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.negocio.produtor.ProdutorAbstract;
import br.com.weon.negocio.produtor.ProdutorFabrica;
import br.com.weon.negocio.produtor.TipoProdutorType;
import br.com.weon.persistencia.conexao.Conexao;

public abstract class PoolProdutorAbstract {

	private ProdutorAbstract[] pool;

	public void iniciar() {
		InputStream arquivoConfig = getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties propriedades = new Properties();
		try {
			propriedades.load(arquivoConfig);

			int quantidade = recuperarQuantidadeProdutor(propriedades);
			long tempo = recuparTempoProdutor(propriedades) * 1000l;

			pool = new ProdutorAbstract[quantidade];

			for (int i = 0; i < quantidade; ++i) {
				pool[i] = ProdutorFabrica.instanciar(retornarTipoProdutor(), FilaSingleston.get(), tempo, new Conexao());
				pool[i].start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract int recuperarQuantidadeProdutor(Properties propriedades);

	protected abstract int recuparTempoProdutor(Properties propriedades);

	protected abstract TipoProdutorType retornarTipoProdutor();

}
