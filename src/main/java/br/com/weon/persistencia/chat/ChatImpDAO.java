package br.com.weon.persistencia.chat;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import br.com.weon.modelo.chat.ChatModel;
import br.com.weon.persistencia.conexao.Conexao;
import br.com.weon.persistencia.conexao.IConexao;

@Repository
public class ChatImpDAO implements IChatDAO {

	private IConexao conexao;

	public ChatImpDAO() {
		conexao = new Conexao();
	}

	public ChatImpDAO(IConexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public ChatModel salvar(ChatModel chat) {
		if (chat == null)
			return null;

		chat = conexao.salvar(chat);

		return chat;
	}

	@Override
	public ChatModel buscarPeloId(BigInteger idChat) {
		if (idChat == null)
			return null;

		ChatModel chat = conexao.buscar(ChatModel.class, idChat);

		return chat;
	}

	@Override
	public void excluir(ChatModel chat) {
		if (chat == null || chat.getIdChat() == null)
			return;

		conexao.deletar(chat);
	}

}
