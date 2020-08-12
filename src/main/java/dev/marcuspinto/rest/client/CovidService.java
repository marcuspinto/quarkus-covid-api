package dev.marcuspinto.rest.client;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/prod")
@RegisterRestClient(configKey="covid-api")
public interface CovidService {

    @GET
    @Path("/PortalGeralApi")
    @Produces("application/json")
    Geral getGeral();
    
    @GET
    @Path("/PortalEstado")
    @Produces("application/json")
    Set<Estado> getEstado();
    
    @GET
    @Path("/PortalEstadoRegiao")
    @Produces("application/json")
    Set<EstadoRegiao> getEstadoRegiao();    
    
}
