import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TelefonicaTest {
    private static String TELEFONICA_URL = "https://www.telefonica.com";
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(TELEFONICA_URL);
        driver.manage().timeouts().implicitlyWait( 15, TimeUnit.SECONDS ) ;
    }

    @Test
    public void telefonicaTest() throws InterruptedException {

        LandingPage setting_page = new LandingPage(driver);
        System.out.println("Titulo: " + setting_page.getTitlePage());
        setting_page.acceptCokies();

        WebDriver driver_quotation = setting_page.navigateToQuotation();
        CotizacionesPage quotation_page = setting_page.enterToQuotation(driver_quotation);

        quotation_page.validateCurrentSelected("BME");
        quotation_page.clickToNyse();
        quotation_page.validateCurrentSelected("NYSE");

        quotation_page.showValues();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
