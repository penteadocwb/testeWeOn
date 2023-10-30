package br.com.weon.controlador.email;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weon.servico.email.EmailService;
import br.com.weon.servico.email.vo.EmailFormulario;
import br.com.weon.servico.email.vo.EmailListagemFormulario;

@RestController
@RequestMapping("/email")
public class EmailController {

	private EmailService servico;

	public EmailController(EmailService servico) {
		this.servico = servico;
	}

	@GetMapping("listar-em-processamento")
	public EmailListagemFormulario listar() {
		return servico.listarMensagensAguardadoProcessamento();
	}

	@GetMapping("/{id}")
	public EmailFormulario buscar(@PathVariable("id") BigInteger idChat) {
		return servico.buscar(idChat);
	}

	@PostMapping
	public String inserir(@RequestBody EmailFormulario formulario) {
		return servico.gravar(formulario);
	}

	@PutMapping()
	public String atualizar(@RequestBody EmailFormulario formulario) {
		return servico.gravar(formulario);
	}

}
