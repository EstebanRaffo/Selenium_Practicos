package Intermedio.Practico1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BookingTest {
    String URL = "https://www.booking.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void primerTestA() {
        System.out.println("Titulo --> " + driver.getTitle());

        List<WebElement> elementsList = driver.findElements(By.tagName("h112"));

        Assert.assertTrue(elementsList.isEmpty());
        Assert.assertFalse(elementsList.isEmpty() == false);
        Assert.assertEquals(0, elementsList.size());
    }

    @Test
    public void primerTest(){
        System.out.println("Titulo --> " + driver.getTitle());

        List<WebElement> elementsList = driver.findElements(By.className("noexiste.com"));
        List<WebElement> inputList = driver.findElements(By.xpath("//*[id='input']"));

        System.out.println("Tamaño de la lista " + elementsList.size());

        Assert.assertNotNull(elementsList);

        Assert.assertTrue(elementsList.isEmpty() == false);
        Assert.assertFalse(elementsList.size() != 0 );
        Assert.assertFalse(elementsList.isEmpty());
        Assert.assertNotEquals(elementsList.size(), 0, "La lista no tiene elementos!!");
    }


    @Test
    public void bookingTest(){
        List<WebElement> h2List = driver.findElements(By.tagName("h2"));
        Assert.assertNotEquals(h2List.size(), 0);
        Assert.assertFalse(h2List.isEmpty()); //retorna true si la lista es vacia, de lo contrario retorna falso...
        boolean genteViajeraPresente = false;
        for (WebElement h2 : h2List){
            System.out.println(h2.getText());
            if (h2.getText().equals("Conecta con gente viajera")){
                genteViajeraPresente = true;
            }
        }

        Assert.assertTrue(genteViajeraPresente);

    }

    @Test
    public void iniciaSesion(){
        //WebElement btnIniciaSesion = driver.findElement(By.className("bui-button__text"));

        WebElement btnIniciaSesion = getBtnByText("Inicia sesión");

        System.out.println("-->" + btnIniciaSesion.getText());

    }

    private WebElement getBtnByText(String btnText){
        List<WebElement> btnList = driver.findElements(By.className("bui-button__text"));
        Assert.assertNotEquals(btnList.size(), 0);

        for (WebElement btn : btnList){
            if (btn.getText().equals(btnText)){
                return btn;
            }
        }

        Assert.assertFalse(true, "No se encontro el elemento");
        return null;
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }
}
