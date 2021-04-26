package ecommerce.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasePage{

    public AuthPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    public WebElement email_input;

    @FindBy(id = "SubmitCreate")
    public WebElement create_button;

    public RegistrationPage registerUser(String anEmail){
        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        email_input.sendKeys(anEmail);
        //click on the submit button
        create_button.click();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        return registrationPage;
    }
}
