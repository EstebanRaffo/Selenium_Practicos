package clase7;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManejoDeExcepciones {

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Ingrese la suma de sus notas");
            int notas = input.nextInt();
            System.out.println("Ingrese la cantidad de notas obtenidas");
            int cantidad = input.nextInt();
            int promedio = notas/cantidad;
        } catch (ArithmeticException ex){
            System.out.println("No se puede dividir entre 0!!");
        } catch (InputMismatchException ex){
            System.out.println("Ha ingresado un dato invalido");
        } catch (Exception ex){
            System.out.println("Ha ocurrido una excepcion!!" + ex.getMessage());
        }

        System.out.println("Ingrese su edad");
        try {
            int edad = input.nextInt();
            System.out.println("Su edad es " + edad);
        } catch (Exception ex){
            System.out.println("Ha ocurrido una excepcion!! " + ex.getMessage());
        }
        System.out.println("Fin del programa");


    }
}
