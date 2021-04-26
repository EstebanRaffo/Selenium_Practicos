package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SalesforceTest {
    WebDriver driver;
    private final String URL_SALESFORCE = "https://login.salesforce.com";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SALESFORCE);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void forgotPassword() throws InterruptedException {
        Thread.sleep(5000);
        WebElement forgot_pass = driver.findElement(By.partialLinkText("¿Olvidó la contraseña?"));
        forgot_pass.click();
    }

    @Test
    public void implicitWaitTest(){
        WebElement forgot_pass = driver.findElement(By.partialLinkText("¿Olvidó la contraseña?"));
        forgot_pass.click();
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Fin del test");
        /*driver.close();*/
    }
}
