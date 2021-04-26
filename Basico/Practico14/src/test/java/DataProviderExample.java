import org.testng.annotations.Test;

public class DataProviderExample {

    @Test(dataProvider = "paises", dataProviderClass = DataGenerator.class)
    public void mostrarPaises(String pais, String capital){
        System.out.println("Pais: " + pais + " Capital: " + capital);
    }

    @Test(dataProvider = "personas", dataProviderClass = DataGenerator.class)
    public void mostrarDatosPersonas(String nombre, String apellido, Integer edad){
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Edad: " + edad);
    }
}
