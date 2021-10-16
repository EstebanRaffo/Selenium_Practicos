package Clase5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAndDeleteSimpleTest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getRequestTest(){
        Response response =
            given().contentType(ContentType.JSON)
                .param("postId","2")
            .when()
                .get("/comments")
            .then().log().all()
                .extract().response();

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body : " + response.getBody().asString());
    }

    @Test
    public void deleteRequestTest(){
        Response response =
                given().log().all().contentType(ContentType.JSON)
                    .param("postId","2")
                .when()
                    .delete("/posts/1")
                .then().log().all()
                    .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Error: la request ha fallado");
    }








}
