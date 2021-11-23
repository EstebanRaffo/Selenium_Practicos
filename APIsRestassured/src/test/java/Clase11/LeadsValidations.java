package Clase11;

import Clase11.Base.BaseClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LeadsValidations extends BaseClass {

    @Test
    public void requiredFieldOnLeadIsMissingCourse__cTest() {

        Lead newLead = new Lead("Perez Rodriguez", "UM");

        String newAccountResponse =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .body(newLead)
                        .when()
                        .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                        .assertThat()
                        .statusCode(400)
                        .body("errorCode", contains("REQUIRED_FIELD_MISSING"))
                        .body("message", contains("Required fields are missing: [Course__c]"))
                        .log().all()
                        .extract().asString();
    }


    @Test
    public void duplicateErrorOnLeadTest(){

        String randomEmail = "test" + Math.random() + "@gmail.com";

        System.out.println("Random Email : --> " + randomEmail);
        Lead newLead = new Lead("Perez Rodriguez", "UM", "APIs", randomEmail);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(newLead)
                .when()
                .post("/services/data/v51.0/sobjects/Lead")
                .then()
                .assertThat()
                .statusCode(201)
                .body("success", is(true))
                .log().all()
                .extract().asString();


        String response =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .body(newLead)
                        .when()
                        .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                        .assertThat()
                        .statusCode(400)
                        .body("[0].duplicateResut.duplicateRule", is("Standard_Lead_Duplicate_Rule"))
                        .body("[0].duplicateResut.duplicateRuleEntityType", is("Lead"))
                        .body("[0].duplicateResut.errorMessage", is("Use one of these records?"))
                        .log().all()
                        .extract().asString();

        System.out.println("----> " + response);

    }
}
