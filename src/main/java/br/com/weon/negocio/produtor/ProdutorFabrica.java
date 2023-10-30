package br.com.weon.negocio.produtor;

import br.com.weon.negocio.fila.Fila;
import br.com.weon.persistencia.conexao.IConexao;

public class ProdutorFabrica {

	public static ProdutorAbstract instanciar(TipoProdutorType produtor, Fila fila, long tempoDeExecucaoEmMilessegundos, IConexao conexao) {
		if (TipoProdutorType.CHAT.equals(produtor))
			return new ProdutorChat(fila, tempoDeExecucaoEmMilessegundos, conexao);

		if (TipoProdutorType.EMAIL.equals(produtor))
			return new ProdutorEmail(fila, tempoDeExecucaoEmMilessegundos, conexao);

		if (TipoProdutorType.VOZ.equals(produtor))
			return new ProdutorVoz(fila, tempoDeExecucaoEmMilessegundos, conexao);

		return null;
	}

}
