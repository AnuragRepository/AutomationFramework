package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath= "//*[@class='hero-primary']")
    WebElement thanksorderMessageHomePageEle;

    public String getConfirmationMessage()
    {
        String thanksorderMessageHomePage = thanksorderMessageHomePageEle.getText();
        return thanksorderMessageHomePage;
    }




}
