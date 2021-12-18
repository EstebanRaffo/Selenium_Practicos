package Practico14.test;

import Practico14.utilities.Constants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthenticationHelper {
    public static String ACCESS_TOKEN;
    public static String INSTANCE_URL;

    private RequestSpecification requestSpecificationForUAT = new RequestSpecBuilder()
            .addParam("grant_type", "password")
            .addParam("client_id", Constants.UAT_CLIENT_ID)
            .addParam("client_secret", Constants.UAT_CLIENT_SECRET)
            .addParam("username", Constants.UAT_USERNAME)
            .addParam("password", Constants.UAT_PASSWORD)
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();

    private RequestSpecification requestSpecificationForDEV = new RequestSpecBuilder()
            .addParam("grant_type", "password")
            .addParam("client_id", Constants.DEV_CLIENT_ID)
            .addParam("client_secret", Constants.DEV_CLIENT_SECRET)
            .addParam("username", Constants.DEV_USERNAME)
            .addParam("password", Constants.DEV_PASSWORD)
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();

    @Given("I got the access token and instance url")
    public void i_got_the_access_token_and_instance_url() {

        String respuesta = given()
                .spec(requestSpecificationForDEV)
                .when()
                .post("token")
                .then().assertThat().statusCode(200)
                .extract().asString();

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");

        RestAssured.baseURI = INSTANCE_URL;
    }
}
