package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ejerciciosSteps {
    public WebDriver driver;
    private Integer suma, resta, producto;
    private String title;

    @Given("una persona")
    public void una_persona(){
        System.out.println("Persona A");
    }

    @When("se encuentra con otra")
    public void se_encuentra_con_otra(){
        System.out.println("se encuentra con Persona B");
    }

    @Then("la saluda")
    public void la_saluda(){
        System.out.println("Persona A dice Hola a Persona B");
    }

    @Given("estoy en google")
    public void estoy_en_google(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @When("busco {string}")
    public void busco(String palabra){
        driver.findElement(By.name("q")).sendKeys(palabra);
        driver.findElement(By.xpath("(//input[@value='Buscar con Google'])[2]")).click();
    }

    @Then("se encuentra {string} en el titulo")
    public void se_encuentra_en_el_titulo(String palabra){
        Assert.assertTrue(driver.getTitle().contains(palabra));
    }

    @Given("inicio calculadora")
    public void inicio_calculadora() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Inicio calculadora");
    }

    @When("ingreso {int} y {int}")
    public void ingreso_y(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Se ingresaron los números " + int1 + " y " + int2);
        suma = int1 + int2;
        resta = int1 - int2;
        producto = int1 * int2;
    }

    @Then("muestro la suma")
    public void muestro_la_suma() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("La suma es: " + suma);
    }

    @Then("muestro la resta")
    public void muestro_la_resta() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("La resta es: " + resta);
    }

    @Then("muestro el producto")
    public void muestro_el_producto() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("El producto es: " + producto);
    }

    @Given("ingreso en registracion de spotify")
    public void ingreso_en_registracion_de_spotify() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com/uy/signup/");
        // driver.findElement(By.xpath("//*[@href='https://www.spotify.com/uy/signup/']")).click();
    }

    @When("completo email con {string}")
    public void completo_email_con(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("email")).sendKeys(string);
    }

    @When("confirmo email con {string}")
    public void confirmo_email_con(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("confirm")).sendKeys(string);
        driver.findElement(By.id("password")).click();
    }

    @Then("valido el mensaje de error confirmacion de email")
    public void valido_el_mensaje_de_error_confirmacion_de_email() {
        // Write code here that turns the phrase above into concrete actions
        WebElement message_error = driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo electrónico no coinciden')]"));
        Assert.assertEquals(message_error.getText(), "Las direcciones de correo electrónico no coinciden.");
        System.out.println(message_error.getText());
    }

    @Given("ingreso en Spotify")
    public void ingreso_en_spotify() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
    }

    @When("consulto el titulo")
    public void consulto_el_titulo() {
        // Write code here that turns the phrase above into concrete actions
        title = driver.getTitle();
    }

    @Then("valido el titulo")
    public void valido_el_titulo() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(title.contains("Spotify"));
        System.out.println(title);
    }

    @When("dejo email vacio")
    public void dejo_email_vacio() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("confirm")).click();
    }

    @Then("valido mensaje de error de email obligatorio")
    public void valido_mensaje_de_error_de_email_obligatorio() {
        // Write code here that turns the phrase above into concrete actions
        WebElement message_error = driver.findElement(By.xpath("//span[contains(text(), 'Es necesario que introduzcas tu correo electrónico.')]"));
        Assert.assertEquals(message_error.getText(), "Es necesario que introduzcas tu correo electrónico.");
        System.out.println(message_error.getText());
    }

    @When("dejo contraseña vacia")
    public void dejo_contraseña_vacia() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("displayname")).click();
    }

    @Then("valido mensaje de error de contraseña vacia")
    public void valido_mensaje_de_error_de_contraseña_vacia() {
        // Write code here that turns the phrase above into concrete actions
        WebElement message_error = driver.findElement(By.xpath("//div[contains(text(), 'Debes introducir una contraseña.')]"));
        Assert.assertEquals(message_error.getText(), "Debes introducir una contraseña.");
        System.out.println(message_error.getText());
    }
}
