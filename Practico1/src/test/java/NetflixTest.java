
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class NetflixTest extends BaseTest{
    String URL = "https://www.netflix.com";

    @BeforeMethod
    public void setupNetflix(){
        driver.get(URL);
    }

    @Test
    public void showH3InForm(){
        WebElement form_element = driver.findElement(By.tagName("form"));
        List<WebElement> h3List = form_element.findElements(By.tagName("h3"));
        for(WebElement h3 : h3List){
            System.out.println(h3.getText());
        }
    }

    @Test
    public void showH3InFormImproved(){
        List<WebElement> forms = driver.findElements(By.tagName("form"));
        System.out.println("Forms => " + forms.size());
        for(WebElement form : forms){
            List<WebElement> h3List = form.findElements(By.tagName("h3"));
            System.out.println("H3 => " + h3List.size());
            for(WebElement h3 : h3List){
                System.out.println(h3.getText());
            }
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }

}
