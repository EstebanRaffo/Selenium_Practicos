package Basico.practico15.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Basico.practico15.pageObject.DocusignLandingPage;
import Basico.practico15.pageObject.DocusignRegistrationPage;

import java.util.concurrent.TimeUnit;

public class DocusignTest {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get("https://www.docusign.com.es/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void docusignRegistrationTest(){
        // Page Object Pattern

        DocusignLandingPage docusignLandingPage = new DocusignLandingPage(driver);
        String titulo = docusignLandingPage.docusignPageTitle();
        String url = docusignLandingPage.docusignUrl();

        Assert.assertEquals(titulo, "DocuSign | Líder de la industria de firma electrónica");
        Assert.assertEquals(url, "https://www.docusign.com.es/");

        DocusignRegistrationPage docusignRegistrationPage = docusignLandingPage.clickPruebaGratuitaBtn();

        Assert.assertEquals(docusignRegistrationPage.docusignPageTitle(), "Prueba gratuita de DocuSign");
        Assert.assertEquals(docusignRegistrationPage.docusignUrl(), "https://go.docusign.com.es/o/trial/");

        docusignRegistrationPage.fillingRegistrationFields();
        docusignRegistrationPage.clickOnComenzarBtn();
        /*driver.findElement(By.name("first_name")).sendKeys("Jhon");
        driver.findElement(By.name("last_name")).sendKeys("Paul");
        driver.findElement(By.name("email")).sendKeys("test@test.com");

         */

        //driver.findElement(By.id("submitMainText_0")).click();

    }
}
