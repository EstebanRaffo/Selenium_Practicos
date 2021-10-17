package Practico5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Reqres {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getAnUserTest(){
        Response response =
                given().contentType(ContentType.JSON)
                        .when().get("/api/users/2")
                        .then().log().all().assertThat().statusCode(200).extract().response();

        System.out.println("Body : " + response.getBody().asString());
    }

    @Test
    public void getUserNotFoundTest(){
        Response response =
                given().contentType(ContentType.JSON)
                        .when().get("/api/users/23")
                        .then().log().all().assertThat().statusCode(404).extract().response();

        System.out.println("Body : " + response.getBody().asString());
    }

    @Test
    public void postUserTest(){

        String userInfo = "{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                            "}";

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and().body(userInfo)
                .when()
                        .post("/api/users")
                .then().assertThat().log().all().statusCode(201)
                        .extract().response();

        JsonPath js = response.jsonPath();

        String name = js.get("name");
        String job = js.get("job");
        String id = js.get("id");
        String createdAt = js.get("createdAt");

        System.out.println("id: " + id +"\n"+ "Fecha creación: " + createdAt +"\n"+ "Name: " + name +"\n"+ "Job: " + job);
    }

    @Test
    public void putUserTest(){
        String userInfo = "{\n" +
                            "    \"name\": \"morpheus\",\n" +
                            "    \"job\": \"zion resident\"\n" +
                            "}";

        Response response =
                given()
                    .header("Content-type", "application/json")
                    .and().body(userInfo)
                .when()
                    .put("/api/users/2")
                .then().assertThat().log().all().statusCode(200)
                    .extract().response();

        JsonPath js = response.jsonPath();

        String name = js.get("name");
        String job = js.get("job");
        String updatedAt = js.get("updatedAt");

        System.out.println("Fecha actualización: " + updatedAt +"\n"+ "Name: " + name +"\n"+ "Job: " + job);
    }
}
