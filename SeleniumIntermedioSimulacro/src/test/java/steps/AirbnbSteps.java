package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AirbnbSteps {
    public WebDriver driver;
    public WebDriverWait wait;


    @Given("estoy en la pagina de Airbnb")
    public void estoy_en_la_pagina_de_airbnb() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.airbnb.com.ar");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        driver.findElement(By.xpath("//button[@data-testid='accept-btn']")).click();
    }

    @FindBy(xpath = "//input[@placeholder='¿A dónde vas?']")
    public WebElement lugar;

    @FindBy(xpath = "//div[contains(text(), 'Check-in')]")
    public WebElement checkin;

    @FindBy(xpath = "//div[@data-testid='datepicker-day-2021-06-25']")
    public WebElement datein;

    @FindBy(xpath = "//div[@data-testid='datepicker-day-2021-07-10']")
    public WebElement dateout;

    @FindBy(xpath = "//div[contains(text(), 'Check-out')]")
    public WebElement checkout;

    @FindBy(xpath = "//div[contains(text(), 'Huéspedes')]")
    public WebElement huespedes;

    @FindBy(xpath = "//button[@data-testid='stepper-adults-increase-button']")
    public WebElement increaseAdults;

    @FindBy(xpath = "//button[@data-testid='structured-search-input-search-button']")
    public WebElement searchButton;

    @When("informo lugar Rosario, Santa Fe")
    public void informo_lugar_rosario_santa_fe() {
        lugar.sendKeys("Rosario, Santa Fe");
    }

    @When("informo checkin")
    public void informo_checkin() {
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(checkin));
        checkin.click();
        datein.click();
    }

    @When("informo checkout")
    public void informo_checkout() {
        dateout.click();
    }

    @When("informo huespedes {int} adultos")
    public void informo_huespedes(Integer cantidad) {
        huespedes.click();

        for(int i = 0; i < cantidad; i++){
            increaseAdults.click();
        }
    }

    @When("click en Buscar")
    public void click_en_buscar() {
        searchButton.click();
    }

    @Then("se muestra el resultado de lugares donde alojarse en Rosario, Santa Fe")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_rosario_santa_fe() {
        Assert.assertTrue(driver.getCurrentUrl().contains("Rosario"));
        List<WebElement> cantidadHuespedesMsgList = driver.findElements(By.xpath("//*[contains(text(),'2 huéspedes')]"));

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @Then("se muestra el resultado de lugares donde alojarse en Villa Carlos Paz, Cordoba")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_villa_carlos_paz_cordoba() {
        Assert.assertTrue(driver.getCurrentUrl().contains("Villa-Carlos-Paz"));
        List<WebElement> cantidadHuespedesMsgList = driver.findElements(By.xpath("//*[contains(text(),'2 huéspedes')]"));

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @Then("se muestra el resultado de lugares donde alojarse en Villa La Angostura, Neuquén")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_villa_la_angostura_neuquen() {
        Assert.assertTrue(driver.getCurrentUrl().contains("Villa-La-Angostura"));
        List<WebElement> cantidadHuespedesMsgList = driver.findElements(By.xpath("//*[contains(text(),'2 huéspedes')]"));

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @When("informo lugar Villa Carlos Paz, Cordoba")
    public void informo_lugar_villa_carlos_paz_cordoba() {
        lugar.sendKeys("Villa Carlos Paz, Cordoba");
    }

    @When("informo lugar Villa La Angostura, Neuquén")
    public void informo_lugar_villa_la_angostura_neuquén() {
        lugar.sendKeys("Villa La Angostura, Neuquén");
    }

    @Given("selecciono experiencias")
    public void selecciono_experiencias() {
        driver.findElement(By.xpath("(//span[contains(text(), 'Experiencias')])[1]")).click();
        WebElement textExperiences = driver.findElement(By.xpath("(//span[contains(text(), 'Escápate a la naturaleza')])[3]"));
        Assert.assertTrue(textExperiences.getText().contains("Escápate a la naturaleza"));
    }

    @When("informo lugar {string}")
    public void informo_lugar(String destino) {
        lugar.sendKeys(destino);
    }

    @When("informo fecha")
    public void informo_fecha() {
        driver.findElement(By.xpath("//div[contains(text(), 'Agregá cuándo querés ir')]")).click();
        datein.click();
    }

    @Then("se muestra el resultado de experiencias")
    public void se_muestra_el_resultado_de_experiencias() {
        WebElement text_result = driver.findElement(By.xpath("//h1[contains(text(), 'experiencia')]"));
        Assert.assertTrue(text_result.getText().contains("experiencia"));
    }

    @Then("se muestran lugares cercanos")
    public void se_muestran_lugares_cercanos() {
       List<WebElement> lugares_cercanos = driver.findElements(By.xpath("//span[@class='_1i13tcg']"));

       System.out.println("Explorá lugares cercanos: ");
       for(WebElement unLugar : lugares_cercanos){
           List<WebElement> ubicacion_y_horas = unLugar.findElements(By.tagName("span"));

           System.out.println("Ubicacion: " + ubicacion_y_horas.get(0).getText());
           System.out.println("Horas en auto: " + ubicacion_y_horas.get(1).getText());
       }
    }
}
