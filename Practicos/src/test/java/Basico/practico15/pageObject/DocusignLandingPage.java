package Basico.practico15.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DocusignLandingPage {

    public WebDriver driver;

    public DocusignLandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public String docusignPageTitle(){
        return driver.getTitle();
    }

    public String docusignUrl(){
        return driver.getCurrentUrl();
    }

    public DocusignRegistrationPage clickPruebaGratuitaBtn(){
        driver.findElement(By.xpath("//*[@href='https://go.docusign.com.es/o/trial/']")).click();
        return new DocusignRegistrationPage(driver);
    }

}
