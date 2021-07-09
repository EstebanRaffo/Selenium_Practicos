package Intermedio.Practico15;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OutlinedSteps {
    Integer valorInicial = 0;

    @Given("tengo {int} dolares")
    public void tengo_dolares(Integer monto) {
        valorInicial = monto;

    }

    @When("si gasto {int} dolares")
    public void si_gasto_dolares(Integer gasto) {
        valorInicial = valorInicial - gasto;
    }

    @Then("me sobran {int} dolares")
    public void me_sobran_dolares(Integer resto) {
        Assert.assertEquals(resto, valorInicial);

    }

    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////


    @Given("que estoy logeado en el sitio web")
    public void que_estoy_logeado_en_el_sitio_web() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("me encuentro en la pagina prinicipal")
    public void me_encuentro_en_la_pagina_prinicipal() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el nombre de usuario Marcus")
    public void busco_el_nombre_de_usuario_marcus() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("recibio un mensaje indicando No se encuentra en el sistema")
    public void recibio_un_mensaje_indicando_no_se_encuentra_en_el_sistema() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("busco el nombre de usuario Rox123")
    public void busco_el_nombre_de_usuario_rox123() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("recibio un mensaje indicando Nombre de usuario invalido")
    public void recibio_un_mensaje_indicando_nombre_de_usuario_invalido() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el nombre de usuario Mariangela")
    public void busco_el_nombre_de_usuario_mariangela() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("recibio un mensaje indicando Nombre de usuario inactivo")
    public void recibio_un_mensaje_indicando_nombre_de_usuario_inactivo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }




}
