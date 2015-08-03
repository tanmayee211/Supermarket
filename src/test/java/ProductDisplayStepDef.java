import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class ProductDisplayStepDef {
    WebDriver webDriver = new FirefoxDriver();


    @Given("^I open the application$")
    public void I_open_the_application() throws Throwable {
        webDriver.get("http://localhost:8080/supermarket");
    }

    @When("^The page loads$")
    public void The_page_loads() throws Throwable {
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Then("^I should see a list of products$")
    public void I_should_see_a_list_of_products() throws Throwable {
        assertThat(webDriver.findElement(By.id("productList")).getSize(),is(not(0)));
    }
}
