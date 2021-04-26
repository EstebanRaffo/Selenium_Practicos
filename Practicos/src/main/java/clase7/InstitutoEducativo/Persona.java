package clase7.InstitutoEducativo;

public class Persona {
    protected String nombre;
    private int dni;
    private int edad;
    protected Direccion direccion;

    public Persona(String unNombre, int unDni){
        this.nombre = unNombre;
        this.dni = unDni;
        this.edad = 0;
    }

    public Persona(String unNombre, int unDni, int unaEdad ){
        this.nombre = unNombre;
        this.edad = unaEdad;
        this.dni = unDni;
    }

    public void setDireccion(Direccion dire) {
        this.direccion = dire;
    }

    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setDni(int unDNI){
        this.dni = unDNI;
    }

    public int getDni(){
        return this.dni;
    }

    public void setEdad(int unaEdad){
        this.edad = unaEdad;
    }

    public int getEdad(){
        return this.edad;
    }

    public String toString(){
        String descripcion = "El nombre de la persona es " + this.nombre + " de DNI " + this.dni;
        if (this.edad > 0 && this.edad < 100){
            descripcion += ". Su edad es " + this.edad;
        }
        descripcion += ". " + this.direccion;
        return descripcion;

    }


}
