package br.com.weon.servico.chat.vo;

import java.math.BigInteger;
import java.util.Date;

public class ChatFormulario {

	private BigInteger idChat;
	private String nomeUsuarioOrigem;
	private String nomeUsuarioDestino;
	private Date dataHora;

	public BigInteger getIdChat() {
		return idChat;
	}

	public void setIdChat(BigInteger idChat) {
		this.idChat = idChat;
	}

	public String getNomeUsuarioOrigem() {
		return nomeUsuarioOrigem;
	}

	public void setNomeUsuarioOrigem(String nomeUsuarioOrigem) {
		this.nomeUsuarioOrigem = nomeUsuarioOrigem;
	}

	public String getNomeUsuarioDestino() {
		return nomeUsuarioDestino;
	}

	public void setNomeUsuarioDestino(String nomeUsuarioDestino) {
		this.nomeUsuarioDestino = nomeUsuarioDestino;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
