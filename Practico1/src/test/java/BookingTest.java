
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class BookingTest extends BaseTest{
    String URL = "https://www.booking.com";

    @BeforeMethod
    public void setupBooking(){
        driver.get(URL);
    }

    @Test
    public void validateH1(){
        List<WebElement> h1List = driver.findElements(By.tagName("h1"));

        Assert.assertFalse(h1List.isEmpty());
        Assert.assertTrue(!h1List.isEmpty());
        Assert.assertNotEquals(0, h1List.size());

        for(WebElement h1 : h1List){
            System.out.println(h1.getText());
        }
    }

    @Test
    public void iniciaSesion(){
        //WebElement btnIniciaSesion = driver.findElement(By.className("bui-button__text"));

        WebElement btnIniciaSesion = getBtnByText("Inicia sesión");

        Assert.assertNotEquals(null, btnIniciaSesion);
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

        Assert.assertFalse("No se encontro el elemento", true);
        return null;
    }

    @Test
    public void searchLogin(){
        try{
            WebElement login_button = driver.findElement(By.linkText("Iniciaa sesión"));
            login_button.click();
            System.out.println("Click en Iniciar sesión");
        }catch (Exception e){
            Assert.assertFalse("El boton no se ha econtrado en el sitio", true);
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }

}
