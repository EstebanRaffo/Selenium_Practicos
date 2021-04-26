package Basico.practico16.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

}
