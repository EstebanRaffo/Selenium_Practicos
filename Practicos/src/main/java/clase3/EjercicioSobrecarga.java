package clase3;

import java.util.Scanner;

public class EjercicioSobrecarga {

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        int num1 = input.nextInt();

        System.out.println("Ingrese otro numero");
        int num2 = input.nextInt();

        System.out.println("Ingrese otro numero");
        int num3 = input.nextInt();

        System.out.println("Ingrese el ultimo numero");
        int num4 = input.nextInt();

        int suma1 = sumar(num1, num2, num3, num4);
        System.out.println("La suma es " + suma1);
    }

    public static int sumar(int x, int y ){
        System.out.println("Sumando dos numeros....");
        return x+y;
    }

    public static int sumar(int num1, int num2, int num3){
        System.out.println("Sumando tres numeros....");
        return num1 + num2 + num3;
    }

    public static int sumar(int num1, int num2, int num3, int num4){
        System.out.println("Sumando cuatro numeros....");
        return num1 + num2 + num3 + num4;
    }

}
