package dev.marcuspinto.rest.client;

public class Geral {

	private Confirmados confirmados;
	private Obitos obitos;
	private String dt_updated;
	private Planilha planilha;

	public Confirmados getConfirmados() {
		return confirmados;
	}

	public void setConfirmados(Confirmados confirmados) {
		this.confirmados = confirmados;
	}

	public Obitos getObitos() {
		return obitos;
	}

	public void setObitos(Obitos obitos) {
		this.obitos = obitos;
	}

	public String getDt_updated() {
		return dt_updated;
	}

	public void setDt_updated(String dt_updated) {
		this.dt_updated = dt_updated;
	}

	public Planilha getPlanilha() {
		return planilha;
	}

	public void setPlanilha(Planilha planilha) {
		this.planilha = planilha;
	}

}
