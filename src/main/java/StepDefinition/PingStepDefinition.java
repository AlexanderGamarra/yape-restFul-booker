package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import Steps.PingSteps;
public class PingStepDefinition {
    @Steps
    PingSteps pingSteps;

    TokenStepDefinition tokenStepDefinition;

    public PingStepDefinition(){
        tokenStepDefinition = new TokenStepDefinition();
    }

    @When("Consulto la disponibilidad de los servicios")
    public void checkAPIAvailability() {
        pingSteps.checkAPIAvailability();
    }

    @Given("Las apis se encuentran disponibles")
    public void lasApisSeEncuentranDisponibles() {
        checkAPIAvailability();
        tokenStepDefinition.verifyStatusCode(201);
    }
}
