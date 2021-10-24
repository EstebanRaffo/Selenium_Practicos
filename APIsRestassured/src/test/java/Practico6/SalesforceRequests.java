package Practico6;

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

        String respuesta = given()
                    //.header("Content-type", "application/json")
                    .queryParam("grant_type", "password")
                    .queryParam("client_id", "3MVG9p1Q1BCe9GmBFxFv86hAuBdOHjVb8SJIhkKHfVZUqdeSKAWcYNzzoLMN3v6QC0yp61kUYE1fCmekeCX.O")
                    .queryParam("client_secret", "4381C0ECBB5F8BA367E97A1B75874BCBBB3EAE68ECD34D2523740B9EC56B68C8")
                    .queryParam("username", "e.fraffo@gmail.com")
                    .queryParam("password", "holamundo123sJkHjd5X8zNbjH46JyWllDnD")
                .when()
                    .post("token")
                .then().assertThat().statusCode(200)
                    .extract().asString();

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        String accessToken = js.get("access_token");

        System.out.println("Access token: --> " + accessToken);
    }
}
