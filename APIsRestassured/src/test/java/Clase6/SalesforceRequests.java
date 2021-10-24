package Clase6;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SalesforceRequests {
//https://seleniumacademy2-dev-ed.my.salesforce.com/services/data/v51.0/sobjects/Contact/0035f000006RWIzAAO
    @Test
    public void getTokenTest(){

        //obtener la autorizacion para trabajar en la instancia de Salesforce...

//        Pedir permiso y token
//        Crear contacto y responde con id de contacto creado y buscar en SF

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

        String accessToken = js.get("access_token");
        String instanceUrl = js.get("instance_url");
        String instanceId = js.get("id");

        System.out.println("Access token: --> " + accessToken);
        System.out.println("Instance Url: --> " + instanceUrl);
        System.out.println("Instance id: --> " + instanceId);

        Contact contacto1 = new Contact("Marquez Gonzalez");


        //crear un nuevo contacto....
        RestAssured.baseURI = instanceUrl;

        String nuevoContacto = given()
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
