package br.com.weon.servico.fila;

import org.springframework.stereotype.Service;

import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.servico.fila.vo.FilaStatusFormularioVO;

@Service
public class FilaService {

	public FilaStatusFormularioVO getStatus(FilaStatusFormularioVO formulario) {
		if (formulario == null)
			formulario = new FilaStatusFormularioVO();

		formulario.setTotalProduzido(FilaSingleston.get().getTotalProduzido());
		formulario.setTotalConsumido(FilaSingleston.get().gettotalConsumido());

		return formulario;
	}

}
