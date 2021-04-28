package Practico12;

public class Persona {

    private int dni;
    private String nombre;
    private String pais;
    private String email;

    public Persona(int unDni, String unNombre, String unPais, String unEmail){
        this.nombre = unNombre;
        this.dni = unDni;
        this.pais = unPais;
        this.email = unEmail;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public String toString(){
        return "Nombre de la persona: " + this.nombre + " y dni " + this.dni + " de " + this.pais;
    }

}
