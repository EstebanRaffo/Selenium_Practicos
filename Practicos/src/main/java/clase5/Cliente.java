package clase5;

public class Cliente {
    //atributo de la clase cliente (por ser estatico)
    private static int IDENTIFICADOR = 1000;

    //atributos
    private String nombre;
    private int edad;
    private int nroCliente;

    //constructores
    public Cliente(){
        this.nombre = "Sin asignar";
        this.edad = 0;
        IDENTIFICADOR++;
        this.nroCliente = IDENTIFICADOR;
    }

    public Cliente(String unNombre, int unaEdad){
        this.nombre = unNombre;
        this.edad = unaEdad;
        IDENTIFICADOR++;
        this.nroCliente = IDENTIFICADOR;
    }

    //setters y getters
    public void setNombre (String unNombre){
        this.nombre = unNombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setEdad(int unaEdad){
        this.edad = unaEdad;
    }

    public int getEdad(){
        return this.edad;
    }

    //toString
    public String toString(){
        return "Nro cliente " + this.nroCliente + " de se llama " + this.nombre + " de edad " + this.edad;
    }


}
