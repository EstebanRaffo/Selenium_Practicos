package clase2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ejercicio1 {

    private WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void forgotAccountTest(){
        WebDriver driver = getDriver("https://www.facebook.com");

        Assert.assertEquals(driver.getTitle(), "Facebook - Inicia sesión o regístrate" );
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        driver.findElement(By.linkText("¿Olvidaste tu contraseña?")).click();

        Assert.assertEquals(driver.getTitle(), "¿Olvidaste tu contraseña? | No puedo iniciar sesión | Facebook");
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
    }

    @Test
    public void forgotPartialLinkTest(){
        WebDriver driver = getDriver("https://www.facebook.com");
        System.out.println("Titulo: " + driver.getTitle());
        driver.findElement(By.partialLinkText("¿Olvidaste")).click();
        System.out.println("Titulo: " + driver.getTitle());
    }

    @Test
    public void registrationTest() throws InterruptedException {
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(3000);
        WebElement dias = driver.findElement(By.id("day"));
        Select comboDias = new Select(dias);
        comboDias.selectByValue("25");


        WebElement meses = driver.findElement(By.id("month"));
        Select comboMeses = new Select(meses);
        comboMeses.selectByVisibleText("jul");

    }




    @Test
    public void salesforceTest(){
        WebDriver driver = getDriver("https://login.salesforce.com/");
        System.out.println("Titulo: " + driver.getTitle());
        driver.findElement(By.linkText("Use Custom Domain")).click();
        driver.findElement(By.name("mydomain")).sendKeys("as");
        driver.findElement(By.id("mydomainContinue")).click();
        driver.findElement(By.id("okta-signin-username")).sendKeys("test@test.com");
    }
}
