package ecommerce.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneE2EecomFlow {

    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        String emailID ="test786test786@gmail.com";
        String password ="Automation@01";
        String[] arrItems ={"ZARA COAT 3","ADIDAS ORIGINAL"};
        String textcountrytoSelect = "Ind";
        String countrytoSelect = "India";
        Actions actions = new Actions(driver);
        //String productName = "ZARA COAT 3";
        //String productName = "ADIDAS ORIGINAL"; it also works

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailTextBox = driver.findElement(By.id("userEmail"));
        WebElement passwordTextBox= driver.findElement(By.xpath("//*[@id='userPassword']"));
        emailTextBox.sendKeys(emailID);
        passwordTextBox.sendKeys(password);
        WebElement login = driver.findElement(By.xpath("//*[@id='login']"));
        login.click();
        //getting no such element exception but xpath correct
        // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='products']"))));//
        //WebElement productCart = driver.findElement(By.xpath("//*[@id='products']"));
       /* List<WebElement> products = productCart.findElements(By.xpath("//.b"));
        boolean itemPresent = products.stream().anyMatch(s->s.getText().equalsIgnoreCase("ZARA COAT 3"));
        Assert.assertTrue(itemPresent);*/

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
        for(String productName: arrItems)
        {
            List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]/div/div/h5/b"));
            WebElement product = products.stream().filter(s->s.getText().equalsIgnoreCase(productName)).findAny().orElse(null);
            WebElement addToCart = product.findElement(By.xpath(".//ancestor::div/button[2]"));
            addToCart.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' Product Added To Cart ']")));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        WebElement cartButton = driver.findElement(By.xpath("//*[@class='ng-star-inserted']  //li[4]  //*[contains(@class,'btn-custom')]"));
        cartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=('cartSection')]/h3")));
        List<WebElement> addedItems = driver.findElements(By.xpath("//*[@class=('cartSection')]/h3"));
        List<String> addedItemsText = addedItems.stream().map(s->s.getText()).collect(Collectors.toList());
        Assert.assertEquals(addedItemsText.toArray(),arrItems);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,16000)");
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        actions.moveToElement(driver.findElement(By.xpath("//ul/li[3] //button[contains(@class,'btn-primary')]"))).build().perform();
        //js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//ul/li[3] //button[contains(@class,'btn-primary')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[3] //button[contains(@class,'btn-primary')]")));
        WebElement checkOutButton = driver.findElement(By.xpath("//ul/li[3] //button[contains(@class,'btn-primary')]"));
        checkOutButton.click();
        WebElement selectCountry = driver.findElement(By.xpath("//*[@class='form-group']//*[contains(@class,'text-validated')]"));
        actions.moveToElement(selectCountry).click().sendKeys(countrytoSelect).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='form-group']//*[contains(@class,'text-validated')]/following-sibling::section/button")));

        List<WebElement> countryListView = driver.findElements(By.xpath("//*[@class='form-group']//*[contains(@class,'text-validated')]/following-sibling::section/button"));
        WebElement country =countryListView.stream().filter(s->s.getText().equalsIgnoreCase("India")).findAny().orElse(null);
        country.click();
        WebElement placeOrderButton = driver.findElement(By.xpath("//*[contains(@class,'btnn action__submit')]"));
        placeOrderButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'toast-title')]")));
        String thanksorderMessageHomePage = driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
        Assert.assertEquals(thanksorderMessageHomePage,"THANKYOU FOR THE ORDER.");



    }



}

