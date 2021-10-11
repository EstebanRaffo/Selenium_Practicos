package Clase4.Ejemplo1_Procesar_JSON_De_Respuesta;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EjerciciosJsons {

    @Test
    public void getReponseData(){
        String response = "{\n" +
                "  \"access_token\": \"00D1N000001Rznq!AQYAQGGqJYk6Jqt3g8oVNzy2m6QKXGN_utdsdli5xUiwW9O1LROsoQEDmTGI8PO4V17pPC2tJCjlW0b5txdsbOsSL8CG8rW5\",\n" +
                "  \"instance_url\": \"https://emilianognocchi-dev-ed.my.salesforce.com\",\n" +
                "  \"id\": \"https://login.salesforce.com/id/00D1N000001RznqUAC/0051N000005TNbMQAW\",\n" +
                "  \"token_type\": \"Bearer\",\n" +
                "  \"issued_at\": \"1624384798213\",\n" +
                "  \"signature\": \"BSowPjEKEdR55Z8EX/7ym3TDSu/g8EC8SekYP7xBwYs=\"\n" +
                "}";

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.get("access_token");
        String instanceUrl = jsonPath.get("instance_url");
        String id = jsonPath.get("id");
        String signature = jsonPath.get("signature");

        System.out.println("El accessToken obtenido es " + accessToken);
        System.out.println("La URL de instancia es " + instanceUrl);
        System.out.println("El id de sesion es " + id);
        System.out.println("La firma de la sesion es " + signature);

        Assert.assertTrue(instanceUrl.endsWith("my.salesforce.com"), "Error: se esperaba otra url instancia");
        Assert.assertTrue(id.startsWith("https://login.salesforce.com"), "Error: el id de sesion no es el esperado");

        HashMap<String, String> mapaCrendeciales = new HashMap<>();
        //  < accessToken,  00D1N000001Rznq>
        //  < instanceUrl,   https://emilianognocchi-dev-ed.my.salesforce.com>
        mapaCrendeciales.put("token", accessToken);
        mapaCrendeciales.put("instance", instanceUrl);
        mapaCrendeciales.put("id", id);
        mapaCrendeciales.put("signature", signature);

        System.out.println("En el mapa la URL de instancia es " + mapaCrendeciales.get("instance"));
        System.out.println("En el mapa la token es " + mapaCrendeciales.get("token"));

    }

}