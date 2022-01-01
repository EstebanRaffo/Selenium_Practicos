package Practico14.steps;

import Practico10.Lead;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import static Practico14.steps.AuthenticationHelper.ACCESS_TOKEN;

import static io.restassured.RestAssured.given;

public class LeadTest {
        String leadCompany = "IBM";
        String leadName = "Pedro Lopez";
        String leadCourse = "Course A0";
        Lead newLead;
        Response newLeadResponse;
        String newLeadInformation;

        @Given("I got a lead")
        public void i_got_a_new_case() {
                newLead = new Lead(leadName, leadCompany, leadCourse);
        }

        @When("I send a request to create the lead")
        public void i_send_a_request_to_create_a_case() {
                System.out.println("RestAssured.baseURI: " + RestAssured.baseURI);

                //create the lead
                newLeadResponse =
                        given()
                                .header("Content-type", "application/json")
                                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                                .body(newLead).log().all()
                        .when()
                                .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                                .assertThat()
                                .statusCode(201)
                                .log().all()
                                .extract().response();

                System.out.println("Respuesta newLeadResponse: " + newLeadResponse);

        }

        @Then("a lead is created")
        public void a_case_has_been_created() {
                JsonPath jsonPath = newLeadResponse.jsonPath();

                String leadId = jsonPath.get("id");
                boolean status = jsonPath.get("success");

                //obtener la informacion del lead previamente creado...
                newLeadInformation =
                        given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .when()
                                .get("services/data/v51.0/sobjects/Lead/" + leadId )
                        .then()
                                .assertThat().statusCode(200).extract().asString();

                System.out.println("Informacion del contacto obtenido: " + newLeadInformation);

                Assert.assertTrue(leadId.startsWith("00Q"), "El id deberia comenzar con 00Q");
                Assert.assertTrue(status, "Error: el status deberia ser true");
        }

        @Then("new lead information expected is the same as lead sent")
        public void new_lead_information_expected_is_the_same_as_lead_sent() {
                JsonPath js = new JsonPath(newLeadInformation);

                String lastName = js.getString("LastName");
                String company = js.getString("Company");
                String course = js.getString("Course__c");

                Assert.assertEquals(lastName, leadName, "Se esperaba otro apellido");

        }
}

