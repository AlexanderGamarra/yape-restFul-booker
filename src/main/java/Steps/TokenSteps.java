package Steps;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class TokenSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenSteps.class);


    private Response response;
    private List<String> jsonResponse;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private EnvironmentVariables environmentVariables;

    @Before
    public void init() {

        requestSpec = new RequestSpecBuilder()
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public TokenSteps(){}

    public void getTokenId(String userName, String password) {

        String bodyRequest = "{\n" +
                            "    \"username\" : \"{username}\",\n" +
                            "    \"password\" : \"{password}\"\n" +
                            "}";
        bodyRequest = bodyRequest.replace("{username}",userName).replace("{password}",password);
        response =
                given().
                        log().all().
                        spec(requestSpec).
                        contentType("application/json").
                        body(bodyRequest).
                when().
                        post("https://restful-booker.herokuapp.com/auth").
                then()
                        .contentType(ContentType.JSON).extract().response();
    }

    public void verifyStatusCode(int statusCode) {
        lastResponse().getBody().prettyPrint();
        Assert.assertEquals(lastResponse().getStatusCode(), statusCode);
    }
    public void verifyTokenAPIBodyResponse() {
        if(lastResponse().getBody().path("token")==null){fail("No existe la variable token en el body response");}
        if(lastResponse().getBody().path("token").equals("")){fail("La variable token se encuentra vacía");}
    }
}

