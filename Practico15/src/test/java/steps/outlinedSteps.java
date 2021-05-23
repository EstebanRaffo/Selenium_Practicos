package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import java.util.List;

public class outlinedSteps {
    private Integer resultado, monto_inicial, saldo;
    public WebDriver driver;

    @Given("tengo una calculadora")
    public void tengo_una_calculadora() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Iniciar Calculadora");
    }

    @When("ingreso {int} y {int}")
    public void ingreso_y(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        resultado = int1 + int2;
    }

    @Then("la suma es {int}")
    public void la_suma_es(Integer suma) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(resultado, suma);
    }

    /***********************************************************************************************************/

    @Given("monto inicial de {int}")
    public void monto_inicial_de(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        monto_inicial = int1;
    }

    @When("gasto de {int}")
    public void gasto_de(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        saldo = monto_inicial - int1;
    }

    @Then("el saldo restante es {int}")
    public void el_saldo_restante_es(Integer saldo_resultado) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(saldo_resultado, saldo);
    }

    /***********************************************************************************************************/

    @Given("estoy logeado en el sitio web")
    public void estoy_logeado_en_el_sitio_web() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("estoy en la pagina principal")
    public void estoy_en_la_pagina_principal() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Marcus")
    public void busco_el_usuario_marcus() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje No se encuentra en el sistema")
    public void obtengo_el_mensaje_no_se_encuentra_en_el_sistema() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Rox123")
    public void busco_el_usuario_rox123() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje Nombre de usuario invalido")
    public void obtengo_el_mensaje_nombre_de_usuario_invalido() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("busco el usuario Matt")
    public void busco_el_usuario_matt() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("obtengo el mensaje Nombre de usuario inactivo")
    public void obtengo_el_mensaje_nombre_de_usuario_inactivo() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /***********************************************************************************************************/

    @Given("estoy en el sitio Orange")
    public void estoy_en_el_sitio_orange() {
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/");
        driver.manage().window().maximize();
    }

    @When("me logueo")
    public void me_logueo() {
        driver.findElement(By.id("btnLogin")).click();
    }

    @When("valido que estoy en la landing page")
    public void valido_que_estoy_en_la_landing_page() {
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("client/#/dashboard"));
    }

    @Then("valido que aparece Assign Leave en Quick Access")
    public void valido_que_aparece_assign_leave_en_quick_access() throws InterruptedException {
        boolean quick_access_found = StepsHelper.searchQuickAccess(driver, Constants.QUICK_ACCESS_ASSIGN_LEAVE);
        Assert.assertTrue(quick_access_found, "No se encontró el Quick Access: " + Constants.QUICK_ACCESS_ASSIGN_LEAVE);
        driver.close();
    }

    @Then("valido que aparece Leave List en Quick Access")
    public void valido_que_aparece_leave_list_en_quick_access() throws InterruptedException {
        boolean quick_access_found = StepsHelper.searchQuickAccess(driver, Constants.QUICK_ACCESS_LEAVE_LIST);
        Assert.assertTrue(quick_access_found, "No se encontró el Quick Access: " + Constants.QUICK_ACCESS_LEAVE_LIST);
        driver.close();
    }

    @Then("valido que aparece Leave Calendar en Quick Access")
    public void valido_que_aparece_leave_calendar_en_quick_access() throws InterruptedException {
        boolean quick_access_found = StepsHelper.searchQuickAccess(driver, Constants.QUICK_ACCESS_LEAVE_CALENDAR);
        Assert.assertTrue(quick_access_found, "No se encontró el Quick Access: " + Constants.QUICK_ACCESS_LEAVE_CALENDAR);
        driver.close();
    }

    /***********************************************************************************************************/

    int cantidad_leave_request_informada = 0;

    @When("obtengo la cantidad de leave request to approve")
    public void obtengo_la_cantidad_de_leave_request_to_approve() throws InterruptedException {
        Thread.sleep(3000);
        String quantity = driver.findElement(By.className("record-count")).getText();

        String quantity_correct = quantity.replace("(", "");
        quantity_correct = quantity_correct.replace(")", "");

        cantidad_leave_request_informada = Integer.valueOf(quantity_correct);
        System.out.println("Cantidad en Leave request to approve: " + cantidad_leave_request_informada);
    }

    @When("ingreso a la seccion de leave request")
    public void ingreso_a_la_seccion_de_leave_request() {
        driver.findElement(By.xpath("//span[contains(text(), 'Leave Requests to Approve')]")).click();
    }

    int cantidad_dentro_de_leave_request = 0;
    @Then("valido que coincide la cantidad de elementos")
    public void valido_que_coincide_la_cantidad_de_elementos() throws InterruptedException {
        Thread.sleep(3000);
        WebElement page_data = driver.findElement(By.className("summary"));
        String[] array_items = page_data.getText().split("of");
        String cantidad_paginado = array_items[1].replace(" ", "");
        cantidad_dentro_de_leave_request = Integer.valueOf(cantidad_paginado);

        System.out.println("cantidad obtenida: " + cantidad_dentro_de_leave_request);
        Assert.assertEquals(cantidad_dentro_de_leave_request, cantidad_leave_request_informada);
        driver.close();
    }
}
