package Practico13;

import com.opencsv.exceptions.CsvValidationException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class LeadsFromCSV {

    protected static String ACCESS_TOKEN = "";
    protected static String INSTANCE_URL = "";

    private RequestSpecification requestSpecificationForUAT = new RequestSpecBuilder()
            .addParam("grant_type", "password")
            .addParam("client_id", "3MVG9p1Q1BCe9GmBFxFv86hAuBdOHjVb8SJIhkKHfVZUqdeSKAWcYNzzoLMN3v6QC0yp61kUYE1fCmekeCX.O")
            .addParam("client_secret", "4381C0ECBB5F8BA367E97A1B75874BCBBB3EAE68ECD34D2523740B9EC56B68C8")
            .addParam("username", "e.fraffo@gmail.com")
            .addParam("password", "revolucionario87Ay7nOjpbwyCVhOpVNp2pSwfTx")
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();

    @BeforeClass
    public void readCSV() throws CsvValidationException, IOException {
        LeadDataLoader.leerCSV();
    }

    @BeforeTest
    public void getCredentials() {

        String respuesta =
            given()
                .spec(requestSpecificationForUAT)
            .when()
                .post("token")
            .then()
                .assertThat().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(respuesta);
        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");
    }

    @Test
    public void createLeadTest(){
        String leadId;

        RequestSpecification requestSpecificationForLeads = new RequestSpecBuilder()
                .addHeader("Content-type", "application/json")
                .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                .setBaseUri(INSTANCE_URL)
                .build();

        for(Lead newLead : LeadDataLoader.LISTA_LEADS) {
            //create the lead
            Response response =
                given()
                    .spec(requestSpecificationForLeads)
                    .body(newLead)
                .when()
                    .post("/services/data/v51.0/sobjects/Lead")
                .then()
                    .assertThat().statusCode(201).log().all().extract().response();

            JsonPath jsonPath = response.jsonPath();

            leadId = jsonPath.get("id");
            boolean status = jsonPath.get("success");

            Assert.assertTrue(leadId.startsWith("00Q"), "El id deberia comenzar con 00Q");
            Assert.assertTrue(status, "Error: el status deberia ser true");
            System.out.println("Se ha creado el lead : " + newLead);
        }
    }
}