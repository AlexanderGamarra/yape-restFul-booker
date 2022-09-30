package StepDefinition;

import Entities.Utils;
import Steps.BookingSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class BookingStepDefinition {
    @Steps
    BookingSteps bookingSteps;

    @When("El usuario consulta todas las reservas del sistema")
    public void getAllBooksId() {
        bookingSteps.getAllBooksId();
    }
    @When("El usuario consulta todas las reservas del sistema por nombres: {string} y {string}")
    public void getAllBooksByNames(String firstName, String lastName) {
        bookingSteps.getAllBooksByNames(firstName,lastName);
    }

    @When("El usuario consulta todas las reservas del sistema por fechas: {string} y {string}")
    public void getAllBooksByDate(String checkIn, String checkOut) {
        bookingSteps.getAllBooksByDate(checkIn,checkOut);
    }

    @And("Verifico que no exista un id repetido")
    public void searchingDuplicatesID() throws Exception {
        bookingSteps.searchingDuplicatesID();
    }

    @And("Se obtiene un ID existente")
    public void getExistingID() {
        bookingSteps.getAllBooksId();
        bookingSteps.getFirstID();
    }

    @When("Consultar el detalle de la reserva con el ID obtenido")
    public void getBookDetailByID() {
        bookingSteps.getBookDetailByID(Utils.id);
    }

    @And("Verifico los datos de la reserva")
    public void verifyBookingInfo() {
        bookingSteps.verifyBookingInfo();
    }

    @When("Consultar el detalle de la reserva con el ID {int}")
    public void getBookDetailByID(String id) {
        bookingSteps.getBookDetailByID(id);
    }
}
