import org.testng.annotations.*;

public class DataGenerator {

    @DataProvider(name="paises")
    public Object[][] paisesYCapitales(){
        return new Object[][]{
                {"Argentina", "Buenos Aires"},
                {"Uruguay", "Montevideo"},
                {"Chile", "Santiago"}
        };
    }

    @DataProvider(name="personas")
    public Object[][] personas(){
        return new Object[][]{
            {"Juan", "Perez", new Integer(32)},
            {"Maria", "Lopez", new Integer(33)}
        };
    }

    @DataProvider(name="persona")
    public Object[][] persona(){
        return new Object[][]{
                {"John", "Smith", "Tester", "test@test.com", "1234123412", }
        };
    }

}
