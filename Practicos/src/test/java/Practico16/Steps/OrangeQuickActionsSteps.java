package Practico16.Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Practico16.Helpers.Constants;
import Practico16.Helpers.StepsHelper;
import Practico16.PageObject.OrangeDashboardPage;
import Practico16.PageObject.OrangeLeavePage;
import Practico16.PageObject.OrangeLoginPage;

import java.util.concurrent.TimeUnit;

public class OrangeQuickActionsSteps extends OrangeBaseSteps {

    /*@Given("estoy en la pagina de Orange")
    public void estoy_en_la_pagina_de_orange() {
       *//*  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
         driver = new ChromeDriver();
         driver.get("https://orangehrm-demo-6x.orangehrmlive.com/");
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
*//*
        orangeLoginPage = new OrangeLoginPage(driver);
        orangeLoginPage.inicializarOrangePage();
    }

    @When("me logeo")
    public void me_logeo() {
        //driver.findElement(By.name("Submit")).click();
        orangeDashboardPage = orangeLoginPage.loginToOrange();
    }*/

    @And("entro en la landing page")
    public void entro_en_la_landing_page() {
        Assert.assertTrue(orangeDashboardPage.getDashboardTitle().contains("Dashboard"));
        Assert.assertTrue(orangeDashboardPage.getDashboardURL().contains("client/#/dashboard"));
    }

    @Then("veo la Assign Leave de quick actions")
    public void veo_la_assign_leave_de_quick_actions() throws InterruptedException {
        //boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_ASSIGN_LEAVE, driver);
        boolean quickActionFound = orangeDashboardPage.searchQuickAction(Constants.QUICK_ACTION_ASSIGN_LEAVE);
        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_ASSIGN_LEAVE );
       // driver.close();
    }

    @Then("veo la Leave List de quick actions")
    public void veo_la_leave_list_de_quick_actions() throws InterruptedException {
        //boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_LEAVE_LIST, driver);
        boolean quickActionFound = orangeDashboardPage.searchQuickAction(Constants.QUICK_ACTION_LEAVE_LIST);

        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_LEAVE_LIST );
       // driver.close();
    }

    @Then("veo la Leave Calendar de quick actions")
    public void veo_la_leave_calendar_de_quick_actions() throws InterruptedException {
        //boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_LEAVE_CALENDAR, driver);
        boolean quickActionFound = orangeDashboardPage.searchQuickAction(Constants.QUICK_ACTION_LEAVE_CALENDAR);
        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_LEAVE_CALENDAR );
      //  driver.close();
    }

    String cantidadDeElementos = "";

    @When("obtengo la cantidad de leave request to approve")
    public void obtengo_la_cantidad_de_leave_request_to_approve() throws InterruptedException {
        /*Thread.sleep(3000);
        WebElement cantidadLeaveRequestElement = driver.findElement(By.className("record-count"));
        String cantidad = cantidadLeaveRequestElement.getText();
        System.out.println(cantidad);

        cantidadDeElementos = cantidad.replace("(", "");
        cantidadDeElementos = cantidadDeElementos.replace(")", "");
        */
        cantidadDeElementos = orangeDashboardPage.getLeaveRequestToApproveNumber();

    }

    @When("ingreso a la seccion de leave request")
    public void ingreso_a_la_seccion_de_leave_request() {
        //driver.findElement(By.className("link-title")).click();
        orangeLeavePage = orangeDashboardPage.goToLeavePage();
    }

    @Then("la cantidad de elementos a deplegarse debe coincidir")
    public void la_cantidad_de_elementos_a_deplegarse_debe_coincidir() throws InterruptedException {

        //WebElement tableInfoElement = driver.findElement(By.className("summary"));
        //System.out.println("---- " + tableInfoElement.getText());
        // 1 - 9 of 9
        Assert.assertTrue(orangeLeavePage.getAmountOfLeaveElements().endsWith("of " + cantidadDeElementos));
        //driver.close();

    }



}
