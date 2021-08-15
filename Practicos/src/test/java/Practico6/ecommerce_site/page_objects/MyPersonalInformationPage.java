package Practico6.ecommerce_site.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPersonalInformationPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public MyPersonalInformationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);  //para utilizar los @Find By...
    }

    public String getGenderClassAttribute(){
        return driver.findElement(By.cssSelector("#uniform-id_gender1 span")).getAttribute("class");
    }

    public String getFirstNameFieldValue (){
        return driver.findElement(By.id("firstname")).getAttribute("value");
    }

    public String getLastNameFieldValue(){
        return driver.findElement(By.id("lastname")).getAttribute("value");
    }

    public void goToMyAccountInformation(){
        driver.get("http://automationpractice.com/index.php?controller=my-account");
    }

    public void goToAddressSection(){
        WebElement addressInformationButton = driver.findElement(By.className("icon-building"));
        addressInformationButton.click();
    }

    public String getAddressInformation(){
        return driver.findElement(By.className("bloc_adresses")).getText();
    }



}
