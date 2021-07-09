package Intermedio.Practico15;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeSteps {

    public WebDriver driver;

    @Given("estoy en la pagina de Orange")
    public void estoy_en_la_pagina_de_orange() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/");
    }

    @When("me logeo")
    public void me_logeo() {
        driver.findElement(By.name("Submit")).click();
    }

    @And("entro en la landing page")
    public void entro_en_la_landing_page() {
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("client/#/dashboard"));
    }

    @Then("veo la Assign Leave de quick actions")
    public void veo_la_assign_leave_de_quick_actions() throws InterruptedException {
        boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_ASSIGN_LEAVE, driver);
        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_ASSIGN_LEAVE );
        driver.close();
    }

    @Then("veo la Leave List de quick actions")
    public void veo_la_leave_list_de_quick_actions() throws InterruptedException {
        boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_LEAVE_LIST, driver);
        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_LEAVE_LIST );
        driver.close();
    }

    @Then("veo la Leave Calendar de quick actions")
    public void veo_la_leave_calendar_de_quick_actions() throws InterruptedException {
        boolean quickActionFound = StepsHelper.searchQuickAction(Constants.QUICK_ACTION_LEAVE_CALENDAR, driver);
        Assert.assertTrue(quickActionFound, "No se encontró la Quick Action: " + Constants.QUICK_ACTION_LEAVE_CALENDAR );
        driver.close();
    }

    int cantidadLeaveRequest = 0;

    @When("obtengo la cantidad de leave request to approve")
    public void obtengo_la_cantidad_de_leave_request_to_approve() throws InterruptedException {
        Thread.sleep(3000);
        WebElement cantidadLeaveRequestElement = driver.findElement(By.className("record-count"));
        String cantidad = cantidadLeaveRequestElement.getText();
        System.out.println(cantidad);

        String cant = cantidad.replace("(", "");
        cant = cant.replace(")", "");

        cantidadLeaveRequest = Integer.valueOf(cant);
        System.out.println("---> " + cantidadLeaveRequest);
    }

    @When("ingreso a la seccion de leave request")
    public void ingreso_a_la_seccion_de_leave_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("la cantidad de elementos a deplegarse debe coincidir")
    public void la_cantidad_de_elementos_a_deplegarse_debe_coincidir() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
