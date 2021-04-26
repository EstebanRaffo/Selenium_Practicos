package clase5;

import java.util.Scanner;

public class Automotora {

    public static void main(String args[]){
        /*String nombre = "Andres";

        if (nombre.equals("Andres")){
            System.out.println("Son iguales!!");
        } else {
            System.out.println("Son distintos");
        }*/

        Scanner input = new Scanner(System.in);

        Camion c1 = new Camion();

        Camion c2 = new Camion(555, "Blanco");
        System.out.println(c1);
        System.out.println(c2);
        if (c1.tieneMismoColor(c2) == true) {
            System.out.println("Los camiones tienen el mismo color!!");
        } else {
            System.out.println("Los camiones NO tienen el mismo color!!");
        }


        System.out.println("Ingrese el nuevo color del camion");
        String color = input.next();

        System.out.println("Ingrese la nueva chapa del camion");
        int unaChapa = input.nextInt();

        System.out.println("Se han actualizado los datos del camion....");

        c1.setChapa(unaChapa);
        System.out.println("La nueva chapa es " + c1.getChapa());

        c1.setColor(color);
        System.out.println("El nuevo color es " + c1.getColor());

        System.out.println("Ingrese el año de compra del camion");
        int unAño = input.nextInt();
        c1.setAñoCompra(unAño);

        if (c1.leTocaRevision() == true){
            System.out.println("Le toca revision!!");
        } else {
            System.out.println("No le toca revision...");
        }

        System.out.println(c1);
    }


}
