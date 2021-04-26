package clase7;

import java.util.Scanner;

public class JavaUtils {
    private static Scanner INPUT = new Scanner(System.in);

    public static String ingresarPalabras(String unMensaje){
        System.out.println(unMensaje);
        String texto = INPUT.next();
        return texto;
    }

    public static int ingresarNumero(String unMensaje){
        System.out.println(unMensaje);
        int num = INPUT.nextInt();
        return num;
    }

    public static int ingresarNumero(String unMensaje, int min, int max){
        System.out.println(unMensaje);

        int num = INPUT.nextInt();
        while (num < min || num > max){
            System.out.println("El numero esta fuera de rango, reingrese");
            num = INPUT.nextInt();
        }
        return num;
    }

    public static int ingresarNumero(String unMensaje, int min, int max, String error){
        System.out.println(unMensaje);

        int num = INPUT.nextInt();
        while (num < min || num > max){
            System.out.println(error);
            num = INPUT.nextInt();
        }
        return num;
    }

    public static float ingresarDecimal(String unMensaje){
        System.out.println(unMensaje);
        float num = INPUT.nextFloat();
        return num;
    }


}
