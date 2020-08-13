package dev.marcuspinto.resource;

public class Register {

	private String name;
	private Integer rateLimitPerMinute;

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

}
