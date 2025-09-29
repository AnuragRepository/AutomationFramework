package cucumber;

import ecommerce.PageObjects.*;
import ecommerce.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StepDefinition extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage ProductCataloguePage;
    public CartPage cartPage;
    public CheckoutReviewPage CheckoutReviewPage;
    public ConfirmationPage ConfirmationPage;

    //Given Open ecommerce Login page
    @Given("Open ecommerce Login page")
    public void Open_ecommerce_Login_page() throws IOException {

        landingPage = launchApplication();
    }

    //Given Login with username <emailID> and password <password>
    @Given("^Login with username (.+) and password (.+)$")
    public void Login_with_username_password (String emailID, String password )
    {
        ProductCataloguePage = landingPage.Login(emailID, password);
    }

    //When  Select products <product> and add to cart
    @When("^Select products (.+) and add to cart$")
    public void Select_products_and_add_to_cart(String items) throws InterruptedException
    {
        ProductCataloguePage.selectAndAddProductToCart(items.split(","));
        ProductCataloguePage.loadingIcon();
    }

    //And    Verify added product <arrItems> in cart,select country <textcountrytoSelect>,<countrytoSelect> and checkout
    @And("^Verify added product (.+) in cart and checkout by select country (.+),(.+)$")
    public void Verify_added_product_in_cart_and_checkout_by_select_country(String items,String textcountrytoSelect, String countrytoSelect)
    {
        cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().stream().map(s->s.toUpperCase()).collect(Collectors.toList()).toArray(), (Arrays.stream(items.split(",")).map(s->s.toUpperCase())).collect(Collectors.toList()).toArray());
        CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
    }

    //Then  Verify message "THANKYOU FOR THE ORDER."
    @Then("Verify success message {string}")
    public void Verify_success_message(String string)
    {
        ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(),string);
        tearDown();
    }

    @Then("Verify error message {string}")
    public void verify_ErrorMessage(String errorMessage) {
        Assert.assertEquals(landingPage.getloginErrorMessage(), errorMessage);
        tearDown();
    }
}
