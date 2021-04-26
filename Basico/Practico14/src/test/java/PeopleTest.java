import org.testng.annotations.Test;

public class PeopleTest {

    @Test (dataProvider = "people", dataProviderClass = DataProviderPeople.class)
    public void printData(String name, String surname, Integer age){
        System.out.println("Name: " + name + " Apellido: " + surname + " Edad: " + age);
    }
}
