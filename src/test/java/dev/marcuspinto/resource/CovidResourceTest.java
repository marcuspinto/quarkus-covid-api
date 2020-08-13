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
    public void testRegister() {
    	
    	Register register = new Register();
    	register.setName(NAME);
    	register.setRateLimitPerMinute(RATE_LIMIT);
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	String registerJson = jsonb.toJson(register);
    	
        given().body(registerJson)
    	  .contentType(ContentType.JSON)
          .when().post("/covid/register")
          .then()
             .statusCode(200)
             .body("name", is(register.getName()),
            		 "rateLimitPerMinute", is(register.getRateLimitPerMinute()),
            		 "apiKey", Matchers.hasLength(CovidUtil.KEY_SIZE),
            		 "$", Matchers.hasKey("createdAt"));
    }
    
    @Test
    public void testGeneralUnauthorized() {
        given()
          .when().get("/covid/general")
          .then()
             .statusCode(401);
    }
    
    @Test
    public void testGeneralManyRequests() {
        given()
          .queryParams("api-key", API_KEY2)
          .when().get("/covid/general")
          .then()
             .statusCode(429);
    }
    
    @Test
    public void testGeneral() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/general")
          .then()
             .statusCode(200)
             .body("$", Matchers.hasKey("confirmados"),
            		 "$", Matchers.hasKey("obitos"));
    }
    
    @Test
    public void testStateUnauthorized() {
        given()
          .when().get("/covid/state")
          .then()
             .statusCode(401);
    }    
    
    @Test
    public void testStateManyRequests() {
        given()
          .queryParams("api-key", API_KEY2)
          .when().get("/covid/state")
          .then()
             .statusCode(429);
    }
    
    @Test
    public void testState() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/state")
          .then()
             .statusCode(200)
             .body("$.size()", is(27),
            		 "[0]", Matchers.hasKey("_id"),
            		 "[0]", Matchers.hasKey("nome"));
    }
    
    @Test
    public void testStateRegionUnauthorized() {
        given()
          .when().get("/covid/stateRegion")
          .then()
             .statusCode(401);
    }
    
    @Test
    public void testStateRegionManyRequests() {
        given()
          .queryParams("api-key", API_KEY2)
          .when().get("/covid/stateRegion")
          .then()
             .statusCode(429);
    }
    
    @Test
    public void testStateRegion() {
        given()
          .queryParams("api-key", API_KEY)
          .when().get("/covid/stateRegion")
          .then()
             .statusCode(200)
             .body("$.size()", is(27),
            		 "[0]", Matchers.hasKey("_id"),
            		 "[0]", Matchers.hasKey("interior"),
            		 "[0]", Matchers.hasKey("metropolitana"));
    }

}
