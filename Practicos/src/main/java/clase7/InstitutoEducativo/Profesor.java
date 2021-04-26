package clase7.InstitutoEducativo;

public class Profesor extends Persona {

    private String materia;
    private int salario;


    public Profesor(String unNombre, int unDni, String unaMateria, int unSalario){
        super(unNombre, unDni);
        this.materia = unaMateria;
        this.salario = unSalario;
        this.nombre = unNombre;
    }

    public String toString(){
        return super.toString() + ". Profesor de " + this.materia + " de salario " + this.salario;
    }


}
