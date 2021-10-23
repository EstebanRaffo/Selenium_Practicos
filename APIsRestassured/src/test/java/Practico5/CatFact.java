package Practico5;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CatFact {


    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://cat-fact.herokuapp.com";
    }

    @Test
    public void getTextsTest(){
        List<String> textos =
                given().header("Content-Type", "application/json")
                .when().get("facts")
                .then().log().all().assertThat().statusCode(200)
//                        .extract().asString()
                .extract().path("text");

        // System.out.println(response);

        for (String text : textos){
            System.out.println(text);
        }


        List<String> status = given().header("Content-Type", "application/json")
                .when().get("facts")
                .then().assertThat().statusCode(200)
                .extract().path("source");

        System.out.println("Source");

        for (String source : status){
            System.out.println(source);
        }
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

    @DataProvider(name = "ciudades")
    public Object[][] datosDePersonas(){
        return new Object[][]{
                {"Montevideo"},
                {"Cordoba"}
        };
    }

    @Test(dataProvider = "ciudades")
    public static void weatherInJsonResponseTest(String unaCiudad){
        RestAssured.baseURI = "https://demoqa.com";
        String endpoint = "/utilities/weather/city/" + unaCiudad;

        Response response = doGetRequest(endpoint);

        JsonPath js = response.jsonPath();

        String city = js.getString("City");
        String temperature = js.getString("Temperature");
        String humidity = js.getString("Humidity");

        HashMap<String, String> weatherInfoMap = new HashMap<>();
        weatherInfoMap.put("Ciudad", city);
        weatherInfoMap.put("Temperatura", temperature);
        weatherInfoMap.put("Humedad", humidity);

        System.out.println("City: " + weatherInfoMap.get("Ciudad") + " | Temperature: " + weatherInfoMap.get("Temperatura") + " | humidity: " + weatherInfoMap.get("Humedad"));

        Assert.assertEquals(city, unaCiudad, "No se obtuvo "+ unaCiudad +"\n"+ "La ciudad obtenida fue "+ city);
        Assert.assertTrue(temperature.contains("Degree celsius"), "La temperatura no está en Degree Celsius");
        Assert.assertTrue(humidity.contains("Percent"), "La humedad no está en %");
    }
}
