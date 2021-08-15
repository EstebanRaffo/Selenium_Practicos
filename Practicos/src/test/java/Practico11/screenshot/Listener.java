package Practico11.screenshot;

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


    public void onTestFailure(ITestResult result) {
        //This will call when test get failed
        System.out.println("OnTestFailure : "+result.getName());

        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

        String projectPath = System.getProperty("user.dir");
        String methodName=result.getName();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name
        try {
            //in windows
            // FileUtils.copyFile(scrFile, new File(projectPath+"\\screenshots\\"+methodName + System.nanoTime() +".png"));
            FileUtils.copyFile(scrFile, new File(projectPath+"/screenshots/"+methodName + System.currentTimeMillis() +".png"));
            System.out.println("***Placed screen shot in "+projectPath+"/screenshots/"+" ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
