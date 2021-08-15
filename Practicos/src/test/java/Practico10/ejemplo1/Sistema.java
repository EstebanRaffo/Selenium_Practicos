package Practico10.ejemplo1;

public class Sistema {

    public static void main(String args[]){
        MyInterface ejemplo = new Demo();

        ejemplo.metodo1();
        ejemplo.metodo2();

        MyInterface ejemplo2 = new Demo2();
        ejemplo2.metodo1();
        ejemplo2.metodo2();

    }


}
