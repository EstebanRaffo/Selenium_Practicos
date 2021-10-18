package Practico5;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CatFact {


    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://cat-fact.herokuapp.com";
    }

    @Test
    public void getTextsTest(){
//        List<String> response =
//                given().header("Content-Type", "application/json")
//                .when().get("facts")
//                .then().assertThat().statusCode(503)
//                .extract().path("text");
//
//        for (String text : response){
//            System.out.println(text);
//        }
    }


    public static Response doGetRequest(String endpoint) {
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .when().get(endpoint).
                then().contentType(ContentType.JSON).log().all().extract().response();
    }

    @Test
    public static void getCurrencyListTest(){
        RestAssured.baseURI = "https://api.n.exchange";
        String endpoint = "/en/api/v1/currency/";

        Response response = doGetRequest(endpoint);
        System.out.println(response.body().asString());
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public static void getJsonUsersTest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        String endpoint = "/users";

        Response response = doGetRequest(endpoint);
        Assert.assertEquals(200, response.statusCode());

        System.out.println(response.body().jsonPath());
        System.out.println(response.body().getClass());

        String jsonResponse = new Gson().toJson(response);
        jsonResponse = "{\"users\": "+ jsonResponse +"}";
        JsonPath js = new JsonPath(jsonResponse);
        System.out.println("Se obtuvieron " + js.getInt("users.size()") + " usuarios");

    }
}
