package dev.marcuspinto.rest.client;

public class Confirmados extends Estatistica {

	private String recuperados;
	private String acompanhamento;

	public String getRecuperados() {
		return recuperados;
	}

	public void setRecuperados(String recuperados) {
		this.recuperados = recuperados;
	}

	public String getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}
}