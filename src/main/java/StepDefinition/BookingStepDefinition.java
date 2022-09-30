package StepDefinition;

import Entities.Booking;
import Entities.Dates;
import Entities.Utils;
import Steps.BookingSteps;
import com.sun.istack.NotNull;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.assertj.core.api.Fail.fail;

public class BookingStepDefinition {
    @Steps
    BookingSteps bookingSteps;
    TokenStepDefinition tokenStepDefinition;

    public BookingStepDefinition(){
        tokenStepDefinition = new TokenStepDefinition();
    }

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

    @When("Consultar el detalle de la reserva con el ID {string}")
    public void getBookDetailByID(String id) {
        bookingSteps.getBookDetailByID(id);
    }

    @Given("Se creará un usuario con la siguiente información")
    public void loadingData(@NotNull DataTable table) {

        try{
            List<List<Object>> rows = table.asLists(String.class);
            int count = 0;
            for (List<Object> columns : rows) {
                count++;
                if(count==2)
                {
                    Dates dates = new Dates(columns.get(4).toString(),columns.get(5).toString());
                    Utils.booking = new Booking(0,
                            columns.get(0).toString(),
                            columns.get(1).toString(),
                            Integer.parseInt(columns.get(2).toString()),
                            Boolean.parseBoolean(columns.get(3).toString()),
                            dates,
                            columns.get(6).toString());
                }


            }
        }catch (Exception e){fail("Ocurrió un error al guardar la información ingresada verificar los valores.");}

    }

    @When("Cuando se ingresa la información en la api de registro")
    public void createBooking() {
        bookingSteps.createBooking(Utils.booking);
    }

    @And("Verifico que la reserva se haya creado exitosamente")
    public void verifyCreatedData() {
        bookingSteps.verifyBookingCreatedRecently();

        //Double check - Go to the getBookingInfoById and verify data again
        bookingSteps.getBookDetailByID(Utils.id);
        tokenStepDefinition.verifyStatusCode(200);
        bookingSteps.verifyBookingInfo(Utils.booking);
    }

    @When("Se actualiza la reserva con la siguiente información")
    public void updatePartBookingInfo(@NotNull DataTable table) {

        try{
            List<List<Object>> rows = table.asLists(String.class);
            int count = 0;
            for (List<Object> columns : rows) {
                count++;
                if(count==2)
                {
                    Dates dates = new Dates(columns.get(4).toString(),columns.get(5).toString());
                    Utils.booking = new Booking(0,
                            columns.get(0).toString(),
                            columns.get(1).toString(),
                            Integer.parseInt(columns.get(2).toString()),
                            Boolean.parseBoolean(columns.get(3).toString()),
                            dates,
                            columns.get(6).toString());
                }
            }

            bookingSteps.updateBooking(Utils.booking,Utils.token,Utils.id);
        }catch (Exception e){fail("Ocurrió un error al guardar la información ingresada verificar los valores.");}

    }

    @And("Verifico que la reserva se haya actualizado exitosamente")
    public void verificoQueLaReservaSeHayaActualizadoExitosamente() {
        bookingSteps.verifyBookingUpdatedRecently();

        //Double check - Go to the getBookingInfoById and verify data again
        bookingSteps.getBookDetailByID(Utils.id);
        tokenStepDefinition.verifyStatusCode(200);
        bookingSteps.verifyBookingInfo(Utils.booking);
    }

    @When("Se actualiza los nombres de la reserva con la siguiente información")
    public void updateBookingInfo(@NotNull DataTable table) {

        try {
            List<List<Object>> rows = table.asLists(String.class);
            int count = 0;
            for (List<Object> columns : rows) {
                count++;
                if (count == 2) {
                    Utils.booking = new Booking();
                    Utils.booking.setFirstname(columns.get(0).toString());
                    Utils.booking.setLastname(columns.get(1).toString());
                }
            }

            bookingSteps.updatePartialInfoBooking(Utils.booking, Utils.token, Utils.id);
        } catch (Exception e) {
            fail("Ocurrió un error al guardar la información ingresada verificar los valores.");
        }
    }

    @And("Verifico que los nombres de la reserva se hayan actualizado exitosamente")
    public void verifyPartialInfoUpdated() {
        bookingSteps.verifyPartialInfoUpdated();
    }

    @When("Se elimina la reserva del ID obtenido")
    public void deleteBookingById() {
        bookingSteps.deleteBookingById(Utils.id);
    }

    @And("Verifico que la reserva se haya eliminado exitosamente")
    public void verifyBookingDeletedRecently() {
        bookingSteps.getBookDetailByID(Utils.id);
        tokenStepDefinition.verifyStatusCode(404);
    }
}
