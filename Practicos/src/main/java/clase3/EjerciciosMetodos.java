package clase3;

import java.util.Scanner;

public class EjerciciosMetodos {

    //variable estática final de tipo int
    //es una constante...
    private static final int VALOR_IVA = 22;
    private static final double VALOR_PI = 3.14;

    public static void main(String args[]){
        mostrarBienvenida();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        int num1 = teclado.nextInt();
        System.out.println("Ingrese otro numero");
        int num2 = teclado.nextInt();

        boolean primeroMayor = esMayor(num1, num2);
        if (primeroMayor == true) {
            System.out.println("El primero es mayor!!");
        } else {
            System.out.println("El segundo numero es mayor!");
        }

        System.out.println("Ingrese su edad");
        int edad = teclado.nextInt();
        boolean esMayorDe18 = esMayorDeEdad(edad);

        if (esMayorDe18 == true){
            System.out.println("Ud. es mayor de edad!!");
        } else {
            System.out.println("Ud. no es mayor de 18 años");
        }

        //System.out.println("Ingrese su sueldo nominal");
        //float unSueldo = teclado.nextFloat();
        //calcularIva(unSueldo);


        /*System.out.println("Ingrese un numero");
        int numeroDelUsuario = teclado.nextInt(); //GUARDAR num

        float doble = calcularDoble(numeroDelUsuario);
        System.out.println("El doble es " + doble);

        mayorAVeinte((int) doble);

        int multiplicacion = multiplicar(5,9);
        System.out.println("La multiplicacion es " + multiplicacion);

        //int sumaTotal = sumar(2,4,5);
        //System.out.println("La suma de los numeros es " + sumaTotal);
        */
        System.out.println("Fin del programa...");
    }

    //int <-- retorno entero
    //calcularDoble <-- nombre del metodo
    //int num <-- es el parametro
    public static float calcularDoble(int unNumero){
        return (float) (unNumero * 2.5);
    }

    public static int sumar(int num1, int num2, int num3){
        int suma = num1 + num2 + num3;
        System.out.println("Sumando.....");
        return suma;
    }

    public static void mostrarBienvenida(){
        System.out.println("Bienvenidos al programa!!");
    }

    //Método que reciba un número entero e imprima en pantalla en caso de ser mayor a 20,
    // “Es mayor a 20”. De lo contrario, imprimir “No es mayor a 20”.
    public static void mayorAVeinte(int unNumero){
        if (unNumero > 20){
            System.out.println("Es mayor a 20");
        } else {
            System.out.println("No es mayor a 20!!");
        }

    }

    //Método que reciba dos enteros y retorne la multiplicación de la misma.
    public static int multiplicar(int num1, int num2){
        int multiplicacion = num1 * num2;
        return multiplicacion;
    }

    //Crear un método que permita reciba el alto y ancho de un paralelogramo tipo, y retorne su área.
    public static float calcularArea(float alto, float ancho){
        return alto * ancho;
    }

    public static void calcularIRPF(float sueldo){

    }

    public static void calcularIva(float sueldo){
        double iva = sueldo * VALOR_IVA;
        System.out.println("El IVA de " + sueldo + " es " + iva);
    }

    //Método que reciba dos números y retorne verdadero si el primer número es mayor que el segundo.
    // De lo contrario, retornar debe retornar falso.

    public static boolean esMayor(int num1, int num2){
        if (num1 > num2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esMayorDeEdad(int unaEdad){
        if (unaEdad > 18){
            return true;
        } else {
            return false;
        }
    }
}
