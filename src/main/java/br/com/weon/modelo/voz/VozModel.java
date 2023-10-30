package br.com.weon.modelo.voz;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import br.com.weon.modelo.base.BaseModel;
import br.com.weon.utils.DataUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Foi decidito que o campo telefone só vai aceitar com 11 digitos Ex.: (11) 11111-1111 sem espaço e pontuação

@Entity
@Table(name = "VOZ")
public class VozModel extends BaseModel {

	@Id
	@GeneratedValue(generator = "VozGenerator")
	@GenericGenerator(name = "VozGenerator", strategy = "increment")
	private BigInteger idVoz;

	@NotBlank
	@Pattern(regexp = "\\d{11}")
	private String telefoneOrigem;

	@NotBlank
	@Pattern(regexp = "\\d{11}")
	private String telefoneDestino;

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

	@Override
	public String toString() {
		return "Voz [telefoneOrigem=" + telefoneOrigem + ", telefoneDestino=" + telefoneDestino + ", data/Hora=" + DataUtils.formatarData(getDataHora()) + "]";
	}

}
