package Clase11.Base;

import Clase11.Utilities.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class BaseClass {

    protected static String ACCESS_TOKEN = "";
    protected static String INSTANCE_URL = "";

    @BeforeTest
    @Parameters({"org"})
    public void obtainAuthorizationFromSalesforce(@Optional("dev") String org) {
        System.out.println("---> " + org);
        if (org.equals("dev")){
            getAuthorization(Constants.DEV_CLIENT_ID, Constants.DEV_CLIENT_SECRET, Constants.DEV_USERNAME, Constants.DEV_PASSWORD, Constants.DEV_SECURITY_TOKEN);
        } else if (org.equals("uat")){
            System.out.println("UAT crendentials");
            getAuthorization(Constants.UAT_CLIENT_ID, Constants.UAT_CLIENT_SECRET, Constants.UAT_USERNAME, Constants.UAT_PASSWORD, Constants.UAT_SECURITY_TOKEN);
        } else if (org.equals("prod")){
            System.out.println("Prod crendentials");
            getAuthorization(Constants.PROD_CLIENT_ID, Constants.PROD_CLIENT_SECRET, Constants.PROD_USERNAME, Constants.PROD_PASSWORD, Constants.PROD_SECURITY_TOKEN);
        }
    }

    private void getAuthorization(String clientId, String clientSecret, String username, String password, String securityToken){
        RestAssured.baseURI = "https://login.salesforce.com/services/oauth2/";

        String respuesta =
            given()
                .queryParam("grant_type", "password")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("username", username)
                .queryParam("password", password+securityToken)
            .when()
                .post("token")
            .then()
                .assertThat().statusCode(200).extract().asString();

        System.out.println(respuesta);

        JsonPath js = new JsonPath(respuesta);

        ACCESS_TOKEN = js.get("access_token");
        INSTANCE_URL = js.get("instance_url");

        RestAssured.baseURI = INSTANCE_URL;
        System.out.println("Access token: --> " + ACCESS_TOKEN);
        System.out.println("Instance Url: --> " + INSTANCE_URL);
    }
}