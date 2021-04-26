import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener {

    public void onTestFailure(ITestResult result){
        System.out.println("onTestFailure: " + result.getName());

        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

        String projectPath = System.getProperty("user.dir");
        String methodName = result.getName();

        File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try{
            FileUtils.copyFile(srcFile, new File(projectPath + "/screenshots/" + methodName + System.currentTimeMillis() + ".png"));
            System.out.println("*** Screenshoot placed in " + projectPath + "/screenshots/");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
