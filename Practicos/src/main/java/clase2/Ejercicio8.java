package clase2;

import java.util.Scanner;

public class Ejercicio8 {

    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        int num = 0;
        System.out.println("Ingrese numeros hasta ingresar uno negativo");
        int suma = 0;
        int contador = 0;

        while (num >= 0) {
            System.out.println("Ingrese un numero:");
            num = input.nextInt();
            if (num > 0) {
                suma = suma + num; //acumulador...
                contador++;
            }
        }

        double promedio = suma / contador;
        System.out.println("La suma total es " + suma);
        System.out.println("El promedio es " + promedio) ;
        System.out.println("El programa ha finalizado");
    }
}
