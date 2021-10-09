package clase4.Ejemplo2_Crear_JSON_Para_POST;

public class Libro {

    String autor;
    String titulo;
    int precio;

    public Libro(){
        this.autor = "Tolkien";
        this.titulo = "Los 4 Fantasticos";
        this.precio = 1500;
    }

    public Libro(String unAutor, String unTitulo, int unPrecio){
        this.autor = unAutor;
        this.precio = unPrecio;
        this.titulo = unTitulo;
    }

}