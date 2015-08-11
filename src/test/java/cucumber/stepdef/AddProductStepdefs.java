package cucumber.stepdef;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class AddProductStepdefs {

    WebDriver browser = new FirefoxDriver();

    @Given("^I open the website$")
    public void I_open_the_website() throws Throwable {
        browser.get("http://localhost:8080/supermarket");
    }

    @When("^vendor clicks on Add product$")
    public void vendor_clicks_on_Add_product() throws Throwable {
      browser.findElement(By.id("addProduct")).click();
    }

    @And("^enters product name : \"([^\"]*)\" and price : \"([^\"]*)\"$")
    public void enters_product_name_and_price_(String name, String price) throws Throwable {
        browser.findElement(By.id("productName")).sendKeys(name);
        browser.findElement(By.id("productPrice")).sendKeys(price);
        browser.findElement(By.id("submit")).click();
    }

    @Then("^A success message is displayed$")
    public void A_success_message_is_displayed() throws Throwable {
        String message = browser.findElement(By.id("message")).getText();
        assertThat(message, equalTo("Successfully added!"));

    }
}
