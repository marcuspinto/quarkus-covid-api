package dev.marcuspinto.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.marcuspinto.entity.Client;
import dev.marcuspinto.entity.ClientService;
import dev.marcuspinto.rest.client.Cadastro;
import dev.marcuspinto.util.CovidUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;

@QuarkusTest
public class CovidResourceTest {
	
	private static final String API_KEY = "111111111111111111111111111111111111";
	private static final String API_KEY2 = "222222222222222222222222222222222222";
	private static final String NAME = "Teste";
	private static final Integer RATE_LIMIT = 2; 
	
	@InjectMock
	ClientService clientService;
	
	@BeforeEach
	public void setup() throws NoSuchAlgorithmException {
    	Client client = new Client();
    	client.setName(NAME);
    	client.setRateLimitPerMinute(RATE_LIMIT);
    	client.setApiKey(API_KEY);
    	client.setCreatedAt(new Date());
    	
    	Mockito.when(clientService.getClient(API_KEY)).thenReturn(client);
    	Mockito.when(clientService.getClient(API_KEY2)).thenReturn(client);
    	Mockito.when(clientService.createClient(NAME, RATE_LIMIT)).thenReturn(client);
    	Mockito.when(clientService.countRequests(API_KEY2)).thenReturn(3L);
	}
	
    @Test
    public void testCadastro() {
    	
    	Cadastro cadastro = new Cadastro();
    	cadastro.setName(NAME);
    	cadastro.setRateLimitPerMinute(RATE_LIMIT);
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String cadastroJson = jsonb.toJson(cadastro);
    	
        given().body(cadastroJson)
    	  .contentType(ContentType.JSON)
          .when().post("/covid/cadastro")
          .then()
             .statusCode(200)
             .body("name", is(cadastro.getName()),
            		 "rateLimitPerMinute", is(cadastro.getRateLimitPerMinute()),
            		 "apiKey", Matchers.hasLength(CovidUtil.KEY_SIZE),
            		 "$", Matchers.hasKey("createdAt"));
    }
    
    @Test
    public void testGeralUnauthorized() {
        given()
          .when().get("/covid/geral")
          .then()
             .statusCode(401);
    }
    
    @Test
    public void testGeralManyRequests() {
        given()
          .queryParams("api-key", API_KEY2)
          .when().get("/covid/geral")
          .then()
             .statusCode(429);
    }
    
    @Test
    public void testGeral() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/geral")
          .then()
             .statusCode(200)
             .body("$", Matchers.hasKey("confirmados"),
            		 "$", Matchers.hasKey("obitos"));
    }
    
    @Test
    public void testEstadoUnauthorized() {
        given()
          .when().get("/covid/estado")
          .then()
             .statusCode(401);
    }
    
    @Test
    public void testEstado() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/estado")
          .then()
             .statusCode(200)
             .body("$.size()", is(27),
            		 "[0]", Matchers.hasKey("_id"),
            		 "[0]", Matchers.hasKey("nome"));
    }
    
    @Test
    public void testEstadoRegiaoUnauthorized() {
        given()
          .when().get("/covid/estadoRegiao")
          .then()
             .statusCode(401);
    }
    
    @Test
    public void testEstadoRegiao() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/estadoRegiao")
          .then()
             .statusCode(200)
             .body("$.size()", is(27),
            		 "[0]", Matchers.hasKey("_id"),
            		 "[0]", Matchers.hasKey("interior"),
            		 "[0]", Matchers.hasKey("metropolitana"));
    }

}
