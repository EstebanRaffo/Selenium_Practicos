package Basico.practico15.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocusignRegistrationPage {

    public WebDriver driver;

    public DocusignRegistrationPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public String docusignPageTitle(){
        return driver.getTitle();
    }

    public String docusignUrl(){
        return driver.getCurrentUrl();
    }

    public void fillingRegistrationFields(){
        driver.findElement(By.name("first_name")).sendKeys("Jhon");
        driver.findElement(By.name("last_name")).sendKeys("Paul");
        driver.findElement(By.name("email")).sendKeys("test@test.com");
    }

    public void clickOnComenzarBtn(){
        driver.findElement(By.id("submitMainText_0")).click();
    }









}
