package ecommerce.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver driverInitialization() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ecommerce/Resources/globalData.properties");
        prop.load(fis);
        //For maven command reading
        String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");
        if (browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//for headless full screen
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new GeckoDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //WebDriverManager.edgedriver().setup();
            //System.setProperty("webdriver.edge.driver","C:/Users/dell/Downloads/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = driverInitialization();
        landingPage = new LandingPage(driver);
        landingPage.goToApplication();
        return landingPage;

    }

    public List<HashMap<String, String>> readJson(File filePath) throws IOException {
        //Convert Json to String
        String jsonFilePathString = FileUtils.readFileToString(filePath,StandardCharsets.UTF_8);

        //Convert String to HashMap using jackson databind dependancy for dataProvider as it takes hashMap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonFilePathString, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    public List<HashMap<String, String[]>> readJsonPerson(File filePath) throws IOException {
        //Covert Json to String
        String jsonFilePathString = FileUtils.readFileToString(filePath,StandardCharsets.UTF_8);

        //Convert String to HashMap using jackson databind dependancy for dataProvider as it takes hashMap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String[]>> data = objectMapper.readValue(jsonFilePathString, new TypeReference<List<HashMap<String, String[]>>>() {
        });

        return data;
    }

    public String screenshotUtility(String testcaseName, WebDriver driver) throws IOException {
       TakesScreenshot ts =  (TakesScreenshot)driver;
       File src = ts.getScreenshotAs(OutputType.FILE);
       File file = new File (System.getProperty("user.dir")+"//ReportsScreenshotFailure"+"//"+testcaseName+".png");
       FileUtils.copyFile(src, file);
       return System.getProperty("user.dir")+"//ReportsScreenshotFailure"+"//"+testcaseName+".png";

        //sample for setting path in folder
        // System.getProperty("user.dir") + "//Reports//framework.html"

    }


   @AfterMethod(alwaysRun = true)
    public void tearDown()
   {
        driver.close();
    }


}
