import org.testng.annotations.*;

public class PeopleSimpleTest {

    @DataProvider(name="personas")
    public Object[][] crearPersonas(){
        return new Object[][]{
                {"Juan", new Integer(32)},
                {"Maria", new Integer(33)}
        };
    }

    @Test (dataProvider="personas")
    public void nombreTest(String nombre, Integer edad){
        System.out.println("Nombre: " + nombre + " edad: " + edad);
    }
}
