package clase2;

import java.util.Scanner;

public class Ejercicio3 {

    public static void main (String args[]){
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int suma = 0;

        //TECLADO
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese un numero");
        num1 = input.nextInt();

        System.out.println("Ingrese otro numero!");
        num2 = input.nextInt();

        System.out.println("Ingrese el tercer numero");
        num3 = input.nextInt();

        suma = num1 + num2 + num3;

        System.out.println("La suma es " + suma);
    }
}
