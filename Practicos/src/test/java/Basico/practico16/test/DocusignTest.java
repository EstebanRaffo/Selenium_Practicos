package Basico.practico16.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Basico.practico16.pageObject.Docusign.DocusignLandingPage;
import Basico.practico16.pageObject.Docusign.DocusignRegistrationPage;

public class DocusignTest extends BaseTest{

    @BeforeMethod
    public void setupDocu(){
        driver.get("https://www.docusign.com.es/");
    }

    @Test
    public void docusignRegistrationTest() throws InterruptedException {
        // Page Object Pattern

        DocusignLandingPage docusignLandingPage = new DocusignLandingPage(driver);
        String titulo = docusignLandingPage.docusignPageTitle();
        String url = docusignLandingPage.docusignUrl();

        Assert.assertEquals(titulo, "DocuSign | Líder de la industria de firma electrónica");
        Assert.assertEquals(url, "https://www.docusign.com.es/");

        DocusignRegistrationPage docusignRegistrationPage = docusignLandingPage.clickPruebaGratuitaBtn();

        Assert.assertEquals(docusignRegistrationPage.getTitle(), "Prueba gratuita de DocuSign");
        Assert.assertEquals(docusignRegistrationPage.getCurrentUrl(), "https://go.docusign.com.es/o/trial/");

        docusignRegistrationPage.fillingRegistrationFields();
        docusignRegistrationPage.clickOnComenzarBtn();
        /*driver.findElement(By.name("first_name")).sendKeys("Jhon");
        driver.findElement(By.name("last_name")).sendKeys("Paul");
        driver.findElement(By.name("email")).sendKeys("test@test.com");
        driver.findElement(By.id("submitMainText_0")).click();
         */
    }
}
