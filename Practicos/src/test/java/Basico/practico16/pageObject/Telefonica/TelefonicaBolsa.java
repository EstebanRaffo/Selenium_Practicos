package Basico.practico16.pageObject.Telefonica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TelefonicaBolsa {
    WebDriver driver;

    public TelefonicaBolsa(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public List<WebElement> getTabs(){
        return driver.findElements(By.className("Tab"));
    }

    public WebElement getActiveTab(){
        return driver.findElement(By.className("Tab_Active"));
    }

    public List<WebElement> getDataItem(){
        return driver.findElements(By.className("DataItem"));
    }

    public List<WebElement> getDataValue(){
        return driver.findElements(By.className("DataValue"));
    }
}
