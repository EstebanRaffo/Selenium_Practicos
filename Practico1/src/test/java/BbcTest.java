import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BbcTest extends BaseTest{
    String URL = "https://www.google.com";

    @BeforeMethod
    public void setupApple(){
        driver.get(URL);
    }

    @Test
    public void getPsFromDiv(){
        WebElement div = driver.findElement(By.tagName("div"));
        List<WebElement> p_list = div.findElements(By.tagName("p"));
        for(WebElement p : p_list){
            System.out.println(p.getText());
        }
    }
/*    public void getPsFromDiv(){
        List<WebElement> div_list = driver.findElements(By.tagName("div"));
        for(WebElement div : div_list){
            System.out.println("<div>");
            List<WebElement> p_list = div.findElements(By.tagName("p"));
            for(WebElement p : p_list){
                System.out.println(p.getText());
            }
        }
    }*/

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }
}
