package ejercicio2;

public class Sistema {

    public static void main(String args[]){
        Alumno unAlumno = new Bachiller();
        unAlumno.calcularPromedio();

        Humano unHumano = new Universitario();
        unHumano.nombre();
        unHumano.apellido();

        Profesor unProfesor = new Adjunto();
        unProfesor.calcularSalario();
        unProfesor.instituto();
    }
}
