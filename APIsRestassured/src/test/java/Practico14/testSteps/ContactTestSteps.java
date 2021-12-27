package Practico14.testSteps;

import Clase6.Contact;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static Practico14.testSteps.AuthenticationHelper.ACCESS_TOKEN;
import static io.restassured.RestAssured.given;

public class ContactTestSteps {
    Contact newContact;
    String newContactResponse;
    String obtainedContactInformation;
    String contactLastName = "Martinez III";

    @Given("I got a new contact")
    public void i_got_a_new_contact() {
        newContact = new Contact(contactLastName);
    }

    @When("I send a request to create a contact")
    public void i_send_a_request_to_create_a_contact() {
        newContactResponse =
            given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(newContact).log().all()
            .when()
                .post("/services/data/v51.0/sobjects/Contact")
            .then()
                .assertThat().statusCode(201).extract().asString();

        System.out.println("Respuesta newContactResponse: " + newContactResponse);
    }

    @Then("a contact has been created")
    public void a_contact_has_been_created() {
        JsonPath js = new JsonPath(newContactResponse);

        String contactId = js.get("id");

        //obtener la informacion del contacto previamente creado...
        obtainedContactInformation =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                    .get("services/data/v51.0/sobjects/Contact/" + contactId )
                .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("Informacion del contacto obtenido: " + obtainedContactInformation);
    }

    @Then("the contact last name is the same as the contact sent")
    public void the_contact_last_name_is_the_same_as_the_contact_sent() {
        JsonPath js = new JsonPath(obtainedContactInformation);

        String obtainedContactLastName = js.get("LastName");

        Assert.assertEquals(obtainedContactLastName, contactLastName, "Error: se esperaba otro apellido" );
    }
}
