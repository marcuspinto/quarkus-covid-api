package dev.marcuspinto.rest.client;

public class Obitos extends Estatistica {
	
	private String letalidade;
	private String mortalidade;

	public String getLetalidade() {
		return letalidade;
	}

	public void setLetalidade(String letalidade) {
		this.letalidade = letalidade;
	}

	public String getMortalidade() {
		return mortalidade;
	}

	public void setMortalidade(String mortalidade) {
		this.mortalidade = mortalidade;
	}
}