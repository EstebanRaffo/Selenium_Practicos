package Practico10.ejemplo2;

public class Automotora {

    public static void main(String args[]){
        IVehiculo auto = new Auto();

        auto.acelerar();
        auto.girar();
        auto.frenar();
        auto.velocidadMaxima();


        IVehiculo moto = new Moto();
        moto.velocidadMaxima();
        moto.girar();
        moto.acelerar();
        moto.frenar();
    }
}
