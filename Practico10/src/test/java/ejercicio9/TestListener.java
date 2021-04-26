package ejercicio9;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onStart(ITestContext context){
        System.out.println("OnStart Method " + context.getOutputDirectory());
    }

    public void onTestStart(ITestResult result){
        System.out.println("New Test started: " + result.getName());
    }

    public void onTestSuccess(ITestResult result){
        System.out.println("Test Passed: " + result.isSuccess() + " ----> " + result.getName());
    }

    public void onTestFailure(ITestResult result){
        System.out.println("Test failed: " + result.getName());
    }

    public void onTestSkipped(ITestResult result){
        System.out.println("Test skipped: " + result.getSkipCausedBy() + "\n" + result.getName());
    }

    public void onTestFailedWithTimeout(ITestResult result){ this.onTestFailure(result); }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        System.out.println("Test Failed but within success percentage: " + result.getName());
    }

    public void onFinish(ITestContext context){
        System.out.println("OnFinish Method: " + context.getPassedTests());
        System.out.println("OnFinish Method: " + context.getFailedTests());
        System.out.println("OnFinish Method: " + context.getSkippedTests());
    }
}
