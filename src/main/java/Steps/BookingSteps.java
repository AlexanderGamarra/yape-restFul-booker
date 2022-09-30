package Steps;

import Entities.Utils;
import Entities.Booking;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.thucydides.core.util.EnvironmentVariables;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.junit.Assert.fail;


public class BookingSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingSteps.class);


    private Response response;
    private List<String> jsonResponse;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private EnvironmentVariables environmentVariables;
    public static Utils utilities;

    @Before
    public void init() {

        requestSpec = new RequestSpecBuilder()
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void getAllBooksId() {
        response =
                given().
                    log().all().
                    spec(requestSpec).
                when().
                    get("https://restful-booker.herokuapp.com/booking").
                then().
                    contentType(ContentType.JSON).extract().response();
    }

    public void getAllBooksByNames(String firstName, String lastName) {
        response =
                given().
                        log().all().
                        spec(requestSpec).
                        queryParam("firstname",firstName).
                        queryParam("lastname",lastName).
                when().
                        get("https://restful-booker.herokuapp.com/booking").
                then().
                        contentType(ContentType.JSON).extract().response();
    }

    public void getAllBooksByDate(String checkIn, String checkOut) {
        response =
                given().
                        log().all().
                        spec(requestSpec).
                        queryParam("checkin",checkIn).
                        queryParam("checkout",checkOut).
                when().
                        get("https://restful-booker.herokuapp.com/booking").
                then().
                        contentType(ContentType.JSON).extract().response();
    }

    public void getBookDetailByID(String id) {

        response =
                given().
                        log().all().
                        spec(requestSpec).
                        when().
                        get("https://restful-booker.herokuapp.com/ping");
        response =
                given().
                        log().all().
                        spec(requestSpec).
                when().
                        get("https://restful-booker.herokuapp.com/booking/"+id);

    }
    public void searchingDuplicatesID() throws Exception {
        try{
            List<Map<String, String>> bodyResponse = lastResponse().getBody().jsonPath().getList("");
            if(bodyResponse.size()==0){throw new Exception("No existen reservas");}
            List<Booking> bookingsId = new ArrayList<>();

            //Saving Bookings
            for (Map<String, String> element : bodyResponse) {
                Booking booking = new Booking();
                booking.setBookingid(String.valueOf((element.get("bookingid"))));
                bookingsId.add(booking);
            }

            //Searching duplicates Id
            for (int i = 0; i < bookingsId.size(); i++)
            {
                for (int j = i + 1; j < bookingsId.size(); j++)
                {
                    if (bookingsId.get(i).bookingid == bookingsId.get(j).bookingid) {
                        fail("El siguiente id esta repetido:" + bookingsId.get(j).bookingid);
                    }
                }
            }
        }catch (Exception e){
            fail("Un error ocurrió al intentar obtener los registros.");
        }
    }

    public void getFirstID() {
        try{
            List<Map<String, String>> bodyResponse = lastResponse().getBody().jsonPath().getList("");
            if(bodyResponse.size()==0){throw new Exception("No existen reservas");}
            Utils.id = String.valueOf(bodyResponse.get(0).get("bookingid"));
        }catch (Exception e){
            fail("Un error ocurrió al intentar obtener el id."+e.getMessage());
        }
    }

    public void verifyBookingInfo() {
        try{
            JSONObject object = new JSONObject(lastResponse().getBody().asString());
            if(object.get("firstname").equals("") && object.get("lastname").equals("") &&
                    object.get("totalprice").toString().equals("") && object.get("bookingdates").toString().equals("")){
                fail("Hay una variable que se encuentra vacia");
            }

        }catch (Exception e){
            fail("Una variable que no existe o cambio de nombre");
        }
    }
}

