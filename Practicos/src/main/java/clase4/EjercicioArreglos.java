package clase4;

import java.util.Random;

public class EjercicioArreglos {

    public static void main(String[] args) {
        int[] valores = new int[10];
        Random r = new Random();

        for (int posicion = 0; posicion < valores.length; posicion++) {
            //genera valores aleatoreos entre 0 y 5
            valores[posicion] = r.nextInt(6) ;
        }

        for (int posicion = 0; posicion < valores.length; posicion++) {
            System.out.println(valores[posicion]);
        }
    }
}



