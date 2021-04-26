package clase5;

import java.util.Scanner;

public class Empresa {

    public static void main(String args[]){
        Cliente cliente1 = new Cliente("Maria", 33);
        Cliente cliente2 = new Cliente("Juan", 22);
        Cliente cliente3 = new Cliente("Pepe", 24);
        Cliente cliente4 = new Cliente("Ana", 29);


        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println(cliente3);
        System.out.println(cliente4);

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese su nombre...");
        String unNombre = teclado.next();
        System.out.println("Ingrese su edad...");
        int unaEdad = teclado.nextInt();

        cliente1.setEdad(unaEdad);
        cliente1.setNombre(unNombre);

        System.out.println(cliente1);



    }
}
