package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutReviewPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutReviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@class='form-group']//*[contains(@class,'text-validated')]")
    WebElement selectCountry;

    @FindBy(xpath= "//*[@class='form-group']//*[contains(@class,'text-validated')]/following-sibling::section/button")
    List<WebElement> countryListView;

    @FindBy(xpath = "//*[contains(@class,'btnn action__submit')]")
    WebElement placeOrderButton;

    By countryList = By.xpath("//*[@class='form-group']//*[contains(@class,'text-validated')]/following-sibling::section/button");

    By PlacedMessage = By.xpath("//*[contains(@class,'toast-title')]");
    public void selectCountry(String textcountrytoSelect,String countrytoSelect)
    {
        ScrollToElementAndInput(selectCountry,textcountrytoSelect);
        waitUntilElementVisible(countryList);
        WebElement country =countryListView.stream().filter(s->s.getText().equalsIgnoreCase(countrytoSelect)).findAny().orElse(null);
        country.click();

    }
    public ConfirmationPage placeOrder()
    {
        placeOrderButton.click();
        waitUntilElementVisible(PlacedMessage);
        return new ConfirmationPage(driver);
    }

}
