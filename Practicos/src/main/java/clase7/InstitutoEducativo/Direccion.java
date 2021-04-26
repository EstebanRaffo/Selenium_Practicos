package clase7.InstitutoEducativo;

public class Direccion {

    private String calle;
    private int nroPuerta;

    public Direccion(String unaCalle, int unNro){
        this.calle = unaCalle;
        this.nroPuerta = unNro;
    }

    public String toString(){
        return "Vive en " + this.calle + " numero " + this.nroPuerta;
    }
}
