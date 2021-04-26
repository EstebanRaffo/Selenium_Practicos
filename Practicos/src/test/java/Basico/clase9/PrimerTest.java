package Basico.clase9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.Test;

import java.util.List;

public class PrimerTest {

    @Test
    public void mostrarTitulo(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver");
        WebDriver driver = new OperaDriver();
        driver.get("https://www.spotify.com");
        System.out.println(driver.getTitle());
        driver.close();
    }

    @Test
    public void primerTest() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
        String url = driver.getCurrentUrl();
        System.out.println("La url actual es " + url);
        //driver.close();
    }


    @Test
    public void mostrarHs(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com");

        List<WebElement> listaDeH1s = driver.findElements(By.tagName("h1"));
        System.out.println("La cantidad de elementos h1 es: " + listaDeH1s.size());

        for (WebElement element : listaDeH1s){
            System.out.println(element.getText());
        }
        System.out.println("*************************");
        List<WebElement> listaDeH2s = driver.findElements(By.tagName("h2"));
        for (WebElement element: listaDeH2s){
            System.out.println("Elemento H2: " + element.getText());
        }

        List<WebElement> listaParrafos = driver.findElements(By.tagName("p"));
        System.out.println("la cantidad de parrafos es " + listaParrafos.size());

        for (WebElement element: listaParrafos){
            System.out.println("Parrafo: " + element.getText());
        }


        driver.close();
    }

    @Test
    public void getWindowSize(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com");

        int altura = driver.manage().window().getSize().height;
        int ancho = driver.manage().window().getSize().width;
        System.out.println("El ancho de la pantalla es " + ancho);
        System.out.println("El alto de la pantalla es " + altura);
    }

    @Test
    public void getSpotifyTest(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
        String title = driver.getTitle();

        if (title.equals("Google")){
            System.out.println("Test passed!!");
        } else {
            System.out.println(title);
            System.out.println("Test failed!!!");
        }
        driver.close();
    }

    public WebDriver getGoogleDriver(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        return driver;
    }

    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void goToGoogle(){
        WebDriver driver = getGoogleDriver();
        String title = driver.getTitle();
        System.out.println(title);
    }

    @Test
    public void testBBC(){
        WebDriver driver = getDriver("https://www.bbc.com");
        String title = driver.getTitle();
        System.out.println(title);
    }
}
