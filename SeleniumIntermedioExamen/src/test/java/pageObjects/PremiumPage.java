package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PremiumPage {
    public WebDriver driver;

    public PremiumPage(WebDriver remoteDriver){ this.driver = remoteDriver; }

    public String getPlanIndividual(){
        WebElement planIndividual = driver.findElement(By.xpath("//h3[contains(text(),'Individual')]"));
        return planIndividual.getText();
    }

    public String getPlanDuo(){
        WebElement planDuo = driver.findElement(By.xpath("//h3[contains(text(),'Duo')]"));
        return planDuo.getText();
    }

    public String getPlanFamiliar(){
        WebElement planFamiliar = driver.findElement(By.xpath("//h3[contains(text(),'Familiar')]"));
        return planFamiliar.getText();
    }
}
