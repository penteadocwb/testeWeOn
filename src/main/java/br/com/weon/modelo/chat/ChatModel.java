package br.com.weon.modelo.chat;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.utils.DataUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CHAT")
public class ChatModel extends BaseModel {

	@Id
	@GeneratedValue(generator = "ChatGenerator")
	@GenericGenerator(name = "ChatGenerator", strategy = "increment")
	private BigInteger idChat;

	@NotBlank
	@Size(min = 2, max = 80)
	private String nomeUsuarioOrigem;

	@NotBlank
	@Size(min = 2, max = 80)
	private String nomeUsuarioDestino;

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

	@Override
	public String toString() {
		return "Chat [nomeUsuarioOrigem=" + nomeUsuarioOrigem + ", nomeUsuarioDestino=" + nomeUsuarioDestino + ", data/Hora=" + DataUtils.formatarData(getDataHora()) + "]";
	}

}
