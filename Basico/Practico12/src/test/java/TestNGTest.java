import org.testng.annotations.*;

public class TestNGTest {

    @BeforeSuite
    public void showBeforeSuite(){System.out.println("Ejecutado en @BeforeSuite");}

    @BeforeTest
    public void showBeforeTest(){System.out.println("Ejecutado en @Beforetest");}

    @BeforeClass
    public void showBeforeClass(){System.out.println("Ejecutado en @BeforeClass");}

    @BeforeMethod
    public void showBeforeMethod(){System.out.println("Ejecutado en @BeforeMethod");}

    @Test
    public void show(){System.out.println("Ejecución del Test");}

    @AfterMethod
    public void showAfterMethod(){System.out.println("Ejecutado en @AfterMethod");}

    @AfterClass
    public void showAfterClass(){System.out.println("Ejecutado en @AfterClass");}

    @AfterTest
    public void showAfterTest(){System.out.println("Ejecutado en @AfterTest");}

    @AfterSuite
    public void showAfterSuite(){System.out.println("Ejecutado en @AfterSuite");}
}
