package Intermedio.Practico11.ejemplo5_suiteListener;

import org.junit.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppleTest {

    @BeforeMethod
    public void setup(){
        System.out.println("@BeforeMethod: Se invoca al setup...");
    }

    @Test
    public void primerTest(){
        System.out.println("@Test Esto es un metodo de test ");
    }

    @Test
    public void failTest(){
        System.out.println("Test falla forzadamente");
        Assert.assertTrue(false);
    }

    @Test
    public void skippedTest(){
        throw  new SkipException("Test salteado forzadamente");
    }

    private int i = 0;
    @Test (successPercentage = 25, invocationCount = 4)
    // cada ejecucion representa el 25%
    // si el successPercentage = 75, solo tolera que un test falle
    // si el successPercentage = 50, tolera 2 fallas de las 4 ejecuciones
    // si el successPercentage = 25, tolera hasta 3 fallas de las 4
    public void percentagePassTestSuccess(){
        i++;
        if (i == 1 || i== 2 || i == 3) {
            Assert.assertTrue(false);
        }
        System.out.println("Test invocation " + i);
    }

    @AfterMethod
    public void close(){
        System.out.println("@AfterMethod: Se termino el metodo");
    }

}
