package Practico2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AirbnbTest {
    String URL = "https://www.airbnb.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void airbnbTest() throws InterruptedException {
        driver.findElement(By.xpath("//button[@data-testid='accept-btn']")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@placeholder='¿A dónde viajas?']")).sendKeys("Budapest");

        WebElement calendario = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-field-split-dates-0']"));
        calendario.click();

        WebElement fechaInicio = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-02-18']"));
        fechaInicio.click();

        WebElement fechaFin = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-02-28']"));
        fechaFin.click();

        WebElement personas = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-field-guests-button']"));
        personas.click();

        WebElement aumentarAdultosBtn = driver.findElement(By.xpath("(//*[@aria-label='aumentar valor'])[1]"));
        WebElement aumentarNiñosBtn = driver.findElement(By.xpath("(//*[@aria-label='aumentar valor'])[2]"));
        WebElement aumentarBebesBtn = driver.findElement(By.xpath("(//*[@aria-label='aumentar valor'])[3]"));

        aumentarAdultosBtn.click();
        aumentarAdultosBtn.click();
        aumentarNiñosBtn.click();
        aumentarBebesBtn.click();

        WebElement buscarBtn = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-search-button']"));
        buscarBtn.click();

        //explicit wait..
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'2 huéspedes')]"))));


        Assert.assertTrue(driver.getCurrentUrl().contains("Budapest"));
        List<WebElement> cantidadHuespedesMsgList = driver.findElements(By.xpath("//*[contains(text(),'2 huéspedes')]"));

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }
}
