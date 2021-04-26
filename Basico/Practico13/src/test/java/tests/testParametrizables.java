package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class testParametrizables {
    WebDriver driver;
    private String URL_SHOPIFY = "https://es.shopify.com/";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SHOPIFY);
    }

    @Test
    @Parameters({"tagName"})
    public void pruebaConParametros(@Optional("button") String tag){
        List<WebElement> lista_elementos = driver.findElements(By.tagName(tag));

        if(lista_elementos.isEmpty()){
            System.out.println("No se encontraron elementos de tipo " + tag);
        }
        else{
            System.out.println("Lista de " + tag);
            for(WebElement element : lista_elementos){
                System.out.println(element.getText());
            }
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Fin del test");
        driver.close();
    }
}
