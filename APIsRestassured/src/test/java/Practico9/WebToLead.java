package Practico9;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class WebToLead {

    private static String ACCESS_TOKEN = "";
    private static String INSTANCE_URL = "";

    @DataProvider(name = "accounts")
    public Object[][] dataProviderAccounts() {
        return new Object[][]{
                {"Juan", "Esta es la cuenta de Juan"},
                {"Maria", "Esta es la cuenta de Maria"}
        };
    }


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

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");

        RestAssured.baseURI = INSTANCE_URL;
        System.out.println("Access token: --> " + ACCESS_TOKEN);
        System.out.println("Instance Url: --> " + INSTANCE_URL);
    }


    @Test
    public void webToLeadTest() throws InterruptedException {

        String query = "select+id+,+lastname+,+status+from+Lead+where+lastname=+'Picapiedra'";

        String salesforceId =
                given()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .urlEncodingEnabled(false)
                    .queryParam("q", query)
                .when()
                    .get("/services/data/v51.0/query/")
                .then()
                    .log().all().assertThat().statusCode(200).extract().path("records[0].Id");

        System.out.println("records[0].Id ---> " + salesforceId);

        String newLeadInfo =
                given()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .urlEncodingEnabled(false)
                    .queryParam("q", query)
                .when()
                    .get("/services/data/v51.0/query/")
                .then()
                    .log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js = new JsonPath(newLeadInfo);

        int totalSize = js.get("totalSize");
        Assert.assertEquals(totalSize, 1, " Error: se esperaba un solo elemento");

        String leadId = js.get("records[0].Id");
        Assert.assertTrue(leadId.startsWith("00Q"), "Error: el objeto obtenido no es un lead!!");

        String lastName = js.get("records[0].LastName");
        System.out.println(lastName);
        Assert.assertEquals(lastName, "Picapiedra", "Error: se esperaba otro apellido");

        String status = js.get("records[0].Status");
        System.out.println(status);
    }

    @Test
    public void getAccountIdTest(){

        String query = "select+id+,+lastname+,+status+from+Lead+where+Id=+'00Q5f000004IsN6EAK'";

        String salesforceId =
                given()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .urlEncodingEnabled(false)
                    .queryParam("q", query)
                .when()
                    .get("/services/data/v51.0/query/")
                .then()
                    .log().all().assertThat().statusCode(200).extract().path("records[0].Id");

        System.out.println("records[0].Id ---> " + salesforceId);

        String accountInfo =
                given()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .urlEncodingEnabled(false)
                    .queryParam("q", query)
                .when()
                    .get("/services/data/v51.0/query/")
                .then()
                    .log().all().assertThat().statusCode(200).extract().asString();

        System.out.println("---> " + accountInfo);

    }
}

