import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MundomacTest extends BaseTest{
    String URL = "https://www.mundomac.com";

    @BeforeMethod
    public void setupApple(){
        driver.get(URL);
    }

    @Test
    public void showAltFromImg(){
        List<WebElement> images = driver.findElements(By.tagName("img"));
        Assert.assertFalse(images.isEmpty());

        ArrayList<String> attr_alt = new ArrayList<>();
        int cont = 0;

        for(WebElement image : images){
            String alt = image.getAttribute("alt");
            if(!alt.isEmpty()){
                attr_alt.add(alt);
            }else{
                cont++;
            }
        }
        Assert.assertNotEquals(0, attr_alt.size());
        Assert.assertTrue(!attr_alt.isEmpty());

        System.out.println("Atributos alt: ");
        for(String alt : attr_alt){
            System.out.println(alt);
        }
        System.out.println("Se encontraron " + cont + " img sin alt");
    }

    @Test
    public void validateButtons(){
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        Assert.assertTrue(!buttons.isEmpty());
        boolean valid_class = true;

        for(WebElement button : buttons){
            String content_class = button.getAttribute("class");
            System.out.println("clase: " + content_class);
            System.out.println(content_class.contains("navbar-toggler"));
            valid_class = valid_class && content_class.contains("navbar-toggler"); // btn btn-default
        }
        Assert.assertTrue(valid_class);
    }

    @Test
    public void validateButtonsWithClass(){
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        Assert.assertTrue(!buttons.isEmpty());

        for(WebElement button : buttons){
            Assert.assertTrue(button.getAttribute("class").contains("navbar-toggler"));
        }
    }

    @Test
    public void getLinksFromLi(){

        List<WebElement> ul_list = driver.findElements(By.tagName("ul"));
        Assert.assertTrue(!ul_list.isEmpty());
        int cont_no_link = 0;


        for(WebElement ul : ul_list){
            List<WebElement> li_list = ul.findElements(By.tagName("li"));
            Assert.assertTrue(!li_list.isEmpty());

            for(WebElement li : li_list){
                try {
                    WebElement a = li.findElement(By.tagName("a"));
                    String link = a.getAttribute("href");
                    System.out.println(link);
                } catch (Exception e){
                    System.out.println("Elemento sin href");
                    cont_no_link++;
                }
            }
        }

        System.out.println("Se econtraron " + cont_no_link + " links sin href");
    }

    @Test
    public void getImgWithAlt(){
        List<WebElement> img_list = driver.findElements(By.tagName("img"));
        List<WebElement> alts = new ArrayList<>();
        List<WebElement> without_alts = new ArrayList<>();

        for(WebElement img : img_list){
            String alt = img.getAttribute("alt");
            if(!alt.isEmpty()){
                alts.add(img);
                System.out.println("alt: " + alt);
            }else{
                String src = img.getAttribute("src");
                without_alts.add(img);
                System.out.println("src: " + src);
            }
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }
}
