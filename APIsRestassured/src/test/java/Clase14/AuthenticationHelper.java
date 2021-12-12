package Clase14;

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
            .addParam("client_id", "3MVG9cHH2bfKACZYj3S3as4gJ9.9.zVTytlu8Q61HwPUN.NtPEwJqbFOp4pEy5gm6fsezrxm_WMW9YkQAcPW3")
            .addParam("client_secret", "696513F287215272F6EC9E2398C94873CF596E8CF92CC9F9F096ED4FE1C8A37F")
            .addParam("username", "seleniumcurso@gmail.com")
            .addParam("password", "holahola123PkC9nQP5ZkNgQahPfnQgWWHc")
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();

    private RequestSpecification requestSpecificationForDEV = new RequestSpecBuilder()
            .addParam("grant_type", "password")
            .addParam("client_id", "3MVG9p1Q1BCe9GmCSj33tBJjfBszMSaJDvJDfwwj2VeMpQy4rRnWS_IXrAPj41qd.0V3e50FHZMBySnXCLnzC")
            .addParam("client_secret", "E63928F2EF75E18F2562AE5CEA50F56897C29092C6D6E3AF9718E16218FE4FC8")
            .addParam("username", "seleniumcurso+123@gmail.com")
            .addParam("password", "holamundo123FxZ0KNxCgPgIQ0TDPYW7HmkmF")
            .setBaseUri("https://login.salesforce.com")
            .setBasePath("/services/oauth2/")
            .build();


//    @Given("I got the access token and instance url")
//    public void i_got_the_access_token_and_instance_url() {
//
//        String respuesta = given()
//                .spec(requestSpecificationForDEV)
//                .when()
//                .post("token")
//                .then().assertThat().statusCode(200)
//                .extract().asString();
//
//        System.out.println(respuesta);
//
//        JsonPath js = new JsonPath(respuesta);
//
//        ACCESS_TOKEN = js.get("access_token");
//        INSTANCE_URL = js.get("instance_url");
//
//        RestAssured.baseURI = INSTANCE_URL;
//
//    }

}
