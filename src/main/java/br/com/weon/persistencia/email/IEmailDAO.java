package br.com.weon.persistencia.email;

import java.math.BigInteger;

import br.com.weon.modelo.email.EmailModel;

public interface IEmailDAO {

	public EmailModel salvar(EmailModel email);

	public EmailModel buscarPeloId(BigInteger idEmail);

	public void excluir(EmailModel email);

}
