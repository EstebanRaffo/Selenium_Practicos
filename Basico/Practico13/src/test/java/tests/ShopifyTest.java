package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class ShopifyTest {
    WebDriver driver;
    private int param;
    private String URL_SHOPIFY = "https://es.shopify.com/";

    public ShopifyTest(){
        this.param = 0;
    }

    public ShopifyTest(int param){
        this.param = param;
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SHOPIFY);
    }

    @Test
    public void testButtons(){
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        ArrayList<WebElement> not_empty_buttons = new ArrayList<WebElement>();

        for(WebElement element : buttons){
            if(!element.getText().equals("")){
                not_empty_buttons.add(element);
            }
        }

        System.out.println("Boton elegido: " + not_empty_buttons.get(this.param).getText());
    }


    @AfterMethod
    public void tearDown(){
        System.out.println("Fin del test");
        driver.close();
    }
}
