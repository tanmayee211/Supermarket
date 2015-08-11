import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@addProduct"},features = "src/test/resources/feature",glue ="cucumber.stepdef",format = "pretty")
public class RunCukesTest {
}
