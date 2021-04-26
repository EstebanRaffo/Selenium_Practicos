package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class dependentGroups {
    WebDriver driver;
    private String URL_SHOPIFY = "https://es.shopify.com/";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_SHOPIFY);
    }

    @Test (dependsOnMethods = {"testTwo", "testThree"})
    public void testOne(){
        System.out.println("Ultimo test");
    }

    @Test
    public void testTwo(){
        System.out.println("testTwo del grupo 1");
    }

    @Test
    public void testThree(){
        System.out.println("testThree del grupo 1");
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Fin del test");
        driver.close();
    }
}
