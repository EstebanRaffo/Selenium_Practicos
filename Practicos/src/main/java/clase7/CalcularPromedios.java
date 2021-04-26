package clase7;

import java.util.Scanner;

public class CalcularPromedios {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        int num = -1;
        int suma = 0;
        int contador = 0;

        boolean numeroInvalido = true;

        while (numeroInvalido == true) {
            System.out.println("Ingrese un numero");
            try {
                num = input.nextInt();
                numeroInvalido = false;
            } catch ( Exception ex) {
                System.out.println("Numero invalido, reingrese");

            }
        }
        System.out.println("El numero ingresado es " + num);



        while(num!= 0){
            System.out.println("Ingrese un numero");
            num = input.nextInt();
            suma = suma + num;
            if (num!=0){
                contador++;
            }
        }
        System.out.println("La suma es " + suma);
        System.out.println("El contador es " + contador);
        try {
            double promedio = suma / contador;
            System.out.println("El promedio es " + promedio);
        } catch (ArithmeticException ex) {
            System.out.println("No se ingresaron numeros para el promedio");
            System.out.println("El promedio es 0");
        } catch (Exception ex){
            System.out.println("Ha ocurrido una excepcion"  + ex.getMessage());
        }
        System.out.println("Fin del programa");
    }



}
