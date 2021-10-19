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
                .when().get(endpoint)
                .then().contentType(ContentType.JSON).log().all().extract().response();
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
    public static void weatherInJsonResponseTest(){
        RestAssured.baseURI = "https://demoqa.com";
        String endpoint = "/utilities/weather/city/Montevideo";

        Response response = doGetRequest(endpoint);

        JsonPath js = response.jsonPath();

        String city = js.getString("City");
        String temperature = js.getString("Temperature");
        String humidity = js.getString("Humidity");

        System.out.println("City: " + city + " | Temperature: " + temperature + " | humidity: " + humidity);

        Assert.assertEquals(city, "Montevideo", "No se obtuvo Montevideo");
        Assert.assertTrue(temperature.contains("Degree celsius"), "La temperatura no está en Degree Celsius");
        Assert.assertTrue(humidity.contains("Percent"), "La humedad no está en %");
    }
}
