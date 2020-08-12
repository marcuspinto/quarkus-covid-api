package dev.marcuspinto.rest.client;

public class Regiao {
	
	private String _id;
	private String casosAcumulado;
	private String obitosAcumulado;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

}
