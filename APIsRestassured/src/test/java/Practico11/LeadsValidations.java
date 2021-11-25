package Practico11;

import Practico10.Lead;
import Practico11.Base.BaseClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
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
                    .assertThat().statusCode(400)
                    .body("errorCode", contains("REQUIRED_FIELD_MISSING"))
                    .body("message", contains("Required fields are missing: [Course__c]"))
                    .log().all()
                    .extract().asString();

        System.out.println("newAccountResponse: " + newAccountResponse);
    }

    @Test
    public void duplicateErrorOnLeadTest(){
// No pueden tener el mismo LastName e Email
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
                    .assertThat().statusCode(400)
                        .body("[0].duplicateResut.duplicateRule", is("Standard_Lead_Duplicate_Rule"))
                        .body("[0].duplicateResut.duplicateRuleEntityType", is("Lead"))
                        .body("[0].duplicateResut.errorMessage", is("Use one of these records?"))
                        .log().all()
                        .extract().asString();

        System.out.println("response: " + response);

    }

    @Test
    public void createLeadTest(){

        String randomEmail = "test" + Math.random() + "@gmail.com";
        System.out.println("Random Email : --> " + randomEmail);

        Lead newLead = new Lead("Lead Test", "UM", "APIs", randomEmail);

        String response =
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
                .body("id", startsWith("00Q"))
                .log().all()
                .extract().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);

        String id = js.getString("id");
        boolean success = js.getBoolean("success");

        System.out.println("id: " + id + "\n" + "success: " + success);

        Assert.assertTrue(id.startsWith("00Q"), "El Id de Lead no es valido");
        Assert.assertTrue(success, "No se obtuvo respuesta exitosa");

//        {
//            "id": "00Q5f000004KdBeEAK",
//            "success": true,
//            "errors": [
//
//             ]
//        }
//        {"id":"00Q5f000004KdBeEAK","success":true,"errors":[]}

    }

    @Test
    public void updateLeadTest(){

        String idLead = "00Q5f000004KdChEAK";
        String abody = "{\n" +
                        "    \"Rating\": \"Hot\"\n" +
                        "}";

        Response response =
            given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(abody)
            .when()
                .patch("/services/data/v51.0/sobjects/Lead/"+idLead)
            .then()
                .assertThat().statusCode(204).log().all().extract().response();

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test
    public void updateWithInvalidFieldTest(){

        String idLead = "00Q5f000004KdChEAK";
        String abody = "{\n" +
                        "    \"Ratin\": \"Cold\"\n" +
                        "}";

        Response response =
            given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(abody)
            .when()
                .patch("/services/data/v51.0/sobjects/Lead/"+idLead)
            .then()
                .assertThat()
                .statusCode(400)
                .body("[0].errorCode", is("INVALID_FIELD"))
                .body("[0].message", startsWith("No such column"))
                .log().all().extract().response();

        System.out.println(response.getBody());

        JsonPath js = response.jsonPath();
        String message = js.get("[0].message");
        String errorCode = js.get("[0].errorCode");

        Assert.assertTrue(message.startsWith("No such column"));
        Assert.assertEquals(errorCode, "INVALID_FIELD");
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}