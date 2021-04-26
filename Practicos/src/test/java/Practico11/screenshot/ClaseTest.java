package Practico11.screenshot;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import Practico11.ejemplo5_suiteListener.TestListener;

@Listeners(Listener.class)

public class ClaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setDriver(ITestContext context) {
        System.setProperty("webdriver.opera.driver","drivers/operadriver.exe");
        driver = new OperaDriver();
        //Navigate to  automation practice site
        driver.navigate().to("https://www.salesforce.com/mx/?ir=1");
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
    }

    @Test
    public void tryForFreeBtnTestFails()  {

        Assert.assertTrue(false);
    }
}
