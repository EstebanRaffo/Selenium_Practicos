import org.testng.annotations.DataProvider;

public class DataProviderPeople {

    @DataProvider(name="people")
    public Object[][] people(){
        return new Object[][]{
                {"Pedro", "Perez", new Integer(34)},
                {"Juan", "Gomez", new Integer(35)}
        };
    }
}
