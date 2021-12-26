package Practico14.test;

import Practico10.Lead;
import Clase6.Contact;
import Practico14.Case;
import Practico14.utilities.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SalesforceTest extends BaseClass{
    Case aCase;
    String leadCompany = "IBM";
    String leadName = "Pedro Gomez";
    String leadCourse = "Course A1";
    Lead newLead;
    private String clientId;
    Contact newContact;
    String newContactResponse;
    String obtainedContactInformation;
    String contactLastName = "Pedro Martinez II";

    @Test
    public void createCaseTest(){

        System.out.println("RestAssured.baseURI: " + RestAssured.baseURI);

        aCase = new Case(Constants.STATUS, Constants.CASE_REASON, Constants.CASE_ORIGIN, Constants.DESCRIPTION);

        String newCaseResponse =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(aCase).log().all()
    //                    .body("{\n" +
    //                            "\"Status\": \"New\",\n" +
    //                            "\"Reason\": \"Installation\",\n" +
    //                            "\"Origin\": \"Web\",\n" +
    //                            "\"Description\": \"I got an error by running the installation\"\n" +
    //                            "}")
                .when()
                    .post("/services/data/v51.0/sobjects/Case")
                .then()
                    .assertThat().statusCode(201).extract().asString();

        System.out.println("Respuesta newCaseResponse: " + newCaseResponse);
    }

    @Test
    public void createContactTest(){
        newContact = new Contact(contactLastName);

        newContactResponse = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .body(newContact).log().all()
                .when()
                .post("/services/data/v51.0/sobjects/Contact")
                .then()
                .assertThat().statusCode(201).extract().asString();

        System.out.println("Respuesta newContactResponse: " + newContactResponse);

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

    @Test
    public void createLeadTest(){
        newLead = new Lead(leadName, leadCompany, leadCourse);

        //create the lead
        Response response =
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

        JsonPath jsonPath = response.jsonPath();

        String leadId = jsonPath.get("id");
        boolean status = jsonPath.get("success");

        Assert.assertTrue(leadId.startsWith("00Q"), "El id deberia comenzar con 00Q");
        Assert.assertTrue(status, "Error: el status deberia ser true");
    }
}
