import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppleTest extends BaseTest{
    String URL = "https://www.apple.com";

    @BeforeMethod
    public void setupApple(){
        driver.get(URL);
    }

    /*@Test
    public void getDivs(){
        List<WebElement> div_list = driver.findElements(By.tagName("div"));
        List<WebElement> divs_with_id = getDivsWithId(div_list);
        for(WebElement div : divs_with_id){
            System.out.println("div id = " + div.getAttribute("id") + ": " + div.getText());
        }
    }

    private List<WebElement> getDivsWithId(List<WebElement> div_list){
        List<WebElement> divs_with_id;
        for(WebElement div : div_list){
            String id = div.getAttribute("id");
            if(id != null){
                divs_with_id.add(div);
            }
        }
        Assert.assertNotEquals(null, divs_with_id);
        return divs_with_id;
    }*/

    @Test
    public void getDivsWithIds(){
        int cantidad = 0;
        List<WebElement> div_list = driver.findElements(By.tagName("div"));
        for(WebElement div : div_list){
            if(!div.getAttribute("id").isEmpty()){
                cantidad++;
            }
        }
        System.out.println("Los div con id son: " + cantidad);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }
}
