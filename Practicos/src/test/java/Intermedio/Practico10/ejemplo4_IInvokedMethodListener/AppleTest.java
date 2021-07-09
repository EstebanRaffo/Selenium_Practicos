package Intermedio.Practico10.ejemplo4_IInvokedMethodListener;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = InvokedMethodListeners.class)
public class AppleTest {

    @BeforeMethod
    public void setup(){
        System.out.println("@BeforeMethod: Se invoca al setup...");
    }

    @Test
    public void primerTest(){
        System.out.println("@Test Esto es un metodo de test ");
    }

    @AfterMethod
    public void close(){
        System.out.println("@AfterMethod: Se termino el metodo");
    }

}
