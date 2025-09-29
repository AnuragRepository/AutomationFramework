package ecommerce.AbstractComponent;

import ecommerce.PageObjects.CartPage;
import ecommerce.PageObjects.OrderHistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);


    }

    @FindBy(xpath="//*[@class='ng-star-inserted']  //li[4]  //*[contains(@class,'btn-custom')]")
    WebElement cartButton;

    By allproductView =  By.xpath("//div[contains(@class,'mb-3')]");

    @FindBy(xpath = "(//*[contains(@class,'btn btn-custom')])[2]")
    WebElement orderButton;

    public void pageScroll()
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,16000)");
    }


    public void ScrollToElement(WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

    }
    public void ScrollToElementAndInput(WebElement element,String input)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(input).build().perform();

    }


    public void waitUntilElementVisible(By allproductView)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(allproductView));
    }
    public void waitUntilElementClickable(By allproductView)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(allproductView));
    }

    public void waitUntilElementVisible(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitUntilElementInVisible(WebElement allproductView)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(allproductView));
    }

    public void implicitWait()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public CartPage clickCartIcon()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(cartButton).build().perform();
        cartButton.click();
        return  new CartPage(driver);

    }
    public OrderHistoryPage goToOrderPage()
    {
        waitUntilElementVisible(orderButton);
        orderButton.click();
        return new OrderHistoryPage(driver);
    }


}
