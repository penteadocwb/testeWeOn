package br.com.weon.servico.email.vo;

import java.math.BigInteger;
import java.util.Date;

public class EmailFormulario {

	private BigInteger idEmail;
	private String emailOrigem;
	private String emailDestino;
	private Date dataHora;

	public BigInteger getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(BigInteger idEmail) {
		this.idEmail = idEmail;
	}

	public String getEmailOrigem() {
		return emailOrigem;
	}

	public void setEmailOrigem(String emailOrigem) {
		this.emailOrigem = emailOrigem;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
