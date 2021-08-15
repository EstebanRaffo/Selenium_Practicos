package Practico16.Steps;

import Practico16.PageObject.OrangeDashboardPage;
import Practico16.PageObject.OrangeLeavePage;
import Practico16.PageObject.OrangeLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class OrangeBaseSteps {
    public WebDriver driver;
    public OrangeLoginPage orangeLoginPage;
    public OrangeDashboardPage orangeDashboardPage;
    public OrangeLeavePage orangeLeavePage;

    @Given("estoy en la pagina de Orange")
    public void estoy_en_la_pagina_de_orange() {
        /*  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
         driver = new ChromeDriver();
         driver.get("https://orangehrm-demo-6x.orangehrmlive.com/");
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        */
        orangeLoginPage = new OrangeLoginPage(driver);
        orangeLoginPage.inicializarOrangePage();
    }

    @When("me logeo")
    public void me_logeo() {
        //driver.findElement(By.name("Submit")).click();
        orangeDashboardPage = orangeLoginPage.loginToOrange();
    }

}
