package br.com.weon.negocio.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.weon.negocio.consumidor.Consumidor;
import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.utils.ConversorUtils;

public class PoolConsumidor {

	private Consumidor[] pool;

	public void iniciar() {
		InputStream arquivoConfig = getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties propriedades = new Properties();
		try {
			propriedades.load(arquivoConfig);

			int quantidade = recuperarQuantidadeConsumidor(propriedades);

			pool = new Consumidor[quantidade];

			for (int i = 0; i < quantidade; ++i) {
				pool[i] = new Consumidor(FilaSingleston.get());
				pool[i].start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int recuperarQuantidadeConsumidor(Properties propriedades) {
		return ConversorUtils.converterStringEmInt(propriedades.getProperty("quantidadeConsumidor"));
	}

}
