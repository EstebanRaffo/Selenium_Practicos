package ejercicio8;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NetflixTest {

    @BeforeSuite
    public void beforeTesting(){
        System.out.println("Before Suite Netflix <----");
    }

    @Test
    public void testng(){
        System.out.println("Esto es un test de Netflix");
    }

    @AfterSuite
    public void afterTesting(){
        System.out.println("After Suite Netflix <----");
    }
}
