package Practico5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolder {

    private static String requestBody = "{\n" +
            " \"title\": \"foo\",\n" +
            " \"body\": \"baz\",\n" +
            " \"userId\": \"1\",\n" +
            " \"id\": \"1\" \n}";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getCommentsPostTest(){
        Response response =
                given()
                        .contentType(ContentType.JSON).param("postId", "2")
                .when()
                        .get("/comments")
                .then()
                        .log().all().assertThat().statusCode(200).extract().response();

        System.out.println(response.getStatusLine());
        System.out.println("Body : " + response.getBody().asString());
        System.out.println("Status code: " + response.getStatusCode());
        Assert.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));
    }

    @Test
    public void putPostTest(){
        Response response =
                given()
                    .contentType(ContentType.JSON)
                .and()
                    .body(requestBody)
                .when()
                    .put("/posts/4")
                .then()
                    .log().all().assertThat().statusCode(200).extract().response();


        System.out.println(response.body().asString());
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("foo", response.jsonPath().getString("title"));
        Assert.assertEquals("baz", response.jsonPath().getString("body"));
        Assert.assertEquals("1", response.jsonPath().getString("userId"));
        Assert.assertEquals("4", response.jsonPath().getString("id"));
    }

    @Test
    public void deleteRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/posts/1")
                .then()
                .extract().response();
        System.out.println(response.body().asString());
        Assert.assertEquals(200, response.statusCode());
    }



}
