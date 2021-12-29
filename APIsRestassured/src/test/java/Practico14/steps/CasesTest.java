package Practico14.steps;

import Practico14.Case;
import Practico14.utilities.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CasesTest {
    Case aCase;
    String newCaseResponse;
    String caseId;
    String obtainedCaseInformation;


    @Given("I got a new case")
    public void i_got_a_new_case() {
        aCase = new Case(Constants.STATUS, Constants.CASE_REASON, Constants.CASE_ORIGIN, Constants.DESCRIPTION);
    }

    @When("I send a request to create a case")
    public void i_send_a_request_to_create_a_case() {
        newCaseResponse =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                    .body(aCase).log().all()
//                    .body("{\n" +
//                            "\"Status\": \"New\",\n" +
//                            "\"Reason\": \"Installation\",\n" +
//                            "\"Origin\": \"Web\",\n" +
//                            "\"Description\": \"caI got an error by running the installation\"\n" +
//                            "}")
                .when()
                    .post("/services/data/v51.0/sobjects/Case")
                .then()
                    .assertThat().statusCode(201)
                    .extract().asString();

        System.out.println("Respuesta: " + newCaseResponse);

        JsonPath jsResponse = new JsonPath(newCaseResponse);
        caseId = jsResponse.getString("id");
    }

    @Then("a case has been created")
    public void a_case_has_been_created() {
        //obtener la informacion del contacto previamente creado...
        obtainedCaseInformation =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                .when()
                    .get("services/data/v51.0/sobjects/Case/" + caseId )
                .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("Informacion del contacto obtenido: " + obtainedCaseInformation);

        Assert.assertTrue(caseId.startsWith("500"), "El id de Case debería comenzar con 500");
    }

}
