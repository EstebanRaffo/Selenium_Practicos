package Practico7;

import Clase6.Contact;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ContactTest {

    private static String ACCESS_TOKEN = "";
    private static String INSTANCE_URL = "";

    @BeforeTest
    public void obtainAuthorizationFromSalesforce(){
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

    @Test
    public void contactFlowTest(){

        Contact contacto1 = new Contact("Pedro Gomez");

        //crear un nuevo contacto....

        String nuevoContacto =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .body(contacto1)
                .when()
                    .post("/services/data/v51.0/sobjects/Contact")
                .then()
                    .log().all().assertThat().statusCode(201).extract().asString();

        System.out.println(nuevoContacto);

        JsonPath js = new JsonPath(nuevoContacto);

        String contactId = js.get("id");

        //obtener la informacion del contacto previamente creado....
        String contactInfo =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                    .get("services/data/v51.0/sobjects/Contact/" + contactId )
                .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("***********  Contact Information ***************");
        System.out.println("---> " + contactInfo);
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
                    .delete("services/data/v51.0/sobjects/Contact/" + "0035f000004cfS6AAI" )
                .then()
                    .assertThat().statusCode(204).extract().asString();

        System.out.println("***********  Contact Information ***************");
        System.out.println("---> " + responseInfo);
    }
}

