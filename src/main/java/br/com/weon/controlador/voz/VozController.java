package br.com.weon.controlador.voz;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weon.servico.voz.VozService;
import br.com.weon.servico.voz.vo.VozFormulario;
import br.com.weon.servico.voz.vo.VozListagemFormulario;

@RestController
@RequestMapping("/voz")
public class VozController {

	private VozService servico;

	public VozController(VozService servico) {
		this.servico = servico;
	}

	@GetMapping("listar-em-processamento")
	public VozListagemFormulario listar() {
		return servico.listarMensagensAguardadoProcessamento();
	}

	@GetMapping("/{id}")
	public VozFormulario buscar(@PathVariable("id") BigInteger idChat) {
		return servico.buscar(idChat);
	}

	@PostMapping
	public String inserir(@RequestBody VozFormulario formulario) {
		return servico.gravar(formulario);
	}

	@PutMapping()
	public String atualizar(@RequestBody VozFormulario formulario) {
		return servico.gravar(formulario);
	}

}
