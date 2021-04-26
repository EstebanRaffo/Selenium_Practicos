package Basico.practico13.refactoring;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class FacebookTest {
    public String FACEBOOK_URL = "https://www.facebook.com";
    WebDriver driver;

    String nombre = "";
    String apellido= "";
    String email= "";

    public FacebookTest(){

    }

    public FacebookTest(String unNombre, String unApellido, String unEmail){
        nombre = unNombre;
        apellido = unApellido;
        email = unEmail;
    }


    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(FACEBOOK_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testing() throws InterruptedException {
        Thread.sleep(4000);

    }

    @Test
    public void facebookRegistrationTest() throws InterruptedException {
        DriverUtilities driverUtility = new DriverUtilities(driver);
        FacebookRegistrationForm registrationForm = new FacebookRegistrationForm(driver);

        System.out.println("---> " + driverUtility.getTitle());

        driverUtility.clickBtn("//*[@ajaxify='/reg/spotlight/']");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("firstname"))));

        registrationForm.fillingForm(nombre, apellido, email);
        driverUtility.clickBtn("(//*[@type='submit'])[2]");

        //driver.findElement(By.xpath("//*[@ajaxify='/reg/spotlight/']")).click();

        /*driver.findElement(By.name("firstname")).sendKeys("Jhon");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("reg_email__")).sendKeys("testing@test.com");
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("testing@test.com");
        driver.findElement(By.xpath("//*[@name='sex'][@value='-1']")).click();*/
        //driver.findElement(By.xpath("(//*[@type='submit'])[2]")).click();
    }
    
    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }


}
