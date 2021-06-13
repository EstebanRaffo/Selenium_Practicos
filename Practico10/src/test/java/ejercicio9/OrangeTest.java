package ejercicio9;

import ejercicio6.InvokedMethodListeners;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(value = InvokedMethodListeners.class)
public class OrangeTest {

    @BeforeMethod
    public void setup(){ System.out.println("@BeforeMethod: setup()"); }

    @Test
    public void primerTest(){ System.out.println("@Test: primerTest()"); }

    @Test
    public void segundoTest(){
        System.out.println("@Test: segundoTest()");
        Assert.assertTrue(5 < 4, "es falso");
    }

    @AfterMethod
    public void close(){ System.out.println("@AfterMethod: Termina el método"); }

}
