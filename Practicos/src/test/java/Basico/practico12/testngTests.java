package Basico.practico12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class testngTests {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("-> esto es before suite method");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("-> esto es before class method");
    }

    @BeforeClass
    public void beforeClassTest(){
        System.out.println("--> esto es before class method");
    }

    @BeforeMethod
    public void beforeMethodTest(){
        System.out.println("--> Esto es before method");
    }

    @Test
    @Parameters({"tagName"})
    public void primerTest(@Optional("button") String tag){ /** button es valor que roma tag si lo ejecuto localmente **/
        System.out.println("---> Esto es un test!!" + tag);
        WebDriver driver = new ChromeDriver();

        List<WebElement> listaElementos = driver.findElements(By.tagName(tag));

    }

    @Test
    public void segundoTest(){
        System.out.println("---> Esto es otro test!!");
    }

    @AfterMethod
    public void afterMethodTest(){
        System.out.println("---> After method test");
    }

    @AfterClass
    public void afterClassMethod(){
        System.out.println("--> After class test");
    }

    @AfterTest
    public void afterTestMethod(){
        System.out.println("--> After test");
    }

    @AfterSuite
    public void afterSuiteMethod(){
        System.out.println("--> After suite");
    }
}
