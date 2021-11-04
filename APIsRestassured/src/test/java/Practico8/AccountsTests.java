package Practico8;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AccountsTests {

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
                    .log().all().assertThat().statusCode(200).extract().asString();

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");

        RestAssured.baseURI = INSTANCE_URL;
        System.out.println("Access token: --> " + ACCESS_TOKEN);
        System.out.println("Instance Url: --> " + INSTANCE_URL);
    }


    @DataProvider(name = "accounts")
    public Object[][] dataProviderAccounts() {
        return new Object[][]{
                {"Pedro Lopez", "Esta es la cuenta de Pedro", "pedro.com.ar"},
                {"Maria Lopez", "Esta es la cuenta de Maria", "maria.com.ar"}
        };
    }

    @Test(dataProvider = "accounts")
    public void loadAccountsTest(String name, String description, String site) {

        System.out.println("---> " + name + "  " + description);

        Account anAccount = new Account(name, description, site);

        String newAccountResponse =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(anAccount)
                .when()
                    .post("/services/data/v51.0/sobjects/Account")
                .then()
                    .log().all().assertThat().statusCode(201).extract().asString();

        System.out.println("Respuesta: " + newAccountResponse);
        JsonPath js = new JsonPath(newAccountResponse);
        String contactId = js.get("id");
        Assert.assertTrue(contactId.startsWith("001"));
    }


    @Test
    public void getAccountTest() {
        String accountInfo =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                    .get("services/data/v51.0/sobjects/Account/" + "0035f000004csvZAAQ" )
                .then()
                    .log().all().assertThat().statusCode(200).extract().asString();

        System.out.println("***********  Contact Information ***************");
        System.out.println("---> " + accountInfo);

        JsonPath js = new JsonPath(accountInfo);
        String accountName = js.get("Name");
        Assert.assertEquals(accountName, "United Oil & Gas Corp.", "Error: se esperaba otro nombre de cuenta!!");
    }

    @Test
    public void deleteAccountTest(){
        String responseInfo =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                    .delete("services/data/v51.0/sobjects/Contact/" + "0035f000004csvZAAQ" )
                .then()
                    .assertThat().statusCode(204).extract().asString();

        System.out.println("***********  Contact Information ***************");
        System.out.println("---> " + responseInfo);
    }


    /*
    @Esteban
    02-11-2021
    Se realiza la prueba de un POST sin access token
    Se espera mensajes de error
     */
    @Test
    public void emptyTokenErrorTest() {

        Account anAccount = new Account("Juan", "Mi cuenta", "juan@mail.com");

        String newAccountResponse =
                given()
                    .header("Content-type", "application/json")
                    //.header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(anAccount)
                .when()
                    .post("/services/data/v51.0/sobjects/Account")
                .then()
                    .log().all().assertThat().statusCode(401).extract().asString();

        System.out.println("--> " + newAccountResponse);

        Assert.assertTrue(newAccountResponse.contains("INVALID_SESSION_ID"), "Error: deberia estar el error code invalid session id");
        Assert.assertTrue(newAccountResponse.contains("Session expired or invalid"), "Error: deberia estar el mensaje session expired");


        Response response =
                given()
                    .header("Content-type", "application/json")
                    //.header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(anAccount)
                .when()
                    .post("/services/data/v51.0/sobjects/Account")
                .then()
                    .log().all().assertThat().statusCode(401).extract().response();

        JsonPath js = response.jsonPath();
        String msg = js.get("[0].message");
        String errorCode = js.get("[0].errorCode");

        System.out.println("---> " + msg);
        System.out.println("---> " + errorCode);

        Assert.assertEquals(msg, "Session expired or invalid", "Error: se esperaba otro mensaje");
        Assert.assertEquals(errorCode, "INVALID_SESSION_ID", "Error: se esperaba otro session id");
    }

    /*
     @Esteban
     02-11-2021
     Se realiza la prueba de un POST sin body
     Se espera mensajes de error
    */
    @Test
    public void emptyBodyErrorTest() {

        String newAccountResponse =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    //.body(anAccount)
                .when()
                    .post("/services/data/v51.0/sobjects/Account")
                .then()
                    .log().all().assertThat().statusCode(400).extract().asString();

        System.out.println("--> " + newAccountResponse);

        Assert.assertTrue(newAccountResponse.contains("JSON_PARSER_ERROR"), "Error: deberia estar el error code JSON_PARSER_ERROR");
        Assert.assertTrue(newAccountResponse.contains("The HTTP entity body is required, but this request has no entity body."), "Error: deberia estar el mensaje no body");
    }
}
