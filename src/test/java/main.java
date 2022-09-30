import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = "@asa",
        plugin = {"pretty"}
)
public class main {
    public static String ANSI_GREEN = "\u001B[32m";
    public static final String WELCOME ="Test Start";

    @BeforeClass
    public static void messages() {
        System.out.println(ANSI_GREEN + WELCOME + ANSI_GREEN);
    }

    @AfterClass
    public static void afterTests() {}

}
