package Intermedio.Practico16.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class OrangeLoginPage {

    public WebDriver driver;

    public OrangeLoginPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }

    public void inicializarOrangePage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public OrangeDashboardPage loginToOrange(){
        driver.findElement(By.name("Submit")).click();
        OrangeDashboardPage orangeDashboardPage = new OrangeDashboardPage(driver);
        return orangeDashboardPage;
    }


}
