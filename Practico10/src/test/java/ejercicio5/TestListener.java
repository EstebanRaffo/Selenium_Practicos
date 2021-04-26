package ejercicio5;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class TestListener implements ITestListener {
    public void onStart(ITestContext context){
        System.out.println("Directorio donde se ejecutan las pruebas " + context.getName());
    }

    public void onFinish(ITestContext context){
        System.out.println("Test finished: " + context.getOutputDirectory());
        System.out.println("Test finished: " + context.getHost());
        System.out.println("Test finished: " + context.getEndDate());
        System.out.println("Test finished: " + context.getStartDate());
    }

    public void onTestStart(ITestResult result){
        System.out.println("Comienza el Test " + result.getName());
    }

    public void onTestSuccess(ITestResult result){
        System.out.println("Test Passed" + result.isSuccess() + "\n" + result.getName());
    }

    public void onTestSkipped(ITestResult result){
        System.out.println("Test skipped" + result.getSkipCausedBy() + "\n" + result.getName());
    }

    public void onTestFailedWithTimeout(ITestResult result){ this.onTestFailure(result); }

    public void onTestFailure(ITestResult result){
        System.out.println("Test failed: " + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        System.out.println("Test with percentage: " + result.getTestName());
    }
}
