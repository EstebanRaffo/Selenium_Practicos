package Basico.clase11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class spotifyTest {

    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void spotifyByPlaceHolder(){
        WebDriver driver = getDriver("https://www.spotify.com/uy/signup/");
        // placeholder="Introduce tu correo electrónico."
        driver.findElement(By.xpath("//*[@placeholder='Introduce tu correo electrónico.']")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//*[@placeholder='Vuelve a introducir tu correo electrónico.']")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//*[@placeholder='Crea una contraseña.']")).sendKeys("selenium");
        driver.findElement(By.xpath("//*[@placeholder='Introduce un nombre de perfil.']")).sendKeys("selcurso");

        //WebElement errMsg = driver.findElement(By.xpath("//*[contains(text(),'Este correo electrónico ya está conectado a una cuenta.')]"));
        List<WebElement> listaErrMsg = driver.findElements(By.xpath("//*[contains(text(),'Este correo electrónico ya está conectado a una cuenta. ')]"));
        System.out.println("cantidad elementos: " + listaErrMsg.size());

        for (WebElement element: listaErrMsg){
            System.out.println("---> " + element.getText());
        }


        //System.out.println("--> " + errMsg.getText());

        //Assert.assertEquals(errMsg.getText(), "Este correo electrónico ya está conectado a una cuenta.");

    }

    @Test
    public void cssSelectorTest(){
        WebDriver driver = getDriver("https://www.spotify.com/uy/signup/");
        driver.findElement(By.cssSelector("input[placeholder='Introduce tu correo electrónico.']")).sendKeys("test@test.com");
        //driver.findElement(By.xpath("//*[@placeholder='Vuelve a introducir tu correo electrónico.']")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[placeholder='Vuelve a introducir tu correo electrónico.']")).sendKeys("test@test.com");

        driver.findElement(By.cssSelector("input[placeholder='Crea una contraseña.']")).sendKeys("holamundo");
        driver.findElement(By.cssSelector("input[id='displayname']")).sendKeys("selenium");
        
        driver.findElement(By.cssSelector("*[type='submit']")).click();

        //driver.findElement(By.xpath("//*[@placeholder='Introduce un nombre de perfil.']")).sendKeys("selcurso");


    }
}
