package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.LandingPage;
import pageObjects.RegistrationPage;
import pageObjects.TermsPage;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class spotifySteps {
    public WebDriver driver;
    public LandingPage landingPage;
    public RegistrationPage registrationPage;
    public TermsPage termsPage;

    @Given("estoy en la pagina de spotify")
    public void estoy_en_la_pagina_de_spotify() {
        landingPage = new LandingPage(driver);
        landingPage.inicializarSpotifyPage();
    }

    @When("ingreso en premium")
    public void ingreso_en_premium() {
        landingPage.clickOnPremium();
    }

    @Then("está el plan Individual")
    public void está_el_plan_individual() {
        Assert.assertTrue(landingPage.getPlanIndividual().contains("Individual"));
    }

    @Then("está el plan Duo")
    public void está_el_plan_duo() {
        Assert.assertTrue(landingPage.getPlanDuo().contains("Duo"));
    }

    @Then("está el plan Familiar")
    public void está_el_plan_familiar() {
        Assert.assertTrue(landingPage.getPlanFamiliar().contains("Familiar"));
    }

    @Given("ingreso en registrarse")
    public void ingreso_en_registrarse() {
        registrationPage = landingPage.clickOnRegistrarse();
    }

    @When("ingreso el email ")
    public void ingreso_el_email_vacio() {
        registrationPage.fillingEmail("");
    }

    @Then("se presenta el mensaje de error Es necesario que introduzcas tu correo electrónico")
    public void se_presenta_el_mensaje_de_error_es_necesario_que_introduzcas_tu_correo_electrónico() {
        Assert.assertTrue(registrationPage.getErrorEmail().contains("Es necesario que introduzcas tu correo electrónico"));
    }

    @When("ingreso el email ffff")
    public void ingreso_el_email_ffff() {
        registrationPage.fillingEmail("ffff");
    }

    @Then("se presenta el mensaje de error Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com")
    public void se_presenta_el_mensaje_de_error_este_correo_electrónico_no_es_válido_asegúrate_de_que_tenga_un_formato_como_este_ejemplo_email_com() {
        Assert.assertTrue(registrationPage.getInvalidEmail().contains("Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com"));
    }

    @When("ingreso el email test@test.com")
    public void ingreso_el_email_test() {
        registrationPage.fillingEmail("test@test.com");
    }

    @Then("se presenta el mensaje de error Este correo electrónico ya está conectado a una cuenta.")
    public void se_presenta_el_mensaje_de_error_este_correo_electrónico_ya_está_conectado_a_una_cuenta() {
        Assert.assertTrue(registrationPage.getEmailRegistered().contains("Este correo electrónico ya está conectado a una cuenta."));
        Assert.assertTrue(registrationPage.getLinkIniciarSesion().contains("Inicia sesión"));
    }

    @When("navego a terminos y condiciones")
    public void navego_a_terminos_y_condiciones() {
        termsPage = landingPage.goTerms();
    }

    @Then("contiene Disfrutando Spotify")
    public void contiene_disfrutando_spotify() {
        Assert.assertTrue(termsPage.getDisfrutandoSpotify().contains("Disfrutando Spotify"));
    }

    @Then("contiene Pagos, cancelaciones y periodo de reflexión")
    public void contiene_pagos_cancelaciones_y_periodo_de_reflexión() {
        Assert.assertTrue(termsPage.getPagos().contains("Pagos, cancelaciones y periodo de reflexión"));
    }

    @Then("contiene Uso de nuestro servicio")
    public void contiene_uso_de_nuestro_servicio() {
        Assert.assertTrue(termsPage.getUsoServicio().contains("Uso de nuestro servicio"));
    }
}
