package br.com.weon.controlador.chat;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weon.servico.chat.ChatService;
import br.com.weon.servico.chat.vo.ChatFormulario;
import br.com.weon.servico.chat.vo.ChatListagemFormulario;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private ChatService servico;

	public ChatController(ChatService servico) {
		this.servico = servico;
	}

	@GetMapping("listar-em-processamento")
	public ChatListagemFormulario listar() {
		return servico.listarMensagensAguardadoProcessamento();
	}

	@GetMapping("/{id}")
	public ChatFormulario buscar(@PathVariable("id") BigInteger idChat) {
		return servico.buscar(idChat);
	}

	@PostMapping
	public String inserir(@RequestBody ChatFormulario formulario) {
		return servico.gravar(formulario);
	}

	@PutMapping()
	public String atualizar(@RequestBody ChatFormulario formulario) {
		return servico.gravar(formulario);
	}

}
