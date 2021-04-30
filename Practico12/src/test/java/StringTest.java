import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.util.*;

public class StringTest {

    public static String first_name = "Pedro";
    public static String second_name = "Juan";
    public static String lastname = "Perez";
    public static String telephone_number = "116688-0926";
    public static String anyString = "ciertos caracteres";
    public static String stringEmails = "e.fraffo@gmail.com; e.fraffo@hotmail.com; e.fraffo@argentina.com";

    public static void main(String args[]){

        String name = first_name.concat(second_name).concat(lastname);
        System.out.println(name);

        String[] numbers = telephone_number.split("-");
        System.out.println(numbers[0]);
        System.out.println(numbers[1]);

        if(anyString.startsWith("c")){
            System.out.println("anyString comienza con c");
        }

        HashMap<String, String> capitalCities = new HashMap<String, String>();

        capitalCities.put("Argentina", "Buenos Aires");
        capitalCities.put("Uruguay", "Montevideo");
        capitalCities.put("Chile", "Santiago");
        capitalCities.put("Noruega", "Oslo");
        capitalCities.put("Finlandia", "Helsinki");
        System.out.println(capitalCities);

        for(Map.Entry pais : capitalCities.entrySet()){
            System.out.println(pais.getKey() + ": " + pais.getValue());
        }

        HashMap<Integer, String> cities = new HashMap<Integer, String>();

        cities.put(0, "Santiago");
        cities.put(1, "Buenos Aires");
        cities.put(2, "Montevideo");
        cities.put(3, "Oslo");

        for(int i = 0; i < cities.size(); i++){
            System.out.println("Ciudad: " + cities.get(i) + " ; posición -> " + i);
        }

        for(Map.Entry city : cities.entrySet()){
            System.out.println(city.getKey() + ": " + city.getValue());
        }

        cities.put(1, "Madrid");
        cities.remove(2);
        System.out.println(cities);

        System.out.println("Validar emails");
        List<String> emails = new ArrayList<>();
        emails.add("e.fraffo@hotmail.com");
        emails.add("e.fraffo@gmail.com");
        emails.add("e.fraffo@argentina.com");

        validateEmails(emails);
        System.out.println("Posicion de @: " + positionOf("e.fraffo@gmail.com"));
        System.out.println("Caracteres reemplazados: " + replaceCharacters(",,,,,,;;;;;;;"));
        System.out.println("Extracto de e.fraffo@gmail.com desde pos 1 a 5: " + extractChars("e.fraffo@gmail.com", 1, 5));
        System.out.println("Convertir HOLA a minuscula: " + convertToLower("HOLA"));
        System.out.println("Convertir hola a mayuscula: " + convertToUpper("hola"));


        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(31123321, "Pedro Perez", "Dev"));
        empleados.add(new Empleado(32123321, "Juan Perez", "QA Analyst"));
        empleados.add(new Empleado(33312231, "Pepe", "AF"));

        HashMap<Integer, Empleado> empleados_list = new HashMap<Integer, Empleado>();

        for(int i = 0; i < empleados.size(); i++){
            Empleado unEmpleado = empleados.get(i);
            empleados_list.put(unEmpleado.getDni(), unEmpleado);
        }

        System.out.println("Empleado con DNI 32123321: " + empleados_list.get(32123321));
        System.out.println("Nombre del Empleado con DNI 32123321: " + empleados_list.get(32123321).getNombre());

        String [] arrayEmails = stringEmails.split(";");
        System.out.println("arrayEmails: ");
        for(int i = 0; i < arrayEmails.length; i++){
            System.out.println("arrayEmails[" + i + "] = " + arrayEmails[i]);
        }
    }

    @Test
    public static void validateEmails(List<String> emails){
        boolean emailsAreValid = false;
        for(String anEmail : emails){
            if(anEmail.endsWith(".com") && anEmail.contains("@")){
                emailsAreValid = true;
            }else{
                emailsAreValid = false;
            }
        }
        Assert.assertTrue(emailsAreValid);
    }

    @Test
    public static int positionOf(String anEmail){
        return anEmail.indexOf("@");
    }

    @Test
    public static String replaceCharacters(String anString){
        String newString;
        newString = anString.replace(",", "-");
        newString = newString.replace(";", "@");

        return newString;
    }

    @Test
    public static String extractChars(String anyString, int start_pos, int end_pos){
        String newString = anyString.substring(start_pos, end_pos);

        return newString;
    }

    @Test
    public static String convertToLower(String aString){
        String newString = aString.toLowerCase();

        return newString;
    }

    @Test
    public static String convertToUpper(String aString){
        String newString = aString.toUpperCase();

        return newString;
    }


}
