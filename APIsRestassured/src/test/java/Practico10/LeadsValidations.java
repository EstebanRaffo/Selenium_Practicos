package Practico10;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class LeadsValidations {
    private static String ACCESS_TOKEN = "";
    private static String INSTANCE_URL = "";

    @BeforeTest
    public void obtainAuthorizationFromSalesforce() {
        //obtener la autorizacion para trabajar en la instancia de Salesforce...

        RestAssured.baseURI = "https://login.salesforce.com/services/oauth2/";

        String respuesta =
            given()
                //.header("Content-type", "application/json")
                .queryParam("grant_type", "password")
                .queryParam("client_id", "3MVG9p1Q1BCe9GmBFxFv86hAuBdOHjVb8SJIhkKHfVZUqdeSKAWcYNzzoLMN3v6QC0yp61kUYE1fCmekeCX.O")
                .queryParam("client_secret", "4381C0ECBB5F8BA367E97A1B75874BCBBB3EAE68ECD34D2523740B9EC56B68C8")
                .queryParam("username", "e.fraffo@gmail.com")
                .queryParam("password", "revolucionario87Ay7nOjpbwyCVhOpVNp2pSwfTx")
            .when()
                .post("token")
            .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("respuesta: " + respuesta);

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
}