package ecommerce.PageObjects;

import ecommerce.AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryPage extends AbstractComponent {

    WebDriver driver;
    public OrderHistoryPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//table //tbody/tr/td[2]")
    List<WebElement> ItemNames;

    public List<String> getOrderItems(String dateOfOrder)
    {
        List<WebElement> orderedItemNamesWithOrderDate = ItemNames.stream().filter(s->s.findElement(By.xpath(".//following-sibling::td[2]")).getText().equalsIgnoreCase(dateOfOrder)).collect(Collectors.toList());
        List<String> orderedItemList = orderedItemNamesWithOrderDate.stream().map(s->s.getText()).collect(Collectors.toList());
        return orderedItemList;
    }
}

