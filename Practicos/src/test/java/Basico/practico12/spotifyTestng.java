package Basico.practico12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class spotifyTestng {
    String URL = "https://www.spotify.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test (priority = 0)
    public void verifySpotifyTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Escuchar es todo - Spotify");
    }

    //Utilizar xpath con caminos absolutos para acceder al botón de Registrar
    //Validar que la url actual, contenga signup
    @Test (priority = 1)
    public void verifySignupUrlTest(){
        //driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//a[@href='https://www.spotify.com/uy/signup/']")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("signup"));
    }

    /*
    Crear un método llamado invalidEmailTest
    Ingresar a spotify y hacer click en Registrarse
    Completar el email con un email inválido: “test.com”
    Validar que se despliegue el error: “La dirección de email que proporcionaste no es válida.”

     */

    @Test (priority = 2)
    public void invalidEmailTest(){
        driver.findElement(By.xpath("//a[@href='https://www.spotify.com/uy/signup/']")).click();
        driver.findElement(By.name("email")).sendKeys("test.com");
        driver.findElement(By.name("confirm")).sendKeys("test.com");
        //   Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com
        WebElement emailErrorMsg = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico no es válido.')]"));
        Assert.assertEquals(emailErrorMsg.getText(), "Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com" );
    }

    //Este correo electrónico ya está conectado a una cuenta.

    @Test (priority = 3)
    public void validateExistingEmail() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='https://www.spotify.com/uy/signup/']")).click();
        driver.findElement(By.name("email")).sendKeys("test@test.com");
        driver.findElement(By.name("confirm")).sendKeys("test@test.com");
        Thread.sleep(3000);

        WebElement duplicateEmailErrorMsg = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico ya está conectado a una cuenta.')]"));
        Assert.assertEquals(duplicateEmailErrorMsg.getText(), "Este correo electrónico ya está conectado a una cuenta. Inicia sesión." );

    }






    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }
}
