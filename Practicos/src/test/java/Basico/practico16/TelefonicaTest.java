package Basico.practico16;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Basico.practico16.pageObject.Telefonica.TelefonicaBolsa;
import Basico.practico16.pageObject.Telefonica.TelefonicaLandingPage;
import Basico.practico16.test.BaseTest;

import java.util.List;

public class TelefonicaTest extends BaseTest {

    @BeforeTest
    public void setupTelefonica(){
        driver.get("https://www.telefonica.com");
    }

    @Test
    public void telefonicaTest() throws InterruptedException {
        TelefonicaLandingPage telefonicaLandingPage = new TelefonicaLandingPage(driver);

        System.out.println(telefonicaLandingPage.getTitle());
        System.out.println(telefonicaLandingPage.getCurrentUrl());

        Assert.assertEquals(telefonicaLandingPage.getTitle(), "Telefónica");
        Assert.assertEquals(telefonicaLandingPage.getCurrentUrl(), "https://www.telefonica.com/es/home");

        telefonicaLandingPage.acceptTerms();

        TelefonicaBolsa telefonicaBolsa = telefonicaLandingPage.switchToBolsa();

        List<WebElement> tabs = telefonicaBolsa.getTabs();

        Assert.assertEquals(tabs.size(), 2);

        for (WebElement elementTab: tabs) {
            System.out.println("-----> " + elementTab.getText());
            if (elementTab.getText().equals("NYSE")){
                elementTab.click();
            }
        }

        WebElement activeTab = telefonicaBolsa.getActiveTab();

        Assert.assertEquals(activeTab.getText(), "NYSE");

        List<WebElement> dataItems = telefonicaBolsa.getDataItem();
        List<WebElement> dataValues = telefonicaBolsa.getDataValue();

        Assert.assertEquals(dataItems.size(), 6);
        Assert.assertEquals(dataValues.size(), 6);

        for (WebElement di: dataItems){
            if (di.getText().isEmpty() == false) {
                System.out.println("--> " +di.getText());
            }
        }

        for (WebElement dv : dataValues) {
            if (dv.getText().isEmpty() == false) {
                System.out.println("--> " + dv.getText());
            }
        }

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

}
