package StepDefinition;

import Steps.TokenSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class TokenStepDefinition {
    @Steps
     TokenSteps tokenSteps;

    public TokenStepDefinition(){tokenSteps = new TokenSteps();}
    @Given("Existe un usuario registrado en el sistema")
    public void givenUserExists() {}

    @When("Consulto el acceso para el usuario: {string} y contraseña {string}")
    public void getTokenId(String userName, String password) {
        tokenSteps.getTokenId(userName,password);
    }

    @Then("Verifico que el status de la API sea {int}")
    public void verifyStatusCode(int status) {
        tokenSteps.verifyStatusCode(status);
    }

    @And("Verifico que se muestre el token en el body response")
    public void verificoQueSeMuestreElTokenEnElBodyResponse() {
        tokenSteps.verifyTokenAPIBodyResponse();
    }

    @Given("El usuario no esta registrado en el sistema")
    public void elUsuarioNoEstaRegistradoEnElSistema() {
    }

    @And("Se obtiene el token")
    public void getToken() {
        getTokenId("admin","password123");
        tokenSteps.getToken();
    }
}
