package Practico4;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static String rawToJson(String response, String param){
        JsonPath jsonPath = new JsonPath(response);
        String parameter = jsonPath.get(param);
        return parameter;
    }
}
