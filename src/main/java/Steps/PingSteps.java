package Steps;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static net.serenitybdd.rest.SerenityRest.given;



public class PingSteps {

    private Response response;
    private static RequestSpecification requestSpec;

    @Before
    public void init() {

        requestSpec = new RequestSpecBuilder()
                .build();
    }


    public void checkAPIAvailability() {
        response =
                given().
                        log().all().
                        spec(requestSpec).
                when().
                        get("https://restful-booker.herokuapp.com/ping");
    }
}

