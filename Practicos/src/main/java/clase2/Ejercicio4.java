package clase2;

import java.util.Scanner;

public class Ejercicio4 {

    public static void main (String args[]){
        int num = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        num = input.nextInt();

        if ( num > 0) {
            System.out.println("El numero es mayor a 0");
        } else if (num < 0) {
            System.out.println("El numero es menor a 0");
        } else {
            System.out.println("El numero es 0!!");
        }

    }
}
