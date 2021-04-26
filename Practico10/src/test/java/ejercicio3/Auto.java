package ejercicio3;

public class Auto implements  IVehiculo{

    public void acelerar(){
        System.out.println("auto acelerando");
    }

    public void frenar() {
        System.out.println("auto frenando");
    }

    public void girar() {
        System.out.println("auto girando");
    }

    public void reversa() {
        System.out.println("auto en reversa");
    }

    public void velocidadMaxima() {
        System.out.println("auto en velocidad maxima");
    }
}
