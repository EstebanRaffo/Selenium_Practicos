package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.*;

import java.util.List;

public class AirbnbSteps {
    public WebDriver driver;
    public LandingPage landingPage;
    public HousingPage housingPage;

    @Given("estoy en la pagina de Airbnb")
    public void estoy_en_la_pagina_de_airbnb() {
        landingPage = new LandingPage(driver);
        landingPage.inicializarAirbnbPage();
    }

    @When("informo lugar Rosario, Santa Fe")
    public void informo_lugar_rosario_santa_fe() {
        landingPage.setLugar("Rosario, Santa Fe");
    }

    @When("informo checkin")
    public void informo_checkin() { landingPage.setDateCheckin(); }

    @When("informo checkout")
    public void informo_checkout() { landingPage.setDateCheckout(); }

    @When("informo huespedes {int} adultos")
    public void informo_huespedes(Integer cantidad) { landingPage.setHuespedes(cantidad); }

    @When("click en Buscar")
    public void click_en_buscar() {
        housingPage = landingPage.clickOnSearchButton();
    }

    @Then("se muestra el resultado de lugares donde alojarse en Rosario, Santa Fe")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_rosario_santa_fe() {

        Assert.assertTrue(housingPage.getCurrentUrl().contains("Rosario"));
        List<WebElement> cantidadHuespedesMsgList = housingPage.getHuespedesResultado();

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @Then("se muestra el resultado de lugares donde alojarse en Villa Carlos Paz, Cordoba")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_villa_carlos_paz_cordoba() {
        Assert.assertTrue(housingPage.getCurrentUrl().contains("Villa-Carlos-Paz"));
        List<WebElement> cantidadHuespedesMsgList = housingPage.getHuespedesResultado();

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @Then("se muestra el resultado de lugares donde alojarse en Villa La Angostura, Neuquén")
    public void se_muestra_el_resultado_de_lugares_donde_alojarse_en_villa_la_angostura_neuquen() {
        Assert.assertTrue(housingPage.getCurrentUrl().contains("Villa-La-Angostura"));
        List<WebElement> cantidadHuespedesMsgList = housingPage.getHuespedesResultado();

        Assert.assertNotEquals(cantidadHuespedesMsgList.size(), 0);
        Assert.assertNotNull(cantidadHuespedesMsgList);
    }

    @When("informo lugar Villa Carlos Paz, Cordoba")
    public void informo_lugar_villa_carlos_paz_cordoba() {
        landingPage.setLugar("Villa Carlos Paz, Cordoba");
    }

    @When("informo lugar Villa La Angostura, Neuquén")
    public void informo_lugar_villa_la_angostura_neuquén() {
        landingPage.setLugar("Villa La Angostura, Neuquén");
    }

    @Given("selecciono experiencias")
    public void selecciono_experiencias() {
        landingPage.selectExperiencias();
        String textExperiences = landingPage.getExperienciasTitle();
        Assert.assertTrue(textExperiences.contains("Escápate a la naturaleza"));
    }

    @When("informo lugar {string}")
    public void informo_lugar(String destino) {
        landingPage.setLugar(destino);
    }

    @When("informo fecha")
    public void informo_fecha() { landingPage.setDateOfExperience(); }

    @Then("se muestra el resultado de experiencias")
    public void se_muestra_el_resultado_de_experiencias() {
        String text_result = landingPage.getTitleResult();
        Assert.assertTrue(text_result.contains("experiencia"));
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
