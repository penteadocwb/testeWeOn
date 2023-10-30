package br.com.weon.persistencia.email;

import java.math.BigInteger;

import br.com.weon.modelo.email.EmailModel;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;

public class EmailImpDAO implements IEmailDAO {

	private IConexao conexao;

	public EmailImpDAO() {
		conexao = new Conexao();
	}

	public EmailImpDAO(IConexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public EmailModel salvar(EmailModel email) {
		if (email == null)
			return null;

		email = conexao.salvar(email);

		return email;
	}

	@Override
	public EmailModel buscarPeloId(BigInteger idEmail) {
		if (idEmail == null)
			return null;

		EmailModel email = conexao.buscar(EmailModel.class, idEmail);

		return email;
	}

	@Override
	public void excluir(EmailModel email) {
		if (email == null || email.getIdEmail() == null)
			return;

		conexao.deletar(email);
	}

}
