package clase7;

import java.util.Scanner;

public class Arreglos {

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        int [] arregloNumeros = new int [5];
        arregloNumeros[0]=2;
        arregloNumeros[1]=4;
        arregloNumeros[2]=6;
        arregloNumeros[3]=7;

        System.out.println("Ingrese una posicion");

        int pos = input.nextInt();

        try {
            System.out.println("El valor del arreglo es " + arregloNumeros[pos]);
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Posicion invalida!!");
        } catch (Exception ex){
            System.out.println("Ha ocurrido una excepcion: " + ex.getMessage() );
        }
    }



}
