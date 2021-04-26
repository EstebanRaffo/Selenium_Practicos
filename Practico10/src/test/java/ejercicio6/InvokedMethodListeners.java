package ejercicio6;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListeners implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult){
        System.out.println("Antes de invocar al método: " + method.getTestMethod().getMethodName());
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult){
        System.out.println("Después de invocar al método: " + method.getTestMethod().getMethodName());
    }
}
