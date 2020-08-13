package dev.marcuspinto.resource;

import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.marcuspinto.entity.Client;
import dev.marcuspinto.entity.ClientService;
import dev.marcuspinto.rest.client.CovidService;
import dev.marcuspinto.rest.client.Estado;
import dev.marcuspinto.rest.client.EstadoRegiao;
import dev.marcuspinto.rest.client.Geral;

@Path("/covid")
public class CovidResource {
	
	@Inject
    @RestClient
    CovidService covidService;
	
	@Inject
	ClientService clientService;

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Client register(Register register) throws NoSuchAlgorithmException {
		return clientService.createClient(register.getName(), register.getRateLimitPerMinute());
	}

	@GET
    @Path("/general")
    @Produces(MediaType.APPLICATION_JSON)
    public Geral general(@QueryParam("api-key") String apiKey) {
		validate(apiKey);
        return covidService.getGeral();
    }
	
	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Estado> state(@QueryParam("api-key") String apiKey) {
		validate(apiKey);
		return covidService.getEstado();
	}
	
	@GET
	@Path("/stateRegion")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<EstadoRegiao> stateRegion(@QueryParam("api-key") String apiKey) {
		validate(apiKey);
		return covidService.getEstadoRegiao();
	}

	private void validate(String apiKey) {
		Client client = clientService.getClient(apiKey);
		if (client == null) {
			throw new WebApplicationException("Unauthorized.", 401);
		}
		if (clientService.countRequests(apiKey) >= client.getRateLimitPerMinute()) {
			throw new WebApplicationException("Too Many Requests.", 429);
		}
		clientService.createClientRequest(apiKey);
	}
	
}
