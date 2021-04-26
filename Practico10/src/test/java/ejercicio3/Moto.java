package ejercicio3;

public class Moto implements IVehiculo{

    public void acelerar(){
        System.out.println("moto acelerando");
    }

    public void frenar() {
        System.out.println("moto frenando");
    }

    public void girar() {
        System.out.println("moto girando");
    }

    public void reversa() {
        System.out.println("moto en reversa");
    }

    public void velocidadMaxima() {
        System.out.println("moto en velocidad maxima");
    }
}
