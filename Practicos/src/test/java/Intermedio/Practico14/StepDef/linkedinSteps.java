package Intermedio.Practico14.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class linkedinSteps {

    @Given("estoy en la pagina de login de linkedin")
    public void estoy_en_la_pagina_de_login_de_linkedin() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Estoy en linkedin");
    }

    @When("ingreso mi email y contraseña")
    public void ingreso_mi_email_y_contraseña() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Ingreso mi email y contraseña correctamente");
    }

    @Then("entro a mi cuenta")
    public void entro_a_mi_cuenta() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entro a mi cuenta");
    }

    @When("ingreso mi email incorrectamente")
    public void ingreso_mi_email_incorrectamente() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("1");
    }

    @And("ingreso mi contraseña correctamente")
    public void ingreso_mi_contraseña_correctamente() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("2");
    }

    @When("ingreso mi contraseña incorrectamente")
    public void ingreso_mi_contraseña_incorrectamente() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Se ingresa la contraseña incorrectamente");
    }

    @Then("se despliega un error de login")
    public void se_despliega_un_error_de_login() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("3");
    }
}
