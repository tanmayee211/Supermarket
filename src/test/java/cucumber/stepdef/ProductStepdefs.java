package cucumber.stepdef;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductStepdefs {

    WebDriver browser = new FirefoxDriver();

    @After
    public void closeBrowser(){
        browser.quit();
    }

    @Given("^The user is on home page$")
    public void The_user_is_on_home_page() throws Throwable {
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

    @When("^the page loads$")
    public void the_page_loads() throws Throwable {
        browser.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Then("^All products should be shown$")
    public void All_products_should_be_shown() throws Throwable {
        assertThat("No product shown.", browser.findElements(By.id("productList")).size(), is(not(0)));
    }


    @Given("^Empty shopping cart and A product - Dove Soap Unit price - (\\d+) exists$")
    public void Empty_shopping_cart_and_A_product_Dove_Soap_Unit_price_exists(int arg1) throws Throwable {

    }

    @When("^User adds (\\d+) Dove Soaps in the shopping cart$")
    public void User_adds_Dove_Soaps_in_the_shopping_cart(Integer quantity) throws Throwable {
       WebElement parentElement= browser.findElement(By.xpath("//div[div/div[contains(@class, 'label label-default ng-binding') and text() = 'Dove Soap']]"));
       parentElement.findElement(By.xpath(".//input[@type='number']")).sendKeys((quantity.toString()));
        parentElement.findElement((By.xpath(".//a[@class='btn btn-sm product-button']"))).click();
    }

    @Then("^The shopping cart contains (\\d+) Dove Soaps of unit price (\\d+) and total price is (\\d+)$")
    public void The_shopping_cart_contains_Dove_Soaps_of_unit_price_and_total_price_is(int quantity, int unitPrice, int totalPrice) throws Throwable {

    }

    @And("^clicks on the cart icon on nav-bar$")
    public void clicks_on_the_cart_icon_on_nav_bar() throws Throwable {
        browser.findElement(By.id("cart-button")).click();
    }
}
