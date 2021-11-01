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
                        .queryParam("client_id", "3MVG9p1Q1BCe9GmCSj33tBJjfBszMSaJDvJDfwwj2VeMpQy4rRnWS_IXrAPj41qd.0V3e50FHZMBySnXCLnzC")
                        .queryParam("client_secret", "E63928F2EF75E18F2562AE5CEA50F56897C29092C6D6E3AF9718E16218FE4FC8")
                        .queryParam("username", "seleniumcurso+123@gmail.com")
                        .queryParam("password", "holamundo123FxZ0KNxCgPgIQ0TDPYW7HmkmF")
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


    @Test(dataProvider = "accounts")
    public void loadAccountsTest(String name, String description) {

        System.out.println("---> " + name + "  " + description);

        Account anAccount = new Account(name, description);

        String newAccountResponse =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .body(anAccount)
                        .when()
                        .post("/services/data/v51.0/sobjects/Account")
                        .then()
                        .assertThat().statusCode(201).extract().asString();

        System.out.println("Respuesta: " + newAccountResponse);
    }


    /*
    @Emiliano
    30-10-2021
    Se realiza la prueba de un POST sin access token
    Se espera mensajes de error
     */
    @Test
    public void emptyTokenErrorTest() {

        Account anAccount = new Account("Juan", "Mi cuenta");

        String newAccountResponse =
                given()
                        .header("Content-type", "application/json")
                        //.header("Authorization", "Bearer " + ACCESS_TOKEN)
                        .body(anAccount)
                        .when()
                        .post("/services/data/v51.0/sobjects/Account")
                        .then()
                        .assertThat().statusCode(401).extract().asString();

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
                        .assertThat().statusCode(401).extract().response();

        JsonPath js = response.jsonPath();
        String msg = js.get("[0].message");
        String errorCode = js.get("[0].errorCode");

        System.out.println("---> " + msg);
        System.out.println("---> " + errorCode);

        Assert.assertEquals(msg, "Session expired or invalid", "Error: se esperaba otro mensaje");
        Assert.assertEquals(errorCode, "INVALID_SESSION_ID", "Error: se esperaba otro session id");

    }

    /*
 @Emiliano
 30-10-2021
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
                        .assertThat().statusCode(400).extract().asString();

        System.out.println("--> " + newAccountResponse);

        Assert.assertTrue(newAccountResponse.contains("JSON_PARSER_ERROR"), "Error: deberia estar el error code JSON_PARSER_ERROR");
        Assert.assertTrue(newAccountResponse.contains("The HTTP entity body is required, but this request has no entity body."), "Error: deberia estar el mensaje no body");
    }

}
