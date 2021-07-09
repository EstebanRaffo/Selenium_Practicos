package Intermedio.Practico14.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class googleSteps {

    @Given("I am on Google site")
    public void i_am_on_google_site() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Comienza la busqueda!!");
    }

    @When("I search on {string}")
    public void i_search_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("El parametro es " + string);
    }

    @Then("I get information about {string}")
    public void i_get_information_about(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Se muestran los resultados sobre " + string );
    }
}
