package Basico.practico16.pageObject.Telefonica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Basico.practico16.pageObject.BaseUITest;

public class TelefonicaLandingPage extends BaseUITest {

    public TelefonicaLandingPage(WebDriver newDriver){
        driver = newDriver;
    }

    public void acceptTerms(){
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    public TelefonicaBolsa switchToBolsa(){
        driver.switchTo().frame("euroland-ticker-es");
        return new TelefonicaBolsa(driver);
    }
}
