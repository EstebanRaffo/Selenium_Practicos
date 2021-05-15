package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ejerciciosSteps {

    @Given("una Persona")
    public void una_persona(){
        System.out.println("Persona A");
    }

    @When("se encuentra con otra")
    public void se_encuentra_con_otra(){
        System.out.println("se encuentra con Persona B");
    }

    @Then("la saluda")
    public void la_saluda(){
        System.out.println("Persona A dice Hola a Persona B");
    }
}
