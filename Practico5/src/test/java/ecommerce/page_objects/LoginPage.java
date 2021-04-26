package ecommerce.page_objects;

import ecommerce.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public LoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    public WebElement email_input;

    @FindBy(id = "SubmitCreate")
    public WebElement create_button;

    @FindBy(xpath = "//div[@id='create_account_error']/ol/li")
    public WebElement emailErrMsg;

    public void validateEmail(String emailString) throws InterruptedException {

        email_input.sendKeys(emailString);

        //click on the submit button
        create_button.click();

        Thread.sleep(3000);

        System.out.println(emailErrMsg.getText());

        Assert.assertEquals(emailErrMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
        Assert.assertTrue(emailErrMsg.getText().contains("Please enter a valid password or request a new one."));
    }

    @FindBy(id = "email")
    public WebElement email_form;

    @FindBy(id = "SubmitLogin")
    public WebElement submitLogin;

    @FindBy(id = "passwd")
    public WebElement password;

    public void validateLogin(String emailString){
        email_form.sendKeys(emailString);
        password.sendKeys(Constants.PASSWORD);
        submitLogin.click();

        String welcome_message = "Welcome to your account";
        WebElement p_welcome = driver.findElement(By.xpath("//p[contains(text(), 'Welcome to your account. Here you can manage all of your personal information and orders.')]"));
        Assert.assertTrue(p_welcome.getText().contains(welcome_message));
    }
}
