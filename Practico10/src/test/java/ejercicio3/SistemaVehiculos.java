package ejercicio3;

public class SistemaVehiculos {

    public static void main(String args[]){
        IVehiculo unAuto = new Auto();
        unAuto.acelerar();
        unAuto.frenar();
        unAuto.girar();
        unAuto.reversa();
        unAuto.velocidadMaxima();

        IVehiculo unaMoto = new Moto();
        unaMoto.acelerar();
        unaMoto.frenar();
        unaMoto.girar();
        unaMoto.reversa();
        unaMoto.velocidadMaxima();

        IVehiculo unaBicicleta = new Bicicleta();
        unaBicicleta.acelerar();
        unaBicicleta.frenar();
        unaBicicleta.girar();
        unaBicicleta.reversa();
        unaBicicleta.velocidadMaxima();
    }
}
