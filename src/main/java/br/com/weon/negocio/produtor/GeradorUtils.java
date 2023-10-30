package br.com.weon.negocio.produtor;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import br.com.weon.modelo.chat.ChatModel;
import br.com.weon.modelo.email.EmailModel;
import br.com.weon.modelo.voz.VozModel;

public class GeradorUtils {

	public static String gerarNome() {
		ThreadLocalRandom gerador = ThreadLocalRandom.current();

		StringBuilder nome = new StringBuilder();

		// Gera a primeira letra do nome
		nome.append(gerarCaracterMaiusculo(gerador));

		// Gera o restante do nome, o nome será composto por 3 a 10 caracteres
		nome.append(gerarCadeiaDeCaracteresMinuscula(gerador.nextInt(2, 10), gerador));

		nome.append(' ');

		// Gera a primeira letra do sobrenome
		nome.append(gerarCaracterMaiusculo(gerador));

		// Gera o restante do sobrenome, o sobrenome será composto por 5 a 12 caracteres
		nome.append(gerarCadeiaDeCaracteresMinuscula(gerador.nextInt(4, 12), gerador));

		return nome.toString();
	}

	private static char gerarCaracterMaiusculo(ThreadLocalRandom gerador) {
		// Na tabela ASCII as posições entre 65 até 90 representam as letras maiusculas.
		return (char) gerador.nextInt(65, 90);
	}

	private static String gerarCadeiaDeCaracteresMinuscula(int qtdCaracter, ThreadLocalRandom gerador) {
		// Na tabela ASCII as posições entre 97 até 122 representam as letras minusculas.
		StringBuffer str = new StringBuffer();
		for (int i = 1; i < qtdCaracter; i++) {
			str.append((char) gerador.nextInt(97, 122));
		}

		return str.toString();
	}

	public static String gerarEmail() {
		ThreadLocalRandom gerador = ThreadLocalRandom.current();

		StringBuilder email = new StringBuilder();

		// Gera a identificação do usuario
		email.append(gerarCadeiaDeCaracteresMinuscula(gerador.nextInt(5, 20), gerador));

		email.append('@');

		// Gera o nome do provedor
		email.append(gerarCadeiaDeCaracteresMinuscula(gerador.nextInt(4, 8), gerador));

		email.append(".com");

		return email.toString();
	}

	public static String gerarTelefone() {
		ThreadLocalRandom gerador = ThreadLocalRandom.current();

		StringBuilder telefone = new StringBuilder();

		for (int i = 0; i < 11; ++i) {
			telefone.append((char) gerador.nextInt(48, 57));
		}

		return telefone.toString();
	}

	public static ChatModel gerarMsgChat() {
		ChatModel chat = new ChatModel();
		chat.setNomeUsuarioOrigem(gerarNome());
		chat.setNomeUsuarioDestino(gerarNome());
		chat.setDataHora(new Date());

		return chat;
	}

	public static EmailModel gerarMsgEmail() {
		EmailModel email = new EmailModel();
		email.setEmailOrigem(gerarEmail());
		email.setEmailDestino(gerarEmail());
		email.setDataHora(new Date());

		return email;
	}

	public static VozModel gerarMsgVoz() {
		VozModel voz = new VozModel();
		voz.setTelefoneOrigem(gerarTelefone());
		voz.setTelefoneDestino(gerarTelefone());
		voz.setDataHora(new Date());

		return voz;
	}

}
