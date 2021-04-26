package Basico.practico16.pageObject.Docusign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Basico.practico16.pageObject.BaseUITest;

public class DocusignRegistrationPage extends BaseUITest {

    public DocusignRegistrationPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public void fillingRegistrationFields(){
        driver.findElement(By.name("first_name")).sendKeys("Jhon");
        driver.findElement(By.name("last_name")).sendKeys("Paul");
        driver.findElement(By.name("email")).sendKeys("test@test.com");
    }

    public void clickOnComenzarBtn(){
//        driver.findElement(By.id("submitMainText_0")).click();
        driver.findElement(By.xpath("//label[@class=\"button text-center buttonBig\"]")).click();
    }
}
