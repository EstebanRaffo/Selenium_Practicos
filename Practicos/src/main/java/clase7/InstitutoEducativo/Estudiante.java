package clase7.InstitutoEducativo;

// import clase6.InstitutoEducativo.Persona;

public class Estudiante extends Persona {

    private float promedio;
    private boolean tienePrevias;
    private String grupo;
    private int nroEstudiante;

    public Estudiante(String unNombre, int unDni, float unPromedio){
        //invoco al constructor de la clase padre....
        super(unNombre, unDni);
        this.promedio = unPromedio;

        this.tienePrevias = false;
        this.grupo = "1A";
    }

    public Estudiante(String unNombre, int unDni, float unPromedio, boolean conPrevias, String unGrupo){
        //invoco al constructor de la clase padre....
        super(unNombre, unDni);
        this.promedio = unPromedio;
        this.tienePrevias = conPrevias;
        this.grupo = unGrupo;
    }

    public String toString(){
        String info =  "El nombre de alumno es " + this.getNombre() + " y su dni es  "+ this.getDni() + " tiene promedio " + this.promedio;
        info += ". Es del grupo " + this.grupo;

        if (this.tienePrevias == true){
            info += ". El alumno tiene previas";
        } else {
            info += ". El alumno no tiene previas";
        }

        return info;
    }

}
