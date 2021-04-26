package clase4;

import java.util.ArrayList;
import java.util.Scanner;

public class EjerciciosSumas {
    public static void main (String [] args) {
        ArrayList<Integer> listaDeNumeros = new ArrayList<Integer>();
        Scanner input = new Scanner(System.in);
        int num = -1;

        System.out.println("Ingrese numeros hasta ingresar un 0");
        while (num != 0){
            System.out.println("Ingrese un numero:");
            num = input.nextInt();
            if (num != 0){
                listaDeNumeros.add(num);
            }
        }

        int sumaTotal = sumarEnteros(listaDeNumeros);
        System.out.println("La suma total es " + sumaTotal);
        System.out.println("Los numeros ingresados fueron:");
        imprimirListaEnteros(listaDeNumeros);
    }

    public static void imprimirListaEnteros(ArrayList<Integer> listaNumeros) {
        for (int i = 0; i < listaNumeros.size(); i++){
            System.out.println(listaNumeros.get(i));
        }
    }

    //Método que reciba una lista de enteros, y retorne la suma.
    public static int sumarEnteros(ArrayList<Integer> listaNumeros){
        int suma = 0;
        for (int i = 0; i < listaNumeros.size(); i++){
            suma = suma + listaNumeros.get(i);
        }
        return suma;
    }

}
