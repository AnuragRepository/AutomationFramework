package ecommerce.Test;

import ecommerce.PageObjects.*;
import ecommerce.TestComponents.BaseTest;
import ecommerce.TestComponents.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class E2EFlowECommerceTest extends BaseTest {

    String emailID = "test786test786@gmail.com";
    String password = "Automation@01";
    String[] arrItems = {"ZARA COAT 3", "ADIDAS ORIGINAL"};
    String textcountrytoSelect = "Ind";
    String countrytoSelect = "India";
    String dateOfOrder = "";


    @Test(groups = {"anuraggroup1"},retryAnalyzer = RetryAnalyzer.class)

    public void submitOrderTest() throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(emailID, password);
        ProductCataloguePage.selectAndAddProductToCart(arrItems);
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(), arrItems);
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();
        System.out.println("dateOfOrder = "+dateOfOrder);



    }

    @Test(dependsOnMethods = {"submitOrderTest"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyOrderHistory() {
        ProductCataloguePage ProductCataloguePage = landingPage.Login(emailID, password);
        OrderHistoryPage orderHistoryPage = ProductCataloguePage.goToOrderPage();
        Assert.assertTrue(orderHistoryPage.getOrderItems(dateOfOrder).containsAll(Arrays.asList(arrItems)));// equal case not used due to existing items in order page
    }




}

