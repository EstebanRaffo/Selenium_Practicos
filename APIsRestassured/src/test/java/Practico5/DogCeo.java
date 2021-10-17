package Practico5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DogCeo {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://dog.ceo";
    }

    @Test
    public void getRandomImageTest(){
        Response response =
                given().contentType(ContentType.JSON)
                .when().get("/api/breeds/image/random")
                .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "status code distinto de 200");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Body : " + response.getBody().asString());
    }

    @Test
    public void getImagesTest(){
        Response response =
                given().contentType(ContentType.JSON)
                .when().get("/api/breed/hound/images")
                .then().log().all().assertThat().statusCode(200).extract().response();

        JsonPath js = response.jsonPath();

        int cantidad_images = js.getInt("message.size()");
        System.out.println("Se obtuvieron "+ cantidad_images + " imagenes");

        boolean termina_en_jpg = false;
        int i;
        for(i = 0; i < cantidad_images; i++){
            System.out.println("Iteracion: " + i);
            String image_url = js.get("message["+i+"]");
            termina_en_jpg = image_url.endsWith(".jpg");
            if (!termina_en_jpg){ break; }
        }

        Assert.assertEquals(termina_en_jpg, true, "La imagen "+ i +" no termina en .jpg");
    }


}
