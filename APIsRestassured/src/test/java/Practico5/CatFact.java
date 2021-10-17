package Practico5;

import io.restassured.RestAssured;
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
        List<String> response =
                given().header("Content-Type", "application/json")
                .when().get("facts")
                .then().assertThat().statusCode(503)
                .extract().path("text");

        for (String text : response){
            System.out.println(text);
        }
    }
}
