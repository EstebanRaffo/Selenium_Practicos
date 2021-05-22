package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class outlinedSteps {
    private Integer resultado, monto_inicial, saldo;

    @Given("tengo una calculadora")
    public void tengo_una_calculadora() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Iniciar Calculadora");
    }

    @When("ingreso {int} y {int}")
    public void ingreso_y(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        resultado = int1 + int2;
    }

    @Then("la suma es {int}")
    public void la_suma_es(Integer suma) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(resultado, suma);
    }


    @Given("monto inicial de {int}")
    public void monto_inicial_de(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        monto_inicial = int1;
    }

    @When("gasto de {int}")
    public void gasto_de(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        saldo = monto_inicial - int1;
    }

    @Then("el saldo restante es {int}")
    public void el_saldo_restante_es(Integer saldo_resultado) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(saldo_resultado, saldo);
    }


    @Given("estoy logeado en el sitio web")
    public void estoy_logeado_en_el_sitio_web() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("estoy en la pagina principal")
    public void estoy_en_la_pagina_principal() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Marcus")
    public void busco_el_usuario_marcus() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje No se encuentra en el sistema")
    public void obtengo_el_mensaje_no_se_encuentra_en_el_sistema() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Rox123")
    public void busco_el_usuario_rox123() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje Nombre de usuario invalido")
    public void obtengo_el_mensaje_nombre_de_usuario_invalido() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Matt")
    public void busco_el_usuario_matt() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje Nombre de usuario inactivo")
    public void obtengo_el_mensaje_nombre_de_usuario_inactivo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
