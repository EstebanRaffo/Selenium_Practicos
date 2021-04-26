package clase5;

public class Camion {

    //atributos de los camiones...
    private int chapa;
    private String color;
    private int añoCompra;

    //constructor sin parametros...
    public Camion(){
        this.chapa = 1111;
        this.color = "Blanco";
    }

    //constructor con parametros...
    public Camion(int unaChapa, String unColor){
        this.chapa = unaChapa;
        this.color = unColor;
    }

    public void setChapa(int unaChapa){
        this.chapa = unaChapa;
    }

    public int getChapa(){
        return this.chapa;
    }

    // c1.setColor("Negro");
    // c4.setColor("Negro");
    public void setColor(String unColor){
        this.color = unColor;
    }

    public String getColor(){
        return this.color;
    }

    public void setAñoCompra(int unAño){
        this.añoCompra = unAño;
    }

    public int getAñoCompra(){
        return this.añoCompra;
    }

    public boolean leTocaRevision(){
        if (this.añoCompra < 2015){
            return true;
        } else {
            return false;
        }
    }

    //tieneMismoColor,
    // que reciba un Camión como parámetro, y retorne verdadero si dos camiones tienen el mismo color
    public boolean tieneMismoColor(Camion unCamion){
        return this.getColor().equals(unCamion.getColor());
    }

    public String toString(){
        String retorno = "El camion tiene chapa " + this.chapa + ". ";
        retorno += "Su color es " + this.color;

        if (this.leTocaRevision() == true){
            retorno += " y le toca revision!!";
        } else {
            retorno += " y no le toca revision!!!";
        }

        return retorno;
    }

}
