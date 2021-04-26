package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpotifyTest {
    WebDriver driver;
    private String URL_SPOTIFY = "https://www.spotify.com/uy/signup/";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SPOTIFY);
    }

    @Test
    public void explicitWaitTest(){
        driver.findElement(By.id("email")).sendKeys("test@test.com");
        driver.findElement(By.id("confirm")).sendKeys("test@test.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Este correo electrónico ya está conectado a una cuenta.\")]")));
        WebElement error_message = driver.findElement(By.xpath("//span[contains(text(),\"Este correo electrónico ya está conectado a una cuenta.\")]"));

        String error_expected = "Este correo electrónico ya está conectado a una cuenta. Inicia sesión.";
        Assert.assertEquals(error_message.getText(), error_expected);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Fin del test");
        /*driver.close();*/
    }
}
