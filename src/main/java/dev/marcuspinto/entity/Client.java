package dev.marcuspinto.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	@Id
	private String apiKey;
	private String name;
	private Integer rateLimitPerMinute;
	private Date createdAt;

	@OneToMany(mappedBy = "apiKey")
	private List<ClientRequest> requests;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRateLimitPerMinute() {
		return rateLimitPerMinute;
	}

	public void setRateLimitPerMinute(Integer rateLimitPerMinute) {
		this.rateLimitPerMinute = rateLimitPerMinute;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<ClientRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<ClientRequest> requests) {
		this.requests = requests;
	}

}
