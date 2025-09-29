package ecommerce.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    //for flaky/inconsistent test
    int count = 0;
    int maxRerun = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxRerun && !iTestResult.isSuccess())// 0<1
        {
            count++; //1
            return true;
        }
        return false;
    }
}
