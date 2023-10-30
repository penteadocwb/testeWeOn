package br.com.weon.servico.fila.vo;

public class FilaStatusFormularioVO {

	private long totalProduzido;
	private long totalConsumido;

	public long getTotalProduzido() {
		return totalProduzido;
	}

	public void setTotalProduzido(long totalProduzido) {
		this.totalProduzido = totalProduzido;
	}

	public long getTotalConsumido() {
		return totalConsumido;
	}

	public void setTotalConsumido(long totalConsumido) {
		this.totalConsumido = totalConsumido;
	}

}
