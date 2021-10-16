package Clase5;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import jdk.jfr.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostSimpleTest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    String datos = "{\n" +
            "    \"title\" : \"test1\",\n" +
            "    \"body\" : \"hola\",\n" +
            "    \"userId\" : \"1\"\n" +
            "}";

    @Test
    public void postRequest(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and().body(datos)
                .when()
                        .post("/posts")
                .then().log().all()
                        .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201, "Error: el status code deberia ser 201");
        Assert.assertEquals(response.jsonPath().get("title"), "test1", "Error: el titulo es incorrecto");
        Assert.assertEquals(response.jsonPath().get("body"), "hola", "Error: el body es incorrecto");
        Assert.assertEquals(response.jsonPath().get("userId"), "1", "Error: el body es incorrecto");
        Assert.assertNotEquals(response.jsonPath().get("id"), "0", "Error: el id no deberia ser 0");

        JsonPath jsonPath = response.jsonPath();

        String titulo = jsonPath.get("title");
        String body = jsonPath.get("body");
        String userId = jsonPath.get("userId");
        int id = jsonPath.get("id");

        System.out.println("El titulo es " + titulo + " y el body es " + body + " | userId = " + userId + " | id = " + id);
    }

    String userInfo = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}";

    @Test
    public void registrationTest(){
        RestAssured.baseURI = "https://reqres.in/api/register";

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and().body(userInfo)
                .when()
                        .post("")
                .then().assertThat().log().all().statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int identificador = jsonPath.get("id");
        String token = jsonPath.get("token");

        System.out.println("identificador: " + identificador);
        Assert.assertFalse(token.isEmpty(), "Error: el token no deberia ser vacio");

    }





}
