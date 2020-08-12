package dev.marcuspinto.rest.client;

public class EstadoRegiao {

	private String _id;
	private Interior interior;
	private Metropolitana metropolitana;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Interior getInterior() {
		return interior;
	}

	public void setInterior(Interior interior) {
		this.interior = interior;
	}

	public Metropolitana getMetropolitana() {
		return metropolitana;
	}

	public void setMetropolitana(Metropolitana metropolitana) {
		this.metropolitana = metropolitana;
	}

}
