package dev.marcuspinto.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.marcuspinto.util.CovidUtil;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ClientServiceTest {
	
	@Inject
	ClientService clientService;
	
	private Client client;
	
	@BeforeEach
	public void setup() throws NoSuchAlgorithmException {
		client = clientService.createClient("Name", 2);
	}

	@Test
	public void testCreateClient() {
		assertNotNull(client);
		assertEquals("Name", client.getName());
		assertEquals(2, client.getRateLimitPerMinute());
		assertEquals(CovidUtil.KEY_SIZE, client.getApiKey().length());
		assertTrue(client.getCreatedAt() instanceof Date);
	}

	@Test
	public void testCreateClientRequest() {
		clientService.createClientRequest(client.getApiKey());
	}

	@Test
	public void testGetClient() {
		Client result = clientService.getClient(client.getApiKey());
		assertEquals(client.getApiKey(), result.getApiKey());
	}

	@Test
	public void testCountRequests() {
		clientService.createClientRequest(client.getApiKey());
		clientService.createClientRequest(client.getApiKey());
		clientService.createClientRequest(client.getApiKey());
		Long result = clientService.countRequests(client.getApiKey());
		assertEquals(3, result);
	}		
	
}
