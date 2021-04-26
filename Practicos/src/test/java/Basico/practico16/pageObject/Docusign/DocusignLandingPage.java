package Basico.practico16.pageObject.Docusign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Basico.practico16.pageObject.BaseUITest;

public class DocusignLandingPage extends BaseUITest {

    public DocusignLandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public String docusignPageTitle() throws InterruptedException {
        Thread.sleep(3000);
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
