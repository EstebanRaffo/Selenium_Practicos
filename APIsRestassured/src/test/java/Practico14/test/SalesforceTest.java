package Practico14.test;

import Clase10.Lead;
import Clase14.AuthenticationHelper;
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
    Lead newLead;
    private String clientId;
    Contact newContact;
    String newContactResponse;
    String obtainedContactInformation;
    String contactLastName = "Pedro Martinez";

    @Test
    public void createCaseTest(){
//        RestAssured.baseURI = AuthenticationHelper.INSTANCE_URL;

        aCase = new Case(Constants.STATUS, Constants.CASE_REASON, Constants.CASE_ORIGIN, Constants.DESCRIPTION);

        String newCaseResponse =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
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

        System.out.println("Respuesta: " + newCaseResponse);
    }

    @Test
    public void createContactTest(){
        newContact = new Contact(contactLastName);

        newContactResponse = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                .body(newContact)
                .when()
                .post("/services/data/v51.0/sobjects/Contact")
                .then()
                .assertThat().statusCode(201).extract().asString();

        System.out.println(newContactResponse);

        JsonPath js = new JsonPath(newContactResponse);

        String contactId = js.get("id");

        //obtener la informacion del contacto previamente creado...
        obtainedContactInformation =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                .when()
                    .get("services/data/v51.0/sobjects/Contact/" + contactId )
                .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("Informacion del contacto obtenido: " + obtainedContactInformation);
    }

    @Test
    public void createLeadTest(){
        newLead = new Lead(leadName, leadCompany);

        //create the lead
        Response response =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(newLead)
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
