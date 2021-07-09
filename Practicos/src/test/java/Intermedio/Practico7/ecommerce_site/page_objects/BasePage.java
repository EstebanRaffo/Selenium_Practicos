package Intermedio.ecommerce_site.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public String getPageTitle(){ return driver.getTitle(); }

    public String getCurrentURL(){
        return driver.getTitle();
    }

}
