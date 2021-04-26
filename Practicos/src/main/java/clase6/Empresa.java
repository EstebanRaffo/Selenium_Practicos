package clase6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empresa {

    public static void main(String args[]){
        List<Persona> listaPersonas = new ArrayList<>();

        /*
        Persona persona1 = new Persona("Juan", 51333234);
        Persona persona2 = new Persona("Ana", 444444, 22);

        persona1.setEdad(33);
        */
        int opc = 0;
        Scanner input = new Scanner(System.in);

        while (opc != 3){
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Agregar Persona");
            System.out.println("2- Ver Personas");
            System.out.println("3- Salir");

            opc = input.nextInt();

            if (opc == 1) {
                System.out.println("****** Agregar Persona *****");

                Persona persona = agregarPersona();
                listaPersonas.add(persona);

                System.out.println("Se ha agregado una persona nueva!!");
            } else if (opc == 2) {

                imprimirListaPersonas(listaPersonas);

            } else if (opc == 3) {
                System.out.println("Saliendo del programa....");
            }

            System.out.println();
            System.out.println();
        }
    }

    public static Persona agregarPersona(){
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el nombre de la persona");
        String nombre = input.next();
        System.out.println("Ingrese el dni de la persona");
        int dni = input.nextInt();
        System.out.println("Ingrese la edad de la persona");
        int edad = input.nextInt();
        Persona persona = new Persona(nombre, dni);
        persona.setEdad(edad);
        return persona;
    }

    public static void imprimirListaPersonas(List<Persona> lista){
        if (lista.isEmpty() == true){
            System.out.println("No hay personas ingresadas!!");
        } else {
            System.out.println("***** Lista de Personas *****");
            for (Persona pers : lista){
                System.out.println(pers);
            }
        }
    }
}
