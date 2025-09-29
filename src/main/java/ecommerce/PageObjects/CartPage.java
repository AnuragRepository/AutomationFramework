package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    By addedProductView = By.xpath("//*[@class=('cartSection')]/h3");
    By checkoutButtonScroll = By.xpath("//ul/li[3] //button[contains(@class,'btn-primary')]");

    @FindBy(xpath="//*[@class=('cartSection')]/h3")
    List<WebElement> addedItems;

    @FindBy(xpath="//ul/li[3] //button[contains(@class,'btn-primary')]")
    WebElement checkOutButton;


    public List<String> getItemsInCart()
    {
        waitUntilElementVisible(addedProductView);
        List<String> addedItemsText = addedItems.stream().map(s->s.getText().toUpperCase()).collect(Collectors.toList());
        return addedItemsText;
    }

    public CheckoutReviewPage clickCheckOut()
    {
        pageScroll();
        ScrollToElement(checkOutButton);
        waitUntilElementVisible(checkoutButtonScroll);
        waitUntilElementClickable(checkoutButtonScroll);
        checkOutButton.click();
        return new CheckoutReviewPage(driver);
    }

}
