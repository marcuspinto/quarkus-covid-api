package dev.marcuspinto.rest.client;

public class Estado {

	private String _id;
	private String nome;
	private String casosAcumulado;
	private String obitosAcumulado;
	private String populacaoTCU2019;
	private String incidencia;
	private String incidenciaObito;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCasosAcumulado() {
		return casosAcumulado;
	}

	public void setCasosAcumulado(String casosAcumulado) {
		this.casosAcumulado = casosAcumulado;
	}

	public String getObitosAcumulado() {
		return obitosAcumulado;
	}

	public void setObitosAcumulado(String obitosAcumulado) {
		this.obitosAcumulado = obitosAcumulado;
	}

	public String getPopulacaoTCU2019() {
		return populacaoTCU2019;
	}

	public void setPopulacaoTCU2019(String populacaoTCU2019) {
		this.populacaoTCU2019 = populacaoTCU2019;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public String getIncidenciaObito() {
		return incidenciaObito;
	}

	public void setIncidenciaObito(String incidenciaObito) {
		this.incidenciaObito = incidenciaObito;
	}

}
