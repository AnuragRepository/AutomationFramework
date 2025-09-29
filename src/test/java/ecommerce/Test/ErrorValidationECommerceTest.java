package ecommerce.Test;

import ecommerce.PageObjects.*;
import ecommerce.TestComponents.BaseTest;
import ecommerce.TestComponents.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class ErrorValidationECommerceTest extends BaseTest {

    String emailID2 = "test486test486@gmail.com";
    String incorrectemailID = "est786test786@gmail.com";
    String password = "Automation@01";
    String[] arrItems = {"ZARA COAT 3", "ADIDAS ORIGINAL"};
    String[] arrItemsIncorrect = {"ZARA COAT 3","ADIDAS"};
    String textcountrytoSelect = "Ind";
    String countrytoSelect = "India";


    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyLoginErrorMessage() throws IOException, InterruptedException {
        ProductCataloguePage ProductCataloguePage = landingPage.Login(incorrectemailID, password);
        Assert.assertEquals(landingPage.getloginErrorMessage(), "ncorrect email or password.");
    }
    @Test(groups = {"anuraggroup1"})
    public void verifyIncorrectProductInCart() throws IOException, InterruptedException {
        ProductCataloguePage ProductCataloguePage = landingPage.Login(emailID2, password);
        ProductCataloguePage.selectAndAddProductToCart(arrItems);
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertFalse(cartPage.getItemsInCart().containsAll(Arrays.asList(arrItemsIncorrect)));
    }


}

