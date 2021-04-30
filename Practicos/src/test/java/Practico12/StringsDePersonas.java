package Practico12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StringsDePersonas {

    public static String data = "112111,Emiliano,Montevideo,Uruguay,testing@test.com; 12341234,Ana,Buenos Aires,Argentina,ana@ana.com";
    public static List<Persona> LISTA_PERSONAS = new ArrayList<>();

    public WebDriver driver;

    @BeforeTest
    public void loadData(){
        String [] arregloPersonas = data.split(";");

        for (int i = 0; i < arregloPersonas.length; i++){
            String [] arregloDatosPersonales = arregloPersonas[i].split(",");

            String dni = arregloDatosPersonales[0];
            String nombre = arregloDatosPersonales[1];
            String pais = arregloDatosPersonales[3];
            String email = arregloDatosPersonales[4];

            Persona persona = new Persona(Integer.parseInt(dni), nombre, pais, email);
            System.out.println(persona);
            LISTA_PERSONAS.add(persona);
        }
    }

    @BeforeMethod
    public void setDriver(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
       // driver.navigate().to("https://www.spotify.com");
    }

    private int i = 0;
    @Test (invocationCount = 2)
    public void registrationTest(){
        driver.get("https://www.spotify.com/uy/signup/");
        Persona p = LISTA_PERSONAS.get(i);
        System.out.println(p);
        driver.findElement(By.name("email")).sendKeys(p.getEmail());
        driver.findElement(By.name("confirm")).sendKeys(p.getEmail());
        driver.findElement(By.name("displayname")).sendKeys(p.getNombre());
        i++;
    }
}
