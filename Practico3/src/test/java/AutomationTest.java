import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationTest extends BaseTest {
    String URL = "http://automationpractice.com/index.php";

    @BeforeMethod
    public void setupAirbnb(){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    /*
    Registrar a un cliente con un email generado de forma randómica

    1)	Localizar todos los elementos de tipo WebElement del sitio.
    2)	Interactuar con los elementos enviando la información deseada.
    3)	Hacer click en el botón de registro.
    4)	Validar que la información desplegada sea la correcta.*/
    @Test
    public void validations(){

        // driver.findElement(By.className("login")).click();
        driver.findElement(By.linkText("Sign in")).click();

        WebElement email_adress = driver.findElement(By.id("email_create"));
        String email = "pedro_"+ Math.random() +"@gmail.com";
        System.out.println("email enviado: " + email);
        email_adress.sendKeys(email);

        driver.findElement(By.id("SubmitCreate")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));
        String email_value = driver.findElement(By.id("email")).getAttribute("value");

        System.out.println("email recibido: " + email_value);
        Assert.assertEquals(email_value, email);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }

}
