package ecommerce.Test;

import ecommerce.PageObjects.*;
import ecommerce.TestComponents.BaseTest;
import ecommerce.TestComponents.RetryAnalyzer;
import ecommerce.data.Person;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class DataProviderECommerceTest extends BaseTest {

    String emailID = "test786test786@gmail.com";
    String password = "Automation@01";
    String[] arrItems = {"ZARA COAT 3", "ADIDAS ORIGINAL"};
    String[] arrItems2 = {"ZARA COAT 3", "iphone 13 pro"};
    String textcountrytoSelect = "Ind";
    String countrytoSelect = "India";
    String dateOfOrder = "";
    String emailID2 = "test486test486@gmail.com";


    @Test(dataProvider = "getDataViaObjectArrayConventional")

    public void submitOrderTestGetDataViaObjectArrayConventional(String user, String pass, String productArray[]) throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(user, pass);
        ProductCataloguePage.selectAndAddProductToCart(productArray);
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(), List.of(productArray).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).toArray());
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();

    }

    @DataProvider
    public Object[][] getDataViaObjectArrayConventional() {
        return new Object[][]{{emailID, password, arrItems}, {emailID2, password, arrItems2}};
    }

    @Test(dataProvider = "getDataViaHashMapList",retryAnalyzer = RetryAnalyzer.class)

    public void submitOrderTestGetDataViaHashMapList(HashMap<String, List<String>> input) throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(input.get("emailID").get(0), input.get("password").get(0));
        ProductCataloguePage.selectAndAddProductToCart(input.get("arrItems1").toArray(new String[0]));
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(), (input.get("arrItems1")).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).toArray());
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();

    }

    @DataProvider
    public Object[][] getDataViaHashMapList() {
        HashMap<String, List<String>> hm1 = new HashMap<>();
        hm1.put("emailID", List.of("test786test786@gmail.com"));
        hm1.put("password", List.of("Automation@01"));
        hm1.put("arrItems1", List.of("ZARA COAT 3", "ADIDAS ORIGINAL"));

        HashMap<String, List<String>> hm2 = new HashMap<>();
        hm2.put("emailID", List.of("test486test486@gmail.com"));
        hm2.put("password", List.of("Automation@01"));
        hm2.put("arrItems1", List.of("ZARA COAT 3", "iphone 13 pro"));

        return new Object[][]{{hm1}, {hm2}};
    }


    @Test(dataProvider = "getDataViaHashMapArray")

    public void submitOrderTestGetDataViaHashMapArray(HashMap<String, String[]> input) throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(input.get("emailID")[0], input.get("password")[0]);
        ProductCataloguePage.selectAndAddProductToCart(input.get("arrItems1"));
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(), (Arrays.asList(input.get("arrItems1"))).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).toArray());
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();

    }

    @DataProvider
    public Object[][] getDataViaHashMapArray() {
        HashMap<String, String[]> hm1 = new HashMap<>();
        hm1.put("emailID", new String[]{"test786test786@gmail.com"});
        hm1.put("password", new String[]{"Automation@01"});
        hm1.put("arrItems1", new String[]{"ZARA COAT 3", "ADIDAS ORIGINAL"});

        HashMap<String, String[]> hm2 = new HashMap<>();
        hm2.put("emailID", new String[]{"test486test486@gmail.com"});
        hm2.put("password", new String[]{"Automation@01"});
        hm2.put("arrItems1", new String[]{"ZARA COAT 3", "iphone 13 pro"});

        return new Object[][]{{hm1}, {hm2}};
    }

    @Test(dataProvider = "getDataViaExtFileJson",retryAnalyzer = RetryAnalyzer.class)
    public void submitOrderTestgetDataViaExtFileJson(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(input.get("emailID"), input.get("password"));
        ProductCataloguePage.selectAndAddProductToCart(input.get("arrItems1").split(","));
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(),List.of(input.get("arrItems1").split(",")).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).toArray());
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();

    }

    @DataProvider
    public Object[][] getDataViaExtFileJson() throws IOException {

        File filePath = new File(System.getProperty("user.dir")+"/src/test/java/ecommerce/data/externalDataFile.json");
        List<HashMap<String,String>> js = readJson(filePath);

        return new Object[][]{{js.get(0)}, {js.get(1)}};
    }


    @Test(enabled = false, dataProvider = "getDataViaExtFileJsonOpt")
    public void submitOrderTestgetDataViaExtFileJsonOpt(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCataloguePage ProductCataloguePage = landingPage.Login(input.get("emailID"), input.get("password"));
        ProductCataloguePage.selectAndAddProductToCart(input.get("arrItems1").split(","));
        ProductCataloguePage.loadingIcon();
        CartPage cartPage = ProductCataloguePage.clickCartIcon();
        Assert.assertEquals(cartPage.getItemsInCart().toArray(),List.of(input.get("arrItems1").split(",")).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).toArray());
        CheckoutReviewPage CheckoutReviewPage = cartPage.clickCheckOut();
        CheckoutReviewPage.selectCountry(textcountrytoSelect, countrytoSelect);
        ConfirmationPage ConfirmationPage = CheckoutReviewPage.placeOrder();
        Assert.assertEquals(ConfirmationPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");
        dateOfOrder = landingPage.fetchCurrentDate();

    }

  /*  @DataProvider
    public Object[][] getDataViaExtFileJsonOpt() throws IOException {

        File file1 = new File(System.getProperty("user.dir")+"/src/test/java/ecommerce/data/extenalDataFileOpt.json");
        List<Person> js = readJsonPerson(file1);
        Object[][] data = new Object[js.size()][1];
        for (int i = 0; i < js.size(); i++) {
            data[i][0] = js.get(i);
        }
        return data;
    }*/

}

