package dev.marcuspinto.entity;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import dev.marcuspinto.util.CovidUtil;

@ApplicationScoped
public class ClientService {
	
	@Inject
    EntityManager em;

	@Transactional
	public Client createClient(String name, Integer rateLimitPerMinute) throws NoSuchAlgorithmException {
		Client client = new Client();
		client.setApiKey(CovidUtil.getKey());
		client.setName(name);
		client.setRateLimitPerMinute(rateLimitPerMinute);
		client.setCreatedAt(new Date());
		em.persist(client);
		return client;
	}
	
	@Transactional
	public void createClientRequest(String apiKey) {
		ClientRequest clientRequest = new ClientRequest();
		clientRequest.setApiKey(apiKey);
		clientRequest.setDateTime(new Date());
		em.persist(clientRequest);
	}
	
	@Transactional
	public Client getClient(String apiKey) {
		return em.createQuery("SELECT c FROM Client c WHERE c.apiKey = :apiKey", Client.class)
				.setParameter("apiKey", apiKey)
				.getResultList().stream().findFirst().orElse(null);
	}
	
	@Transactional
	public Long countRequests(String apiKey) {
		LocalDateTime time = LocalDateTime.now().minusMinutes(1);
		Date date = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(r) FROM ClientRequest r ");
		query.append("WHERE r.apiKey = :apiKey ");
		query.append("AND r.dateTime > :date ");
		return em.createQuery(query.toString(), Long.class)
				.setParameter("apiKey", apiKey)
				.setParameter("date", date, TemporalType.TIMESTAMP)
				.getResultList().stream().findFirst().orElse(null);
	}
	
}
