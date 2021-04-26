package clase2;

import java.util.Scanner;

public class EjerciciosFor {

    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        /*for (int contador = 0; contador <= 5; contador++){
            System.out.println("El contador vale " + contador);
        }*/
        int suma = 0;
        int num = 0;

        for (int dia = 1; dia <= 31; dia++){
            System.out.println("Ingrese el monto vendido el dia " + dia );
            num = input.nextInt();
            suma = suma + num;
        }

        System.out.println("La suma total es " + suma);

        System.out.println("El programa ha finalizado...");
    }
}
