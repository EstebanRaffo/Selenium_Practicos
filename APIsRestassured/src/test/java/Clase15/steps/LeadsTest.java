package Clase15.steps;

import Clase10.Lead;
import Clase14.AuthenticationHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LeadsTest {
    String leadAsJson = "";
    String leadId = "";
    String status = "";
    String convertedAccountId = "";
    String convertedContactId = "";
    String lastName = "My Selenium Lead 12";
    Lead newLead;

    @Given("I got a lead")
    public void i_got_a_lead() {
        //leadAsJson = "{\"LastName\":\"" + lastName +"\", \"Status\": \"Open\", \"Company\": \"This is a company 12\"}";
        newLead = new Lead(lastName, "This is a company 12");
    }

    @When("I send a request to create the lead")
    public void i_send_a_request_to_create_the_lead() {
        RestAssured.baseURI = AuthenticationHelper.INSTANCE_URL;

        String newLeadResp =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                        .body(newLead)
                        .log().all().when()
                        .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                        .assertThat().statusCode(201)
                        .extract().asString();


        System.out.println("Respuesta: " + newLeadResp);

        JsonPath leadInfo = new JsonPath(newLeadResp);
        leadId = leadInfo.getString("id");
        status = leadInfo.getString("success");

        System.out.println(leadId);
        System.out.println(status);
    }

    @Then("a lead is created")
    public void a_lead_is_created() {
        Assert.assertEquals(status, "true", "Error: A lead should have been created");
        Assert.assertTrue(leadId.startsWith("00Q"), "Error: the id is not of a lead");
    }

    @When("I want to update the last name")
    public void i_want_to_update_the_last_name() {
        Response updateResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN).log().all()
                        .body("{\"LastName\":\"Cowen\", \"Company\": \"My Tech Company\"}")
                        .log().all()
                        .when()
                        .patch("/services/data/v51.0/sobjects/Lead/" + leadId)
                        .then()
                        .extract().response();

        System.out.println(updateResponse.getStatusCode());
        Assert.assertEquals(updateResponse.getStatusCode(), 204);

    }

    @Then("the lead is updated")
    public void the_lead_is_updated() {
        //get the lead based on the leadId
        //check the updated fields...
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                .when()
                .get("/services/data/v51.0/sobjects/Lead/" + leadId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("LastName", is("Cowen"))
                .body("Company", is("My Tech Company"));
    }

    @When("I want to update the lead rating to Hot")
    public void i_want_to_update_the_lead_rating_to_hot() {
        Response updateResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN).log().all()
                        .body("{\"Rating\":\"Hot\"}")
                        .log().all()
                        .when()
                        .patch("/services/data/v51.0/sobjects/Lead/" + leadId)
                        .then()
                        .extract().response();

        System.out.println(updateResponse.getStatusCode());
        Assert.assertEquals(updateResponse.getStatusCode(), 204);

    }

    @Then("the lead is converted")
    public void the_lead_is_converted() {
        //CONVERT THE LEAD
        String convertedLeadInformation =
                given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN).log().all()
                        .log().all()
                        .when()
                        .get("/services/data/v51.0/sobjects/Lead/" + leadId)
                        .then()
                        .log().all().extract().asString();

        JsonPath leadInfoJsonPath = new JsonPath(convertedLeadInformation);
        String leadStatus = leadInfoJsonPath.getString("Status");

        Assert.assertEquals(leadStatus, "Closed - Converted", "Error: the lead has been not converted");

        // OBTENER Converted Account Id
        convertedAccountId = leadInfoJsonPath.getString("ConvertedAccountId");

        // OBTENER Converted Contact Id
        convertedContactId = leadInfoJsonPath.getString("ConvertedContactId");

    }

    @Then("a contact is created from the lead")
    public void a_contact_is_created_from_the_lead() {

        //GET CONTACT INFORMATION
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                .log().all()
                .when()
                .get("/services/data/v51.0/sobjects/Contact/" + convertedContactId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("Name", is(lastName))
                .body("CleanStatus", is("Pending"));

    }

    @Then("an account is created from the lead")
    public void an_account_is_created_from_the_lead() {

    }



    @Given("I got a lead {string} and {string}")
    public void i_got_a_lead_and(String lastName, String company) {
        newLead = new Lead(lastName, company);
    }




}
