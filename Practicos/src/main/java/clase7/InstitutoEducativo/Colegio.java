package clase7.InstitutoEducativo;

import java.util.ArrayList;
import java.util.List;

public class Colegio {

    public static void main(String args[]){

        Direccion direccionProfesor1 = new Direccion("Av. Rivera", 1200);

        List<Persona> listaPersonas = new ArrayList<>();
        List<Estudiante> estudiantes = new ArrayList<>();

        Persona profe1 = new Profesor("Juan", 123, "Quimica", 20000);
        profe1.setDireccion(direccionProfesor1);


        Persona a1 = new Estudiante("Andres", 678, 7, true, "2F");
        Persona alumno2 = new Estudiante("Ana", 123412, 10);

        listaPersonas.add(profe1);
        listaPersonas.add(a1);
        listaPersonas.add(alumno2);


        for (Persona p: listaPersonas){
            System.out.println(p);
        }

    }

}
