package br.com.weon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

	public static String formatarData(Date data) {
		if (data == null)
			return null;

		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}

}
