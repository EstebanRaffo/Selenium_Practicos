package Intermedio.Practico14.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class calculadoraSteps {
    private Integer suma;

    @Given("tengo una calculadora")
    public void tengo_una_calculadora() {
        suma = 0;
        System.out.println("Se inicializa la calculadora...");
    }

    @When("sumo {int} y {int}")
    public void sumo_y(Integer int1, Integer int2) {
        suma = int1 + int2;
        System.out.println("Se han sumado dos numeros...");
    }

    @Then("la suma es {int}")
    public void la_suma_es(Integer int1) {

        Assert.assertEquals(suma, int1);
    }




}
