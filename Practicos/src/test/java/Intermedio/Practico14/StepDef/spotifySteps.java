package Intermedio.Practico14.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class spotifySteps {

    public WebDriver driver;
    String titulo = "";

    @Given("me encuentro en el sitio de spotify")
    public void me_encuentro_en_el_sitio_de_spotify() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
    }

    @When("consulto por el titulo de la pagina")
    public void consulto_por_el_titulo_de_la_pagina() {
        titulo = driver.getTitle();
    }

    @Then("se muestra el titulo {string}")
    public void se_muestra_el_titulo(String string) {
        Assert.assertEquals(titulo, string);
    }

    @Given("hago click en Registrame")
    public void hago_click_en_registrame() {
        driver.findElement(By.xpath("//*[@href='https://www.spotify.com/uy/signup/']")).click();
    }

    @Given("se muestra el formulario con los campos para el registro")
    public void se_muestra_el_formulario_con_los_campos_para_el_registro() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.spotify.com/uy/signup/");
    }

    @When("completo el email {string}")
    public void completo_el_email(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.name("email")).sendKeys(string);
    }

    @When("completo la contraseña {string}")
    public void completo_la_contraseña(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.name("password")).sendKeys(string);
    }

    @When("completo el alias {string}")
    public void completo_el_alias(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.name("alias")).sendKeys(string);
    }

    @When("hago click en el boton de registarme")
    public void hago_click_en_el_boton_de_registarme() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("hago click en registrarme");
    }

    @Then("se registra la cuenta")
    public void se_registra_la_cuenta() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Se registra la cuenta exitosamente");
    }

    @Then("se muestra un mensaje de exito")
    public void se_muestra_un_mensaje_de_exito() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Se muestra un mensaje de exito");
    }

    @Then("se muestra un mensaje de error {string}")
    public void se_muestra_un_mensaje_de_error(String string) {

    }

}
