package dev.marcuspinto.entity;

import java.io.Serializable;
import java.util.Date;

public class ClientRequestId implements Serializable {

	private static final long serialVersionUID = -4736077942939001650L;
	
	private String apiKey;
	private Date dateTime;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
