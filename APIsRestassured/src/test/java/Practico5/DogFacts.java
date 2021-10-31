package Practico5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DogFacts {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://dog-facts-api.herokuapp.com";
    }

    @Test
    public void getDogsFactsTest(){
        Response response =
                given().contentType(ContentType.JSON)
                        .when().get("/api/v1/resources/dogs/all")
                        .then().log().all().assertThat().statusCode(200).extract().response();

        System.out.println("Body : " + response.getBody());
        System.out.println("Body as String: " + response.getBody().asString());

    }
}
