package br.com.weon.servico.chat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.modelo.chat.ChatModel;
import br.com.weon.negocio.fila.FilaSingleston;
import br.com.weon.negocio.fila.MensagemType;
import br.com.weon.persistencia.chat.ChatImpDAO;
import br.com.weon.persistencia.chat.IChatDAO;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;
import br.com.weon.servico.chat.vo.ChatFormulario;
import br.com.weon.servico.chat.vo.ChatListagemFormulario;

@Component
public class ChatService {

	private IConexao conexao;

	public ChatService(Conexao conexao) {
		this.conexao = conexao;
	}

	public ChatListagemFormulario listarMensagensAguardadoProcessamento() {
		ChatListagemFormulario formulario = new ChatListagemFormulario();

		List<BaseModel> lista = FilaSingleston.get().listarMensagemAgardandoProcessamento(MensagemType.CHAT);

		formulario.setLista(converterModelEmVo(lista));

		return formulario;
	}

	private List<ChatFormulario> converterModelEmVo(List<BaseModel> lista) {
		List<ChatFormulario> listaVo = new ArrayList<>();

		lista.stream().forEach(model -> {
			ChatModel chat = (ChatModel) model;

			listaVo.add(converterModelEmVo(chat));
		});

		return listaVo;
	}

	private ChatFormulario converterModelEmVo(ChatModel chat) {
		ChatFormulario vo = new ChatFormulario();

		vo.setIdChat(chat.getIdChat());
		vo.setNomeUsuarioOrigem(chat.getNomeUsuarioOrigem());
		vo.setNomeUsuarioDestino(chat.getNomeUsuarioDestino());
		vo.setDataHora(chat.getDataHora());

		return vo;
	}

	public String gravar(ChatFormulario formulario) {
		String erro = validarFormulario(formulario);
		if (erro != null)
			return erro;

		IChatDAO dao = new ChatImpDAO(conexao);
		ChatModel chat = buscarModel(formulario.getIdChat(), dao);
		chat = instanciarModelPeloVo(chat, formulario);

		try {
			conexao.iniciarTransacao();
			chat = dao.salvar(chat);
			conexao.comitarTransacao();

			FilaSingleston.get().inserir(chat);
		} catch (Exception e) {
			e.printStackTrace();
			conexao.desfazerTransacao();

			return "Erro ao inserir a mensagem. " + e;
		}

		return "Mensagem inserida com sucesso";
	}

	private String validarFormulario(ChatFormulario formulario) {
		if (formulario == null)
			return "Formulario não preenchido";

		StringBuilder erro = new StringBuilder();

		if (formulario.getNomeUsuarioOrigem() == null || formulario.getNomeUsuarioOrigem().isBlank())
			erro.append("Nome do usuário de origem não pder ser nulo ou vazio\n");
		else {
			int size = formulario.getNomeUsuarioOrigem().length();
			if (size < 3 || size > 80)
				erro.append("Nome do usuário de origem invalido, quantidade de caracteres deve sem entre 3 até 80");
		}

		if (formulario.getNomeUsuarioDestino() == null || formulario.getNomeUsuarioDestino().isBlank())
			erro.append("Nome do usuário de destino não pder ser nulo ou vazio\n");
		else {
			int size = formulario.getNomeUsuarioDestino().length();
			if (size < 3 || size > 80)
				erro.append("Nome do usuário de destino invalido, quantidade de caracteres deve sem entre 3 até 80");
		}

		if (erro.toString().isBlank())
			return null;

		return erro.toString();
	}

	private ChatModel buscarModel(BigInteger id, IChatDAO dao) {
		if (id == null)
			return null;

		return dao.buscarPeloId(id);
	}

	private ChatModel instanciarModelPeloVo(ChatModel model, ChatFormulario vo) {
		if (model == null)
			model = new ChatModel();

		model.setIdChat(vo.getIdChat());
		model.setNomeUsuarioOrigem(vo.getNomeUsuarioOrigem());
		model.setNomeUsuarioDestino(vo.getNomeUsuarioDestino());
		model.setDataHora(vo.getDataHora());

		return model;
	}

	public ChatFormulario buscar(BigInteger idChat) {
		if (idChat == null)
			return null;

		ChatModel chat = buscarPeloId(idChat);
		if (chat == null)
			return null;

		return converterModelEmVo(chat);
	}

	private ChatModel buscarPeloId(BigInteger idChat) {
		if (idChat == null)
			return null;

		IChatDAO dao = new ChatImpDAO();

		return dao.buscarPeloId(idChat);
	}

}
