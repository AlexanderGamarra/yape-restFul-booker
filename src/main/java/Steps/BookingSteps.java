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

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.junit.Assert.assertEquals;
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
            fail("Una variable cambio de nombre o no existe");
        }
    }

    public void verifyBookingInfo(Booking booking) {
        try{
            assertEquals(booking.getFirstname(),lastResponse().getBody().path("firstname").toString());
            assertEquals(booking.getLastname(),lastResponse().getBody().path("lastname").toString());
            assertEquals(String.valueOf(booking.getTotalprice()),lastResponse().getBody().path("totalprice").toString());
            assertEquals(String.valueOf(booking.isDepositpaid()),lastResponse().getBody().path("depositpaid").toString());
            assertEquals(booking.getBookingdates().getCheckin(),lastResponse().getBody().path("bookingdates.checkin").toString());
            assertEquals(booking.getBookingdates().getCheckout(),lastResponse().getBody().path("bookingdates.checkout").toString());
            assertEquals(booking.getAdditionalneeds(),lastResponse().getBody().path("additionalneeds").toString());

        }catch (Exception e){
            fail("Una variable cambio de nombre o no existe");
        }
    }

    public void createBooking(Booking booking) {
       response =
                given().
                        log().all().
                        contentType("application/json").
                        spec(requestSpec).body(booking).
                when().
                        post("https://restful-booker.herokuapp.com/booking").
                then().
                        contentType(ContentType.JSON).
                        extract().response();
    }

    public void verifyBookingCreatedRecently() {
        try{

            Utils.id = String.valueOf(lastResponse().getBody().path("bookingid").toString());

            assertEquals(Utils.booking.getFirstname(),lastResponse().getBody().path("booking.firstname").toString());
            assertEquals(Utils.booking.getLastname(),lastResponse().getBody().path("booking.lastname").toString());
            assertEquals(String.valueOf(Utils.booking.getTotalprice()),lastResponse().getBody().path("booking.totalprice").toString());
            assertEquals(String.valueOf(Utils.booking.isDepositpaid()),lastResponse().getBody().path("booking.depositpaid").toString());
            assertEquals(Utils.booking.getBookingdates().getCheckin(),lastResponse().getBody().path("booking.bookingdates.checkin").toString());
            assertEquals(Utils.booking.getBookingdates().getCheckout(),lastResponse().getBody().path("booking.bookingdates.checkout").toString());
            assertEquals(Utils.booking.getAdditionalneeds(),lastResponse().getBody().path("booking.additionalneeds").toString());

        }catch (Exception e){
            fail("Una variable cambio de nombre o no existe");
        }
    }
    public void verifyBookingUpdatedRecently() {
        try{

            assertEquals(Utils.booking.getFirstname(),lastResponse().getBody().path("firstname").toString());
            assertEquals(Utils.booking.getLastname(),lastResponse().getBody().path("lastname").toString());
            assertEquals(String.valueOf(Utils.booking.getTotalprice()),lastResponse().getBody().path("totalprice").toString());
            assertEquals(String.valueOf(Utils.booking.isDepositpaid()),lastResponse().getBody().path("depositpaid").toString());
            assertEquals(Utils.booking.getBookingdates().getCheckin(),lastResponse().getBody().path("bookingdates.checkin").toString());
            assertEquals(Utils.booking.getBookingdates().getCheckout(),lastResponse().getBody().path("bookingdates.checkout").toString());
            assertEquals(Utils.booking.getAdditionalneeds(),lastResponse().getBody().path("additionalneeds").toString());

        }catch (Exception e){
            fail("Una variable cambio de nombre o no existe");
        }
    }

    public void updateBooking(Booking booking,String token,String id) {
        response =
                given().
                        log().all().
                        header("Cookie","token="+token).
                        contentType("application/json").
                        spec(requestSpec).body(booking).
                when().
                        put("https://restful-booker.herokuapp.com/booking/"+id).
                then().
                        contentType(ContentType.JSON).
                        extract().response();
    }

    public void updatePartialInfoBooking(Booking booking, String token, String id) {
        String bodyRequest = "{\n" +
                "    \"firstname\": \"{firstname}\",\n" +
                "    \"lastname\": \"{lastname}\"\n" +
                "}";
        bodyRequest = bodyRequest.replace("{firstname}",booking.getFirstname()).replace("{lastname}",booking.getLastname());
        response =
                given().
                        log().all().
                        header("Cookie","token="+token).
                        contentType("application/json").
                        spec(requestSpec).
                        body(bodyRequest).
                when().
                        patch("https://restful-booker.herokuapp.com/booking/"+id).
                then().
                        contentType(ContentType.JSON).
                        extract().response();
    }

    public void verifyPartialInfoUpdated() {
        assertEquals(Utils.booking.getFirstname(),lastResponse().getBody().path("booking.firstname").toString());
        assertEquals(Utils.booking.getLastname(),lastResponse().getBody().path("booking.lastname").toString());
    }

    public void deleteBookingById(String id) {
        response =
                given().
                        log().all().
                        header("Cookie","token="+Utils.token).
                        spec(requestSpec).
                when().
                        delete("https://restful-booker.herokuapp.com/booking/"+id);
    }
}

