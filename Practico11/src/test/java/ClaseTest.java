import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)
public class ClaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setup(ITestContext context){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.navigate().to("https://www.salesforce.com/mx/?ir=1");
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
    }

    @Test
    public void failedForcedTest(){
        Assert.assertTrue(false);
    }
}
