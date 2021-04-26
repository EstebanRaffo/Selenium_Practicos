import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class SegundoTest {
    WebDriver driver;

    public WebDriver getDriverFb(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.facebook.com";
        driver.get(baseURL);
        return driver;
    }

    /******************************************** Ejercicio 1 ***************************************************************/
    @Test
    public void forgotAccountTest(){
        WebDriver driverFb = getDriverFb();

        Assert.assertEquals(driverFb.getTitle(), "Facebook - Entrar o registrarse");

        driverFb.findElement(By.partialLinkText("contraseña")).click();

        Assert.assertEquals(driverFb.getTitle(), "¿Has olvidado la contraseña? | No puedo entrar | Facebook", "se debería estar en ¿Has olvidado la contraseña?");
        Assert.assertNotEquals(driverFb.getCurrentUrl(), "https://www.facebook.com", "Se debería estar en otra url");
        driverFb.close();
    }

    /******************************************** Ejercicio 2 ***************************************************************/
    @Test
    public void forgotAccountPartialLinkTest(){
        WebDriver driverFb = getDriverFb();
        driverFb.findElement(By.partialLinkText("¿Has olvidado")).click();
        Assert.assertEquals(driverFb.getTitle(), "¿Has olvidado la contraseña? | No puedo entrar | Facebook", "se debería estar en ¿Has olvidado la contraseña?");
        driverFb.close();
    }

    /******************************************** Ejercicio 3 ***************************************************************/
    @Test
    public void customSalesforceLink() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://login.salesforce.com";
        driver.get(baseURL);
        driver.findElement(By.id("mydomainLink")).click();
        driver.findElement(By.id("mydomain")).sendKeys("as");
        driver.findElement(By.id("mydomainContinue")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("okta-signin-username")).sendKeys("test@test.com");

    }

    /******************************************** Ejercicio 4 ***************************************************************/
    @Test
    public void checkBoxAndComboboxTest() throws InterruptedException {
        WebDriver driverFb = getDriverFb();
        driverFb.findElement(By.linkText("Crear cuenta nueva")).click();

        Thread.sleep(3000);
        driverFb.findElement(By.id("u_2_6")).click();
        driverFb.findElement(By.name("firstname")).sendKeys("Alan");
        driverFb.findElement(By.name("lastname")).sendKeys("Smith");

        WebElement elementoDias = driverFb.findElement(By.id("day"));
        Select dias = new Select(elementoDias);
        dias.selectByValue("4");

        WebElement elementoMeses = driverFb.findElement(By.id("month"));
        Select meses = new Select(elementoMeses);
        meses.selectByVisibleText("abr");

        WebElement elementoAños = driverFb.findElement(By.id("year"));
        Select años = new Select(elementoAños);
        años.selectByIndex(10);
    }

    /******************************************** Ejercicio 6 ***************************************************************/
    @Test
    public void birthdateTest() throws InterruptedException {
        WebDriver driverFb = getDriverFb();

        driverFb.findElement(By.linkText("Crear cuenta nueva")).click();

        Thread.sleep(3000);
        WebElement elementoDias = driverFb.findElement(By.id("day"));
        Select dias = new Select(elementoDias);
        dias.selectByValue("20");

        WebElement elementoMeses = driverFb.findElement(By.id("month"));
        Select meses = new Select(elementoMeses);
        meses.selectByVisibleText("dic");

        WebElement elementoAños = driverFb.findElement(By.id("year"));
        Select años = new Select(elementoAños);
        años.selectByValue("1990");
    }

    /******************************************** Ejercicio 7 ***************************************************************/
    @Test
    public void comboboxText() throws InterruptedException {
        WebDriver driverFb = getDriverFb();
        driverFb.findElement(By.linkText("Crear cuenta nueva")).click();

        Thread.sleep(3000);
        WebElement birthday_month = driverFb.findElement(By.name("birthday_month"));
        Select months = new Select(birthday_month);
        List<WebElement> options = months.getOptions();
        Assert.assertNotEquals(0, options.size());

        Assert.assertTrue(search("jun", options));
        System.out.println("Existe jun");
        months.selectByValue("1");
        months.selectByValue("2");
        months.selectByValue("3");

    }

    private boolean search(String elem, List<WebElement> options){
        boolean existe = false;

        for(WebElement opc : options){
            System.out.println(opc.getText());
            if(opc.getText().contentEquals(elem)){
                existe = true;
                break;
            }
        }
        return existe;
    }

    /******************************************** Ejercicio 8 ***************************************************************/
    private void setBirthdate(WebDriver driverFb, String dia, String mes, String año){
        WebElement elementoDias = driverFb.findElement(By.name("birthday_day"));
        Select dias = new Select(elementoDias);
        dias.selectByValue(dia);

        WebElement elementoMeses = driverFb.findElement(By.name("birthday_month"));
        Select meses = new Select(elementoMeses);
        meses.selectByVisibleText(mes);

        WebElement elementoAños = driverFb.findElement(By.name("birthday_year"));
        Select años = new Select((elementoAños));
        años.selectByValue(año);
    }

    @Test
    public void completeRegistration() throws InterruptedException {
        WebDriver driverFb = getDriverFb();
        driverFb.findElement(By.linkText("Crear cuenta nueva")).click();

        Thread.sleep(3000);
        WebElement name = driverFb.findElement(By.name("firstname"));
        name.sendKeys("Pedro");
        WebElement lastname = driverFb.findElement(By.name("lastname"));
        lastname.sendKeys("Perez");
        WebElement email = driverFb.findElement(By.name("reg_email__"));
        email.sendKeys("pedro@hotmail.com");
        WebElement pass = driverFb.findElement(By.name("reg_passwd__"));
        pass.sendKeys("123");
        /*WebElement sex = driverFb.findElement(By.id("u_2_3"));
        sex.click();*/
        setBirthdate(driverFb, "6","feb","1987");
    }

    /******************************************** Ejercicio 9 ***************************************************************/
    /** VER GIT **/
}
