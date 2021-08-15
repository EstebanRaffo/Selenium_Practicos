package Practico2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AutomationTest {
    WebDriver driver ;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp () {
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown () {
        //driver.quit();
    }

    @Test
    public void registerionTest () throws InterruptedException {
        //going to the site
        String siteURL = "http://automationpractice.com/index.php";
        driver.get(siteURL);

        //waiting for the SignIn button to be clickable, otherwise it will fail
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("login"))));

        //clicking the SignIn button
        driver.findElement(By.className("login")).click();

        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        //searching for the email field and sending a random value
        String emailString = "matose81+" + Math.random() + "@gmail.com";
        driver.findElement(By.id("email_create")).sendKeys(emailString);
        //click on the submit button
        driver.findElement(By.id("SubmitCreate")).click();
        //waiting for the form to load completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));

        String email = driver.findElement(By.id("email")).getAttribute("value");
        System.out.println(email);
        Assert.assertEquals(emailString, email);

    }
}
