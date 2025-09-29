package ecommerce.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBase {

    public static ExtentReports getextentReportBase()
    {
        String reportPath = System.getProperty("user.dir") + "//Reports//framework.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("FrameworkReport");
        extentSparkReporter.config().setDocumentTitle("FrameworkPageTitle");
        ExtentReports extentReports = new ExtentReports();
        extentReports.setSystemInfo("Testor","Anurag");
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;

    }



}
