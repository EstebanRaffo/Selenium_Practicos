package Practico10.ejemplo3;

public class Colegio {

    public static void main(String args[]){
        IAlumno alumno = new AlumnoBachiller();
        alumno.mostrarGrado();
        alumno.mostrarPromedio();
        alumno.mostrarDNI();
        alumno.mostrarNombre();

    }

}
