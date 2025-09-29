package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//div[contains(@class,'mb-3')]/div/div/h5/b")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement animating;

    @FindBy(xpath = "//*[contains(@class,'toast-message')]")
    WebElement ProductAddedToCartMessageELe;

    By addToCartEle = By.xpath(".//ancestor::div/button[2]");
    By ProductAddedToCartMessage = By.xpath("//*[text()=' Product Added To Cart ']");
    By toastcontainer = By.cssSelector("#toast-container");


    public void selectAndAddProductToCart(String[] arrItems) throws InterruptedException {
        for (String productName : arrItems) {
            pageScroll();
            WebElement product = products.stream().filter(s -> s.getText().equalsIgnoreCase(productName)).findAny().orElse(null);
            WebElement addToCart = product.findElement(addToCartEle);
            addToCart.click();
            waitUntilElementVisible(ProductAddedToCartMessage);

            //Element intercepted exception
          /*  for (String productName : arrItems) {
                WebElement product = products.stream().filter(s -> s.getText().equalsIgnoreCase(productName)).findAny().orElse(null);
                WebElement addToCart = product.findElement(addToCartEle);
                addToCart.click();
                waitUntilElementVisible(ProductAddedToCartMessage);
            }*/

            //working but slow execution
            /*for (String productName : arrItems) {

                WebElement product = products.stream().filter(s -> s.getText().equalsIgnoreCase(productName)).findAny().orElse(null);
                WebElement addToCart = product.findElement(addToCartEle);
                addToCart.click();
                waitUntilElementVisible(ProductAddedToCartMessage);
                ProductAddedToCartMessageELe.click();
                waitUntilElementInVisible(ProductAddedToCartMessageELe);*/


        }
    }

    public void loadingIcon() throws InterruptedException {
        //waitUntilElementVisible(toastcontainer);
        //ng-animating
        Thread.sleep(2000);
        //waitUntilElementInVisible(animating);// application issue making performance slow so used Thread.sleep
    }

}
