package Intermedio.Practico10.ejemplo2;

public class Moto implements IVehiculo {
    public void acelerar() {
        System.out.println("La moto esta acelerando....");
    }

    public void frenar() {
        System.out.println("La moto esta frenando....");

    }

    public void girar() {
        System.out.println("La moto esta girando....");

    }

    public void velocidadMaxima() {
        System.out.println("La moto tiene velocidad maxima igual a 90km/h");
    }
}
