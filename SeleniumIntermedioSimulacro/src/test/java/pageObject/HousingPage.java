package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HousingPage {
    public WebDriver driver;

    public HousingPage(WebDriver remoteDriver) { this.driver = remoteDriver; }

    public String getCurrentUrl(){ return driver.getCurrentUrl(); }

    public List<WebElement> getHuespedesResultado(){ return driver.findElements(By.xpath("//*[contains(text(),'2 huéspedes')]")); }

}
