package Practico11.strings;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Strings {
    //juan,perez,1990,Uruguay;ana,lopez,1999,Argentina,..................................

    public static void main(String args[]){
        System.out.println("***********");

        //replace("Hola que estas haciendo?");
        //joinString();
        splitByChar();
    }


    public static void replace(String texto){
        String replacedText = texto.replace('a','Q');
        System.out.println("Replaced : " + replacedText);

    }

    public static void emailStrings(){
        String email1 = "juan@gmail.com";
        String email2 = "ana@gmail.com";
        String email3 = "pedrogmail.com";

        validateEmail(email1);
        validateEmail(email2);
        validateEmail(email3);

        List<String> emails = new ArrayList<>();

        validateEmail(emails);

    }

    public static void joinString(){
        String primerNombre = "Juan";
        String segundoNombre = "Cruz";
        String primerApellido = "Rodriguez";
        String nombreCompleto = primerNombre.concat(segundoNombre).concat(primerApellido);
        System.out.println(nombreCompleto);
    }


    public static void splitByChar(){
        String data = "Juan, Cruz, 22, test@test.com; Maria, Rodriguez, 29, testing@qa.com";

        String [] personas = data.split(";");
        System.out.println(personas[0]);
        System.out.println(personas[1]);
        System.out.println(personas.length);
        List<String> emailsPersonales = new ArrayList<>();

        for (int i = 0; i < personas.length; i++ ){
            String persona = personas[i];
            String [] datosPersonales = persona.split(",");
            System.out.println(datosPersonales[0]);
            System.out.println(datosPersonales[1]);
            System.out.println(datosPersonales[2]);
            System.out.println(datosPersonales[3]);
            emailsPersonales.add(datosPersonales[3]);
            System.out.println("**********************");
        }

        System.out.println(emailsPersonales);

        /*String [] datosPersonales = data.split(",");
        System.out.println(datosPersonales[0]);
        System.out.println(datosPersonales[1]);
        System.out.println(datosPersonales[2]);
        System.out.println(datosPersonales[3]);*/


    }





    private static void validateEmail(String email){
        if (email.contains("@") == false){
            Assert.assertTrue(false);
        }
        if (email.endsWith(".com") == false){
            Assert.assertTrue(false);
        }
    }

    private static void validateEmail(List<String> emails){
        for (String email : emails) {
            if (email.contains("@") == false){
                Assert.assertTrue(false);
            }
        }

    }





}
