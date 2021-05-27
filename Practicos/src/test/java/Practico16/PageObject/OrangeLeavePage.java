package Practico16.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrangeLeavePage {

    public WebDriver driver;

    public OrangeLeavePage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public String getAmountOfLeaveElements() throws InterruptedException {
        Thread.sleep(4000);
        return  driver.findElement(By.className("summary")).getText();
    }


}
