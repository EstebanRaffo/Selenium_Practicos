package ecommerce.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalInformationPage extends BasePage{

    public PersonalInformationPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    // personal information
    @FindBy(id = "firstname")
    public WebElement firstname_input;

    @FindBy(id = "lastname")
    public WebElement lastname_input;

    @FindBy(id = "email")
    public WebElement email_form;

    @FindBy(className = "icon-building")
    public WebElement addressInformationButton;

    //@FindBy(linkText = "My addresses")
    //public WebElement addressInformationButton;

    @FindBy(className = "bloc_adresses")
    public WebElement bloc_adresses;

    public String getGenderClassAttribute(){
        return driver.findElement(By.cssSelector("#uniform-id_gender1 span")).getAttribute("class");
    }

    public String getFirstNameFieldValue(){
        return firstname_input.getAttribute("value");
    }

    public String getLastNameFieldValue(){
        return lastname_input.getAttribute("value");
    }

    public String getEmailForm(){
        return email_form.getAttribute("value");
    }

    public String getBirthdate(){
        return driver.findElement(By.cssSelector("#uniform-days span")).getText();
    }

    public String getBirthmonth(){ return driver.findElement(By.cssSelector("#uniform-months span")).getText(); }

    public String getBirthyear(){
        return driver.findElement(By.cssSelector("#uniform-years span")).getText();
    }

    public void navigateToAccountInformation(){
        driver.get("http://automationpractice.com/index.php?controller=my-account");
    }

    public void goToAddressSection(){
        //WebElement addressInformationButton = driver.findElement(By.className("icon-building"));
        addressInformationButton.click();
    }

    public String getAddressInformation(){
        return bloc_adresses.getText();
        //return driver.findElement(By.className("bloc_adresses")).getText();
    }
}
