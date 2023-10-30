package br.com.weon.persistencia.voz;

import java.math.BigInteger;

import br.com.weon.modelo.voz.VozModel;

public interface IVozDAO {

	public VozModel salvar(VozModel voz);

	public VozModel buscarPeloId(BigInteger idVoz);

	public void excluir(VozModel voz);

}
