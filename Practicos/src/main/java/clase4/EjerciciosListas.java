package clase4;

import java.util.ArrayList;
import java.util.Scanner;

public class EjerciciosListas {

    public static void main (String [] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> listaNombres = new ArrayList<String>();

        listaNombres.add("Juan");
        listaNombres.add("Ana");
        listaNombres.add("Andres");

        imprimirListaString(listaNombres);
        System.out.println("Ingrese una posicion a eliminar");
        int posicionAEliminar = input.nextInt();

        System.out.println("Eliminando elemento....");
        listaNombres.remove(posicionAEliminar);
        System.out.println("Elemento eliminado!");

        imprimirListaString(listaNombres);

        System.out.println("Ingrese un nombre a buscar");
        String nombreABuscar = input.next();

        int posicion = listaNombres.indexOf(nombreABuscar);

        if (posicion == -1) {
            System.out.println("El elemento no se encuentra en la lista!!");
        } else {
            System.out.println(nombreABuscar + " se encuentra en la posicion " + posicion);
        }

        //listaNombres.clear();
        //imprimirListaString(listaNombres);
    }

    public static void imprimirListaString(ArrayList<String> lista){
        if (lista.isEmpty() == false) {
            for (int pos = 0; pos < lista.size(); pos++){
                System.out.println(pos + "- " +lista.get(pos));
            }
        } else {
            System.out.println("La lista esta vacia!!");
        }
    }


}
