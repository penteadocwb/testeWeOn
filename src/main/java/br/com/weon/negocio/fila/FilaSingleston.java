package br.com.weon.negocio.fila;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilaSingleston {

	private static Fila fila;

	private static FilaSingleston singleston = null;

	private FilaSingleston() {
		fila = new Fila(recuperaTamanhofila());
	}

	private int recuperaTamanhofila() {
		InputStream arquivoConfig = getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties propriedades = new Properties();
		try {
			propriedades.load(arquivoConfig);

			String tamFila = propriedades.getProperty("tamanhoFila");

			if (tamFila == null || tamFila.isBlank())
				return 0;

			return Integer.parseInt(tamFila);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static Fila get() {
		if (singleston == null)
			singleston = new FilaSingleston();

		return fila;
	}

}
