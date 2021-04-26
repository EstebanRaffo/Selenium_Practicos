package clase2;

import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String args[]){
        //TECLADO
        Scanner input = new Scanner(System.in);
        String nombre = "Desconocido";
        int año = 1900;

        //MOSTRAR "Ingrese su nombre"
        System.out.println("Ingrese su nombre");

        //GUARDAR nombre...
        nombre = input.next();

        System.out.println("Ingrese su año de nacimiento");
        año = input.nextInt();

        int edad = 2020 - año;
        System.out.println("Su nombre es " + nombre + " y su edad es " + edad);
    }

}
