package Practico6;

import Clase6.Contact;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SalesforceRequests {

    @Test
    public void getTokenTest() {

        //obtener la autorizacion para trabajar en la instancia de Salesforce...

//        Pedir permiso y token
//        Crear contacto y responde con id de contacto creado y buscar en SF

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

        String accessToken = js.get("access_token");
        String instanceUrl = js.get("instance_url");
        String instanceId = js.get("id");

        System.out.println("Access token: --> " + accessToken);
        System.out.println("Instance Url: --> " + instanceUrl);
        System.out.println("Instance id: --> " + instanceId);

        //crear un nuevo contacto....
        Contact contacto1 = new Contact("Pedro Gomez");

        RestAssured.baseURI = instanceUrl;

        String nuevoContacto =
                given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .body(contacto1)
                .when()
                    .post("/services/data/v51.0/sobjects/Contact")
                .then()
                        .assertThat().statusCode(201).extract().asString();

        System.out.println(nuevoContacto);

        js = new JsonPath(nuevoContacto);

        String contactId = js.get("id");

        //obtener la informacion del contacto previamente creado....
        String contactInfo =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                .when()
//                    El endpoint sale de workbench
                    .get("services/data/v51.0/sobjects/Contact/" + contactId )
                .then()
                    .assertThat().statusCode(200).extract().asString();

        System.out.println("***********  Contact Information ***************");
        System.out.println("---> " + contactInfo);
    }
}
