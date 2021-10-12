package Practico4;

import Practico4.entidades.Estudiante;
import Practico4.entidades.TarjetaCredito;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

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

//        JsonPath jsonPath = new JsonPath(response);
//        String firstName = jsonPath.get("firstName");
//        String lastName = jsonPath.get("lastName");
        String firstName = ReUsableMethods.rawToJson(response, "firstName");
        String lastName = ReUsableMethods.rawToJson(response, "lastName");
        System.out.println(firstName);
        System.out.println(lastName);
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
        System.out.println("====> " + instanceUrl);
        System.out.println("====> " + id);
        System.out.println("====> " + signature);

        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("access_token", accessToken);
        mapa.put("instance_url", instanceUrl);
        mapa.put("id", id);
        mapa.put("signature", signature);

        System.out.println("access_token: " + accessToken);
        System.out.println("instance_url: " + instanceUrl);
    }

    @Test
    public void cursosTest(){

        String responseCursos = "{\n" + "\"dashboard\": {\n" + "\n" + "\"purchaseAmount\": 910,\n" + "\n" + "\"website\": \"rahulshettyacademy.com\"\n" + "\n" + "},\n" + "\n" + "\"courses\": [\n" + "\n" + "{\n" + "\n" + "\"title\": \"Selenium Python\",\n" + "\n" + "\"price\": 50,\n" + "\n" + "\"copies\": 6\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"Cypress\",\n" + "\n" + "\"price\": 40,\n" + "\n" + "\"copies\": 4\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"RPA\",\n" + "\n" + "\"price\": 45,\n" + "\n" + "\"copies\": 10\n" + "\n" + "}\n" + "\n" + "]\n" + "\n" + "}";

        JsonPath jsonPathCursos = new JsonPath(responseCursos);
        List<LinkedHashMap> cursos = jsonPathCursos.get("courses");
        LinkedHashMap datos = jsonPathCursos.get("dashboard");

        int purchaseAmount = Integer.parseInt(datos.get("purchaseAmount").toString());
        System.out.println(datos);
        System.out.println(datos.getClass());
        System.out.println(purchaseAmount);
        System.out.println(cursos.get(0).getClass());
        System.out.println(cursos.get(0));

        int cantidad_cursos = cursos.size();
        int costo_total = 0;

        for(int i = 0; i < cantidad_cursos; i++){
            LinkedHashMap unCurso = cursos.get(i);
            System.out.println("Titulo: " + unCurso.get("title") + " | Precio: " + unCurso.get("price"));
            costo_total += Integer.parseInt(unCurso.get("price").toString()) * Integer.parseInt(unCurso.get("copies").toString());
        }

        Assert.assertEquals(costo_total, purchaseAmount, "El costo calculado no coincide con el de los datos de compra");
        System.out.println("Cantidad de Cursos: "+ cantidad_cursos + "| Costo total es: " + costo_total);
    }

    @Test
    public void librosTest() {
        String responseCursos = "{\n" + "\"dashboard\": {\n" + "\n" + "\"purchaseAmount\": 910,\n" + "\n" + "\"website\": \"rahulshettyacademy.com\"\n" + "\n" + "},\n" + "\n" + "\"courses\": [\n" + "\n" + "{\n" + "\n" + "\"title\": \"Selenium Python\",\n" + "\n" + "\"price\": 50,\n" + "\n" + "\"copies\": 6\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"Cypress\",\n" + "\n" + "\"price\": 40,\n" + "\n" + "\"copies\": 4\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"RPA\",\n" + "\n" + "\"price\": 45,\n" + "\n" + "\"copies\": 10\n" + "\n" + "}\n" + "\n" + "]\n" + "\n" + "}";

        JsonPath extractor = new JsonPath(responseCursos);

        int cantidadCursos = extractor.getInt("courses.size()");
        System.out.println(cantidadCursos);

        int totalCursos = extractor.getInt("dashboard.purchaseAmount");
        System.out.println(totalCursos);

        String titleFirstCourse = extractor.get("courses[0].title");
        System.out.println(titleFirstCourse);

        System.out.println("**** TODOS LOS CURSOS *****");
        int total = 0;

        for (int i = 0; i < cantidadCursos; i++) {
            String titulo = extractor.get("courses[" + i + "].title");
            int precio = extractor.get("courses[" + i + "].price");
            int copias = extractor.get("courses[" + i + "].copies");
            System.out.println(titulo + " Precio: $" + precio);
            total += precio * copias;
        }

        System.out.println("El precio final es de " + total);
        Assert.assertEquals(total, totalCursos, "El costo calculado no coincide con el de los datos de compra");

        //imprimir la cantidad de copias del libro RPA
        for (int i = 0; i < cantidadCursos; i++) {
            String titulo = extractor.get("courses[" + i + "].title");
            if (titulo.equalsIgnoreCase("RPA")){
                int copias = extractor.get("courses[" + i + "].copies");
                System.out.println("La cantidad de copias es " + copias);
                break;
            }
        }
    }
}
