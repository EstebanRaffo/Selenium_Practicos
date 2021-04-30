public class Empleado {

    private int dni;
    private String nombre;
    private String cargo;

    public Empleado(int unDni, String unNombre, String unCargo){
        this.dni = unDni;
        this.nombre = unNombre;
        this.cargo = unCargo;
    }

    public int getDni(){
        return this.dni;
    }

    public String getNombre(){
        return this.nombre;
    }
}
