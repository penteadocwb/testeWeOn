package br.com.weon.persistencia.conexao;

import java.util.HashMap;
import java.util.List;

public interface IConexao {

	public void fechar();

	public <T> T salvar(T objeto);

	public <T> T atualizar(T objeto);

	public void deletar(Object objeto);

	public <T> T buscar(Class<T> classeEntidade, Object chavePrimaria);

	public <T> List<T> buscar(String consulta, HashMap<String, Object> parametros);

	public void iniciarTransacao();

	public void comitarTransacao();

	public void desfazerTransacao();

}
