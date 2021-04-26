package ejercicio3;

import javax.crypto.spec.IvParameterSpec;

public class Bicicleta implements IVehiculo {

    public void acelerar(){
        System.out.println("bicicleta acelerando");
    }

    public void frenar() {
        System.out.println("bicicleta frenando");
    }

    public void girar() {
        System.out.println("bicicleta girando");
    }

    public void reversa() {
        System.out.println("bicicleta en reversa");
    }

    public void velocidadMaxima() {
        System.out.println("bicicleta en velocidad maxima");
    }
}
