package br.com.weon.modelo.email;

import java.math.BigInteger;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.utils.DataUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMAIL")
public class EmailModel extends BaseModel {

	@Id
	@GeneratedValue(generator = "EmailGenerator")
	@GenericGenerator(name = "EmailGenerator", strategy = "increment")
	private BigInteger idEmail;

	@NotBlank
	@Email
	private String emailOrigem;

	@NotBlank
	@Email
	private String emailDestino;

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

	@Override
	public String toString() {
		return "Email [emailOrigem=" + emailOrigem + ", emailDestino=" + emailDestino + ", data/Hora=" + DataUtils.formatarData(getDataHora()) + "]";
	}

}
