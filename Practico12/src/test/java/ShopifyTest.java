import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ShopifyTest {

    public static String data = "112111,Emiliano,Montevideo,Uruguay,testing@test.com;12341234,Ana,Buenos Aires,Argentina,ana@ana.com";
    public static List<Persona> LISTA_PERSONAS = new ArrayList<>();
    public WebDriver driver;

    @BeforeTest
    public void loadData(){
        String[] datos_de_personas = data.split(";");

        for(String datosPersonales : datos_de_personas){
            String[] datos_de_persona = datosPersonales.split(",");
            int dni = Integer.parseInt(datos_de_persona[0]);
            String nombre = datos_de_persona[1];
            String ciudad = datos_de_persona[2];
            String pais = datos_de_persona[3];
            String email = datos_de_persona[4];
            Persona unaPersona = new Persona(dni, nombre, ciudad, pais, email);
            LISTA_PERSONAS.add(unaPersona);
        }
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
    }

    private int i = 0;
    @Test (invocationCount = 2)
    public void registrationTest() throws InterruptedException {
        driver.get("https://www.shopify.com/signup");
        Persona unaPersona = LISTA_PERSONAS.get(i);
        String email = unaPersona.getEmail();


        driver.findElement(By.xpath("//section[@role='region']/button")).click(); // Cerrar anuncio
        Thread.sleep(3000);
        driver.findElement(By.id("0_signup_email")).sendKeys(email);
        driver.findElement(By.id("0_signup_shop_name")).sendKeys(unaPersona.getNombre());
        //driver.findElement(By.xpath("//button[contains(text(), 'Start free trial')]")).click();
        i++;
    }

    /*@AfterMethod
    public void tearDown(){
        driver.close();
    }*/
}
