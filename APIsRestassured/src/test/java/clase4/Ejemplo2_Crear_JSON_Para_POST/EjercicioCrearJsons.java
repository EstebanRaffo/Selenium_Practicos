package clase4.Ejemplo2_Crear_JSON_Para_POST;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class EjercicioCrearJsons {

    @Test
    public void crearLibrosJsonTest() {
        Libro libro1 = new Libro("J. Rodriguez", "El Salvador", 1300);
        Libro libro2 = new Libro("W. Roberts", "La busqueda", 1000);
        Libro libro3 = new Libro("M. Cowens", "La odisea", 500);
        Libro libro4 = new Libro();

        List<Libro> listaLibros = new ArrayList<>();
        listaLibros.add(libro1);
        listaLibros.add(libro2);
        listaLibros.add(libro3);
        listaLibros.add(libro4);

        // Gson ---> convertir un String a Json....
        // JsonPath ---> convertir un Json a un formato que podamos extraer los datos...
        String jsonLibros = new Gson().toJson(listaLibros);
        System.out.println("====> " + jsonLibros);

        // "libros": [{"autor":"J. rodridguez",.....}]
        jsonLibros = "{\"libros\":" + jsonLibros + "}";


        //*** SIMULO LA RESPUESTA DEL SERVIDOR: ES UNA LISTA DE JSONS....
        JsonPath js = new JsonPath(jsonLibros);
        int cantidadDeLibros = js.getInt("libros.size()");

        System.out.println("Cantidad de libros a enviar: " + cantidadDeLibros);

        //buscar que : el titulo el salvador se encuentre dentro de la respuesta
        //buscar que : al menos un libro sea de W. Roberts
        //validar que todos los libros sean de precio mayor o igual a $500

        int sumaPrecios = 0;

        //creo banderas booleanas
        boolean encontreTituloElSalvador = false;
        boolean encontreAutorWRoberts = false;
        boolean sonTodosMayoresA500 = true;

        for (int i = 0; i < cantidadDeLibros; i++){
            String autor = js.get("libros["+i+"].autor");
            if (autor.equals("W. Roberts") == true){
                encontreAutorWRoberts = true;
            }

            String titulo = js.get("libros["+i+"].titulo");
            if (titulo.equals("El Salvador") == true){
                encontreTituloElSalvador = true;
            }

            int precio = js.get("libros["+i+"].precio");
            if (precio < 500){
                sonTodosMayoresA500 = false;
            }

            System.out.println("El titulo del libro es " + titulo + " y su autor es " + autor +". Su precio es " + precio);
            sumaPrecios = sumaPrecios + precio;

        }

        System.out.println("El precio de los " + cantidadDeLibros + " libros es $" + sumaPrecios);

        Assert.assertTrue(encontreAutorWRoberts, "Error: No se encontró el autor W. Roberts");
        Assert.assertTrue(encontreTituloElSalvador, "Error: No se encontró el titulo El Salvador");
        Assert.assertTrue(sonTodosMayoresA500, "Error: hay libros con precio menor a $500!!");
    }

}