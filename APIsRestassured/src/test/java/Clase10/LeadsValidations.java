package Clase10;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LeadsValidations {
    private static String ACCESS_TOKEN = "";
    private static String INSTANCE_URL = "";

    @BeforeTest
    public void obtainAuthorizationFromSalesforce() {
        //obtener la autorizacion para trabajar en la instancia de Salesforce...

        RestAssured.baseURI = "https://login.salesforce.com/services/oauth2/";

        String respuesta = given()
                //.header("Content-type", "application/json")
                .queryParam("grant_type", "password")
                .queryParam("client_id", "3MVG9p1Q1BCe9GmCSj33tBJjfBszMSaJDvJDfwwj2VeMpQy4rRnWS_IXrAPj41qd.0V3e50FHZMBySnXCLnzC")
                .queryParam("client_secret", "E63928F2EF75E18F2562AE5CEA50F56897C29092C6D6E3AF9718E16218FE4FC8")
                .queryParam("username", "seleniumcurso+123@gmail.com")
                .queryParam("password", "holamundo123FxZ0KNxCgPgIQ0TDPYW7HmkmF")
                .when()
                .post("token")
                .then().assertThat().statusCode(200)
                .extract().asString();

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");

        RestAssured.baseURI = INSTANCE_URL;
        System.out.println("Access token: --> " + ACCESS_TOKEN);
        System.out.println("Instance Url: --> " + INSTANCE_URL);
    }


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