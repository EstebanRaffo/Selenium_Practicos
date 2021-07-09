package Intermedio.Practico14.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class greetingSteps {

    @Given("I come in to the bar")
    public void i_come_in_to_the_bar() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entro al bar ");
    }

    @When("I find people")
    public void i_find_people() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Encuentro gente");
    }

    @Then("I greet them")
    public void i_greet_them() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Los saludo");
    }


}
