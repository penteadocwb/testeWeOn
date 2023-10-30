package br.com.weon.controlador.fila;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weon.servico.fila.FilaService;
import br.com.weon.servico.fila.vo.FilaStatusFormularioVO;

@RestController
@RequestMapping("/fila")
public class FilaControlador {

	private FilaService servico;

	public FilaControlador(FilaService servico) {
		this.servico = servico;
	}

	@GetMapping("/status")
	public FilaStatusFormularioVO getStatus() {
		return servico.getStatus(null);
	}

}
