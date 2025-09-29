package ecommerce.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static ecommerce.Resources.ExtentReportBase.getextentReportBase;

public class Listener extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports = getextentReportBase();
    ThreadLocal<ExtentTest> threadLocalExtentTestObj= new ThreadLocal();// for fixing concurrency issue

    @Override
    public void onTestStart(ITestResult result) {

        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocalExtentTestObj.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocalExtentTestObj.get().log(Status.PASS,"Test Passed");// replace test with threadLocalExtentTestObj.get() for redirecting to set thread test
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocalExtentTestObj.get().log(Status.FAIL,"Test Failed");
        threadLocalExtentTestObj.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filePath = null;
        try {
            filePath = screenshotUtility(result.getMethod().getMethodName(),driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        threadLocalExtentTestObj.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
