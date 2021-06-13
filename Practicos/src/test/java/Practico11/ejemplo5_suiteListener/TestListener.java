package Practico11.ejemplo5_suiteListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart ===> Comienza el test... " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess ===>   Test Exitoso: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure ===>  FALLA " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped ===> Test skipped!! " + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test with percentage ===> " + result.getTestName());
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println("Test started ===>" + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test finished " + context.getOutputDirectory());
        System.out.println("Test finished " + context.getHost());
        System.out.println("Test finished " + context.getEndDate());
        System.out.println("Test finished " + context.getStartDate());
    }
}
