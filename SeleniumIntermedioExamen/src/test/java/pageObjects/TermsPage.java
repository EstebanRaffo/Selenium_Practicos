package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TermsPage {
    public WebDriver driver;

    public TermsPage(WebDriver remoteDriver){ this.driver = remoteDriver; }

    public String getDisfrutandoSpotify(){
        WebElement linkDisfrutandoSpotify = driver.findElement(By.xpath("//a[contains(text(),'Disfrutando Spotify')]"));
        return linkDisfrutandoSpotify.getText();
    }

    public String getPagos(){
        WebElement linkPagosCancelaciones = driver.findElement(By.xpath("//a[contains(text(),'Pagos, cancelaciones y periodo de reflexión')]"));
        return linkPagosCancelaciones.getText();
    }

    public String getUsoServicio(){
        WebElement linkUsoDeNuestroServicio = driver.findElement(By.xpath("//a[contains(text(),'Uso de nuestro servicio')]"));
        return linkUsoDeNuestroServicio.getText();
    }
}
