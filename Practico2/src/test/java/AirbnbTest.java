import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AirbnbTest extends BaseTest{
    String URL = "https://www.airbnb.com";

    @BeforeMethod
    public void setupAirbnb(){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    /*Crear un método de test que realice la búsqueda de un alojamiento en el sitio de airbnb.
    Se debe buscar alojamiento en el sitio de airbnb en Budapest, entre dos rangos de fecha superiores a 10
    días y para 2 adultos, 1 niño y un bebé.
    Se desea validar que los resultados de la búsqueda sean acorde a lo especificado previamente.*/
    @Test
    public void searchHousing() throws InterruptedException {
        String destiny = "Budapest";

        driver.findElement(By.xpath("//button[@data-testid='accept-btn']")).click();

        Thread.sleep(5000);
        WebElement place = driver.findElement(By.xpath("//input[@placeholder='¿A dónde vas?']"));
        place.sendKeys(destiny);

        driver.findElement(By.xpath("(//div[contains(text(), '¿Cuándo?')])[1]")).click();

        /*WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='datepicker-day-2021-02-27']/div"))));*/

        WebElement date_from = driver.findElement(By.xpath("//div[@data-testid='datepicker-day-2021-02-27']/div"));
        date_from.click();

        WebElement date_to = driver.findElement(By.xpath("//div[@data-testid='datepicker-day-2021-03-16']/div"));
        date_to.click();

        driver.findElement(By.xpath("//div[contains(text(), '¿Cuántos?')]")).click();
        WebElement incr_adults_button = driver.findElement(By.xpath("//button[@data-testid='stepper-adults-increase-button']"));
        incr_adults_button.click();
        incr_adults_button.click();

        WebElement incr_children_button = driver.findElement(By.xpath("//button[@data-testid='stepper-children-increase-button']"));
        incr_children_button.click();

        WebElement incr_infants_button = driver.findElement(By.xpath("//button[@data-testid='stepper-infants-increase-button']"));
        incr_infants_button.click();

        driver.findElement(By.xpath("//button[@data-testid='structured-search-input-search-button']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains(destiny));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/div[contains(text(),'2 huéspedes')]")));


        List<WebElement> hosts_informed = driver.findElements(By.xpath("//*[contains(text(), '2 huéspedes')]"));
        Assert.assertNotEquals(hosts_informed.size(), 0);

        List<WebElement> date_from_informed = driver.findElements(By.xpath("//*[contains(text(), '27 feb')]"));
        List<WebElement> date_to_informed = driver.findElements(By.xpath("//*[contains(text(), '16 mar')]"));

        Assert.assertNotEquals(date_from_informed.size(), 0);
        Assert.assertNotEquals(date_to_informed.size(), 0);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
//        driver.close();
    }
}
