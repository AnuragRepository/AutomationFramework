package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement emailTextBox;

    @FindBy(xpath="//*[@id='userPassword']")
    WebElement passwordTextBox;

    @FindBy(xpath="//*[@id='login']")
    WebElement login;

    @FindBy(xpath="//*[contains(@class,'toast-message')]")
    WebElement errorMessageEle;

    public  void goToApplication()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public  ProductCataloguePage Login(String emailID, String password)
    {
        emailTextBox.sendKeys(emailID);
        passwordTextBox.sendKeys(password);
        ScrollToElement(login);
        login.click();
        return new ProductCataloguePage(driver);
    }
    public String getloginErrorMessage()
    {
        waitUntilElementVisible(errorMessageEle);
        return errorMessageEle.getText();

    }
    public String fetchCurrentDate()
    {
       String updatedDate,date;
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        //String formatedDate = LocalDate.now().format(dateTimeFormatter);
        if(LocalDate.now().getDayOfMonth()<=9)
        {
            updatedDate =  String.valueOf(0).concat(String.valueOf(LocalDate.now().getDayOfMonth()));
            date = LocalDate.now().getDayOfWeek().toString().substring(0,3)+" "+LocalDate.now().getMonth().toString().substring(0,3)+" "+updatedDate;
        }
       else
        {
            date = LocalDate.now().getDayOfWeek().toString().substring(0,3)+" "+LocalDate.now().getMonth().toString().substring(0,3)+" "+String.valueOf(LocalDate.now().getDayOfMonth());
        }
       return date;

        //Thu Sep 04
    }


}
