package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    public WebDriver driver;

    public RegistrationPage(WebDriver remoteDriver){ this.driver = remoteDriver; }

    public void fillingEmail(String value){
        WebElement inputEmail = driver.findElement(By.id("email"));
        inputEmail.sendKeys(value + Keys.TAB);
    }

    public String getErrorEmail(){
        WebElement spanError = driver.findElement(By.xpath("//span[contains(text(),'Es necesario que introduzcas tu correo electrónico')]"));
        return spanError.getText();
    }

    public String getInvalidEmail(){
        WebElement spanError = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com')]"));
        return spanError.getText();
    }

    public String getEmailRegistered(){
        WebElement spanError = driver.findElement(By.xpath("//span[contains(text(),'Este correo electrónico ya está conectado a una cuenta.')]"));
        return spanError.getText();
    }

    public String getLinkIniciarSesion(){
        WebElement linkIniciaSesion = driver.findElement(By.xpath("//a[contains(text(),'Inicia sesión')]"));
        return linkIniciaSesion.getText();
    }

}
