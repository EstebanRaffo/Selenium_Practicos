package Clase13;

import Clase13.Lead;
import com.opencsv.exceptions.CsvValidationException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class LeadsFromCSV {

    protected static String ACCESS_TOKEN = "";
    protected static String INSTANCE_URL = "";

    private RequestSpecification requestSpecificationForUAT = new RequestSpecBuilder()
            .addParam("grant_type", "password")
            .addParam("client_id", "3MVG9cHH2bfKACZYj3S3as4gJ9.9.zVTytlu8Q61HwPUN.NtPEwJqbFOp4pEy5gm6fsezrxm_WMW9YkQAcPW3")
            .addParam("client_secret", "696513F287215272F6EC9E2398C94873CF596E8CF92CC9F9F096ED4FE1C8A37F")
            .addParam("username", "seleniumcurso@gmail.com")
            .addParam("password", "holahola123PkC9nQP5ZkNgQahPfnQgWWHc")
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();

    @BeforeClass
    public void readCSV() throws CsvValidationException, IOException {
        LeadDataLoader.leerCSV();
    }

    @BeforeTest
    public void getCredentials() {

        String respuesta = given()
                .spec(requestSpecificationForUAT)
                .when()
                .post("token")
                .then().assertThat().statusCode(200)
                .extract().asString();

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
                            .assertThat()
                            .statusCode(201)
                            .log().all()
                            .extract().response();

            JsonPath jsonPath = response.jsonPath();

            leadId = jsonPath.get("id");
            boolean status = jsonPath.get("success");

            Assert.assertTrue(leadId.startsWith("00Q"), "El id deberia comenzar con 00Q");
            Assert.assertTrue(status, "Error: el status deberia ser true");
            System.out.println("Se ha creado el lead : " + newLead);
        }

    }

}