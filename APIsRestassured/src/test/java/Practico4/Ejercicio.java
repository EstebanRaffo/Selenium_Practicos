package Practico4;

import Practico4.entidades.Estudiante;
import Practico4.entidades.TarjetaCredito;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio {

    @Test
    public void crearJsonEstudiantesTest(){
        Estudiante estudiante1 = new Estudiante("Juan", "Lopez", 123123, "Informatica");
        Estudiante estudiante2 = new Estudiante("Maria", "Rodriguez", 222222, "Administracion");
        Estudiante estudiante3 = new Estudiante("Juan", "Cowen", 123124, "Finanzas");

        List<Estudiante> listaEstudiantes = new ArrayList<>();
        listaEstudiantes.add(estudiante1);
        listaEstudiantes.add(estudiante2);
        listaEstudiantes.add(estudiante3);

        String jsonEstudiantes = new Gson().toJson(listaEstudiantes);
        System.out.println("jsonEstudiantes: " + jsonEstudiantes);
        jsonEstudiantes = "{\"estudiantes\": " + jsonEstudiantes +"}";
        System.out.println("jsonEstudiantes: " + jsonEstudiantes);

        JsonPath jsEstudiantes = new JsonPath(jsonEstudiantes);
        int cantidadEstudiantes = jsEstudiantes.getInt("estudiantes.size()");

        for(int i = 0; i < cantidadEstudiantes; i++){
            String nombre = jsEstudiantes.get("estudiantes["+i+"].nombre");
            String apellido = jsEstudiantes.get("estudiantes["+i+"].apellido");
            int dni = jsEstudiantes.get("estudiantes["+i+"].dni");
            String curso = jsEstudiantes.get("estudiantes["+i+"].curso");

            System.out.println("Alumno: " + nombre + " " + apellido + " " + "| DNI: " + dni + "| Carrera: " + curso);
        }
    }

    @Test
    public void crearJsonTarjetasTest(){
        TarjetaCredito tarjeta1 = new TarjetaCredito("VISA", 1234, 5678);
        TarjetaCredito tarjeta2 = new TarjetaCredito("VISA", 4321, 8765);
        TarjetaCredito tarjeta3 = new TarjetaCredito("VISA", 9876, 6543);

        List<TarjetaCredito> listaTarjetas = new ArrayList<>();
        listaTarjetas.add(tarjeta1);
        listaTarjetas.add(tarjeta2);
        listaTarjetas.add(tarjeta3);

        String jsonTarjetas = new Gson().toJson(listaTarjetas);
        jsonTarjetas = "{\"tarjetas\": " + jsonTarjetas +"}";
        System.out.println("jsonTarjetas: " + jsonTarjetas);
    }

    @Test
    public void jsonTest(){
        String response = "{\n" +
                "   \"firstName\": \"Duke\",\n" +
                "   \"lastName\": \"Java\",\n" +
                "   \"age\": 18,\n" +
                "   \"streetAddress\": \"100 Internet Dr\",\n" +
                "   \"city\": \"JavaTown\",\n" +
                "   \"state\": \"JA\",\n" +
                "   \"postalCode\": \"12345\",\n" +
                "   \"phoneNumbers\": [\n" +
                "      { \"Mobile\": \"111-111-1111\" },\n" +
                "      { \"Home\": \"222-222-2222\" }\n" +
                "   ]\n" +
                "}";

        JsonPath jsonPath = new JsonPath(response);
        String firstName = jsonPath.get("firstName");
        String lastName = jsonPath.get("lastName");
        System.out.println(firstName);
        System.out.println(lastName);
    }

    public static String rawToJason(String response, String param){
        JsonPath jsonPath = new JsonPath(response);
        String parameter = jsonPath.get(param);
        return parameter;
    }

    @Test
    public static void jsonCatsTest(){
        String cats = "    {\n" +
                "        \"status\": {\n" +
                "           \"verified\": true,\n" +
                "           \"sentCount\": 1,\n" +
                "            \"feedback\": \"\"\n" +
                "        },\n" +
                "        \"type\": \"cat\",\n" +
                "        \"deleted\": false,\n" +
                "        \"_id\": \"5887e1d85c873e0011036889\",\n" +
                "        \"user\": \"5a9ac18c7478810ea6c06381\",\n" +
                "        \"text\": \"Cats make about 100 different sounds. Dogs make only about 10.\",\n" +
                "        \"__v\": 0,\n" +
                "        \"source\": \"user\",\n" +
                "        \"updatedAt\": \"2020-09-03T16:39:39.578Z\",\n" +
                "        \"createdAt\": \"2018-01-15T21:20:00.003Z\",\n" +
                "        \"used\": true\n" +
                "    }";

        JsonPath jsonPath = new JsonPath(cats);
        System.out.println("status: " + jsonPath.get("status"));
    }

    @Test
    public void getResponseData(){

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

        System.out.println("====> " + accessToken);
        System.out.println("====> " +instanceUrl);
        System.out.println("====> " +id);
        System.out.println("====> " +signature);
    }
}
