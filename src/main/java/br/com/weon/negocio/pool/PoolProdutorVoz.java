package br.com.weon.negocio.pool;

import java.util.Properties;

import br.com.weon.negocio.produtor.TipoProdutorType;
import br.com.weon.utils.ConversorUtils;

public class PoolProdutorVoz extends PoolProdutorAbstract {

	@Override
	protected int recuperarQuantidadeProdutor(Properties propriedades) {
		return ConversorUtils.converterStringEmInt(propriedades.getProperty("quantidadeProdutorVoz"));
	}

	@Override
	protected int recuparTempoProdutor(Properties propriedades) {
		return ConversorUtils.converterStringEmInt(propriedades.getProperty("tempoExecucaoProdutorVoz"));
	}

	@Override
	protected TipoProdutorType retornarTipoProdutor() {
		return TipoProdutorType.VOZ;
	}

}
