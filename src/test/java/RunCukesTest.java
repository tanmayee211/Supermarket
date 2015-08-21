import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@addToCart"},features = "src/test/resources/feature",glue ="cucumber.stepdef",format = "pretty")
public class RunCukesTest {
}
