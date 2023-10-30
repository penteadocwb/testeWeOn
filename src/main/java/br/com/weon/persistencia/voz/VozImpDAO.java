package br.com.weon.persistencia.voz;

import java.math.BigInteger;

import br.com.weon.modelo.voz.VozModel;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;

public class VozImpDAO implements IVozDAO {

	private IConexao conexao;

	public VozImpDAO() {
		conexao = new Conexao();
	}

	public VozImpDAO(IConexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public VozModel salvar(VozModel voz) {
		if (voz == null)
			return null;

		voz = conexao.salvar(voz);

		return voz;
	}

	@Override
	public VozModel buscarPeloId(BigInteger idVoz) {
		if (idVoz == null)
			return null;

		VozModel voz = conexao.buscar(VozModel.class, idVoz);

		return voz;
	}

	@Override
	public void excluir(VozModel voz) {
		if (voz == null || voz.getIdVoz() == null)
			return;

		conexao.deletar(voz);
	}

}
