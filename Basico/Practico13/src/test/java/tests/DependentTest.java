package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class DependentTest {
    WebDriver driver;
    private String URL_SPOTIFY = "https://www.spotify.com/uy";

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SPOTIFY);
    }

    @Test (dependsOnMethods = {"registerTest"})
    public void validate(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement error_pass = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(text(), \"Debes introducir una contraseña.\")]"))));
        String expected_pass_error = "Debes introducir una contraseña.";
        Assert.assertEquals(error_pass.getText(), expected_pass_error);
        System.out.println("validate() passed");
    }

    @Test (dependsOnMethods = {"fillingTwo"})
    public void registerTest(){
        driver.findElement(By.xpath("//button[contains(text(), \"Registrarte\")]")).click();
        System.out.println("registerTest() passed");
    }

    @Test (dependsOnMethods = {"getTitle"})
    public void fillingTwo() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("e.fraffo@gmail.com");
        driver.findElement(By.id("confirm")).sendKeys("e.fraffo@gmail.com");
        System.out.println("fillingTwo() passed");
    }

    @Test (dependsOnMethods = {"welcomeTest"})
    public void getTitle(){
        Assert.assertEquals(driver.getTitle(), "Registrarte - Spotify");
        System.out.println("getTitle() passed");
    }

    @Test
    public void welcomeTest(){
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Escuchar es todo - Spotify");
        driver.findElement(By.xpath("//a[contains(text(), \"Registrarse\")]")).click();
        System.out.println("welcomeTest() passed");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Fin del test");
        driver.close();
    }
}
