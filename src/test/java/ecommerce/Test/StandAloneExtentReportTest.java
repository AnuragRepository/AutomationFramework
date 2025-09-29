package ecommerce.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StandAloneExtentReportTest {

    ExtentReports extentReports;
    @BeforeTest
     public void reportPrerequisite()
    {
        //for setting path and configuring report and sent to main class ExtentReports
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//index.html");

        // set report name
        extentSparkReporter.config().setReportName("Standalone Extent Report");

        //set report page title
        extentSparkReporter.config().setDocumentTitle("Standalone Extent Report Page Title");

        //main class for extent report execution
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        //set tester name in report
        extentReports.setSystemInfo("Testor","Anurag");

    }
    @Test
    public void getPageTitle()
    {
        //Pass test in extent report
        ExtentTest extentTest = extentReports.createTest("getPageTitle");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.udemy.com/user/rahul445/");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Rahul Shetty Academy | QA Instructor @ 1 Million Students| 25+ Best Selling Courses | Udemy");
         driver.close();
        //For forced failure display in report
        extentTest.fail("Test Failed : Known failure");

        //for completing the report creation after all test execution other wise index.html will not create
        extentReports.flush();

      }

    }


