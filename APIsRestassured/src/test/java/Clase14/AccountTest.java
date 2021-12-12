package Clase14;

import Clase11.Utilities.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class AccountTest {
    Account anAccount;
    String newAccountResponse;
    String clientId;


    @Given("I got a new account")
    public void i_got_a_new_account() {
        anAccount = new Account("My new account 2021", "This is a new account");
    }

    @When("I send a request to create an account")
    public void i_send_a_request_to_create_an_account() {
        newAccountResponse =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                        .body(anAccount)
                        .when()
                        .post("/services/data/v51.0/sobjects/Account")
                        .then()
                        .assertThat().statusCode(201)
                        .extract().asString();

        System.out.println("Respuesta: " + newAccountResponse);
    }

    @Then("an account has been created")
    public void an_account_has_been_created() {
        System.out.println("----> " +  newAccountResponse);
        JsonPath jsonPath = new JsonPath(newAccountResponse);
        String accountId = jsonPath.get("id");
        boolean accountCreated = jsonPath.get("success");
        Assert.assertTrue(accountId.startsWith("001"), "Error: el id obtenido no pertence a una account");
        Assert.assertTrue(accountCreated, "Error: la respuesta no fue de exito");
    }
}
