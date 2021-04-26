import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver remote_driver){
        driver = remote_driver;
    }

    public String getTitlePage(){
        return driver.getTitle();
    }

    public void acceptCokies(){
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    public WebDriver navigateToQuotation() throws InterruptedException {
        Thread.sleep(2000);
        WebElement iframeCotizaciones = driver.findElement(By.id( "euroland-ticker-es" ));
        driver.switchTo().frame( iframeCotizaciones );
        return driver;
    }

    public CotizacionesPage enterToQuotation(WebDriver driver_quotes){
        return new CotizacionesPage(driver_quotes);
    }

}
