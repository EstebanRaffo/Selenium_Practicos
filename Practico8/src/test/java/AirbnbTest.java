import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AirbnbTest extends BaseTest {
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

        WebElement cookies_button = driver.findElement(By.xpath("//button[@data-testid='accept-btn']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(cookies_button).click().build().perform();

        Thread.sleep(5000);
        WebElement place = driver.findElement(By.xpath("//input[@placeholder='¿A dónde vas?']"));
        Action completePlace = actions.moveToElement(place).click().sendKeys(Constants.DESTINY).build();
        completePlace.perform();

        WebElement when_button = driver.findElement(By.xpath("(//div[contains(text(), '¿Cuándo?')])[1]"));
        actions.moveToElement(when_button).click().build().perform();

        WebElement date_from_elem = driver.findElement(By.xpath("//div[@data-testid='datepicker-day-"+Constants.DATE_FROM_VALUE.getYear()+"-"+Constants.DATE_FROM_VALUE.getMonth()+"-"+Constants.DATE_FROM_VALUE.getDay()+"']/div"));
        WebElement date_to_elem = driver.findElement(By.xpath("//div[@data-testid='datepicker-day-"+Constants.DATE_TO_VALUE.getYear()+"-"+Constants.DATE_TO_VALUE.getMonth()+"-"+Constants.DATE_TO_VALUE.getDay()+"']/div"));
        actions.moveToElement(date_from_elem).click().build().perform();
        actions.moveToElement(date_to_elem).click().build().perform();

        WebElement cuantos_button = driver.findElement(By.xpath("//div[contains(text(), '¿Cuántos?')]"));
        actions.moveToElement(cuantos_button).click().build().perform();

        WebElement incr_adults_button = driver.findElement(By.xpath("//button[@data-testid='stepper-adults-increase-button']"));
        WebElement incr_children_button = driver.findElement(By.xpath("//button[@data-testid='stepper-children-increase-button']"));
        WebElement incr_infants_button = driver.findElement(By.xpath("//button[@data-testid='stepper-infants-increase-button']"));
        Action clickOnIncremAdultsButton = actions.moveToElement(incr_adults_button).click().build();
        Action clickOnIncremChildrenButton = actions.moveToElement(incr_children_button).click().build();
        Action clickOnIncremInfantsButton = actions.moveToElement(incr_infants_button).click().build();

        for(int i = 0; i < Constants.ADULTS; i++){
            clickOnIncremAdultsButton.perform();
        }

        for(int i = 0; i < Constants.CHILDREN; i++){
            clickOnIncremChildrenButton.perform();
        }

        for(int i = 0; i < Constants.INFANTS; i++){
            clickOnIncremInfantsButton.perform();
        }

        WebElement search_button = driver.findElement(By.xpath("//button[@data-testid='structured-search-input-search-button']"));
        actions.moveToElement(search_button).click().build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/div[contains(text(),'"+Integer.toString(Constants.hosts)+" huéspedes')]")));

        List<WebElement> hosts_informed = driver.findElements(By.xpath("//*[contains(text(), '"+Integer.toString(Constants.hosts)+" huéspedes')]"));
        List<WebElement> date_from_informed = driver.findElements(By.xpath("//*[contains(text(), '" + Constants.DATE_FROM_VALUE.getFormatDay() + " " + Constants.DATE_FROM_VALUE.getMonthWord() +"')]"));
        List<WebElement> date_to_informed = driver.findElements(By.xpath("//*[contains(text(), '" + Constants.DATE_TO_VALUE.getFormatDay() + " " + Constants.DATE_TO_VALUE.getMonthWord() +"')]"));

        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.DESTINY));
        Assert.assertNotEquals(hosts_informed.size(), 0);
        Assert.assertNotEquals(date_from_informed.size(), 0);
        Assert.assertNotEquals(date_to_informed.size(), 0);
    }

    @Test (dependsOnMethods = {"searchHousing"})
    public void goToFooterTest() throws InterruptedException {

        Thread.sleep(4000);

        WebElement trustLink = driver.findElement(By.xpath("//a[@href='/trust']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", trustLink);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        //driver.close();
    }
}
