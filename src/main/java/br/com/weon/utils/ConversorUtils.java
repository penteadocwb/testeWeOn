package br.com.weon.utils;

public class ConversorUtils {

	public static int converterStringEmInt(String str) {
		if (str == null || str.isBlank())
			return 1;

		return Integer.parseInt(str);
	}

}
