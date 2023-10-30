package br.com.weon.persistencia.chat;

import java.math.BigInteger;

import br.com.weon.modelo.chat.ChatModel;

public interface IChatDAO {

	public ChatModel salvar(ChatModel chat);

	public ChatModel buscarPeloId(BigInteger idChat);

	public void excluir(ChatModel chat);

}
