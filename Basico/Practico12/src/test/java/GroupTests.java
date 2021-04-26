import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.*;

public class GroupTests {
    WebDriver driver;

    @Test(groups = {"successTests", "failTests"})

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.spotify.com/uy/";
        driver.get(baseURL);
    }

    @Test (groups = {"successTests"})
    public void verifySpotifyTitle(){
        String title = "Registrarte - Spotify";
        String currentTitle = driver.getTitle();
        Assert.assertEquals(currentTitle, title);
        System.out.println("Titulo obtenido: " + currentTitle);
    }

    @Test (groups = {"successTests"})
    public void verifyDignupUrl(){
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div/header/div/nav/ul/li[5]/a"));
        registrar.click();

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.spotify.com/uy/signup/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    /*************************************** REVISAR ****************************************/

    @Test (groups = {"failTests"}, priority = 1)
    public void invalidEmailTest() throws InterruptedException {
        Thread.sleep(5000);
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("test.com");
        driver.findElement(By.id("confirm")).sendKeys("test.com");
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), \"Este correo electrónico no es válido.\")]"));
        String expectedErrorMessage = "Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com";
        String errorMessageText = errorMessage.getText();
        Assert.assertEquals(errorMessageText, expectedErrorMessage);
    }

    @Test (groups = {"failTests"})
    public void validateExistingEmail() throws InterruptedException {
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("test@test.com");
        driver.findElement(By.id("confirm")).sendKeys("test@test.com");
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),\"Este correo electrónico ya está conectado a una cuenta.\")]"));
        String expectedErrorMessage = "Este correo electrónico ya está conectado a una cuenta. Inicia sesión.";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Ejecución de los test finalizada.");
        /*        driver.close();*/
    }
}
