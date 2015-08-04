import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Tejaswita on 03-08-2015.
 */
public class MyStepdefs {
    @When("^I hit \"([^\"]*)\" $")
    public void I_hit(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Given("^I write the URL in the browser$")
    public void I_write_the_URL_in_the_browser() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^I should go to the \"([^\"]*)\"$")
    public void I_should_go_to_the(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
