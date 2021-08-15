package Practico9;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class alertas {

    String URL = "https://www.seleniumacademy.net/alertas";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
    }

    @Test
    public void aceptarAlertas(){
        driver.findElement(By.id("alert1")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I am an alert box!", alert.getText() );
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void cancelConfirmationAlert() throws InterruptedException {
        driver.findElement(By.id("alert2")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Press a button!", alert.getText() );
        Thread.sleep(2000);
        alert.dismiss();
        WebElement alertResultElement = driver.findElement(By.id("demo"));
        Assert.assertEquals("Alert cancelled", alertResultElement.getText());
    }

    @Test
    public void acceptPromptAlert() throws InterruptedException {
        driver.findElement(By.id("alert3")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Esteban Raffo");
        Thread.sleep(2000);
        alert.accept();
        WebElement alertResultElement = driver.findElement(By.id("demo3"));
        Assert.assertEquals("Hi Esteban Raffo! How are you?", alertResultElement.getText());
    }
}
