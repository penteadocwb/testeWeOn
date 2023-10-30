package br.com.weon.servico.voz.vo;

import java.math.BigInteger;
import java.util.Date;

public class VozFormulario {

	private BigInteger idVoz;
	private String telefoneOrigem;
	private String telefoneDestino;
	private Date dataHora;

	public BigInteger getIdVoz() {
		return idVoz;
	}

	public void setIdVoz(BigInteger idVoz) {
		this.idVoz = idVoz;
	}

	public String getTelefoneOrigem() {
		return telefoneOrigem;
	}

	public void setTelefoneOrigem(String telefoneOrigem) {
		this.telefoneOrigem = telefoneOrigem;
	}

	public String getTelefoneDestino() {
		return telefoneDestino;
	}

	public void setTelefoneDestino(String telefoneDestino) {
		this.telefoneDestino = telefoneDestino;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
