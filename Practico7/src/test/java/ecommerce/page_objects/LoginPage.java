package ecommerce.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{

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

    public String getErrorMessage(){
        return emailErrMsg.getText();
    }

    public void fillEmailAndSend(String emailString){
        email_input.sendKeys(emailString);
        //click on the submit button
        create_button.click();
    }

    @FindBy(id = "email")
    public WebElement email_form;

    @FindBy(id = "SubmitLogin")
    public WebElement submitLogin;

    @FindBy(id = "passwd")
    public WebElement password;

    public AccountPage login(String emailString, String pass){
        email_form.sendKeys(emailString);
        password.sendKeys(pass);
        submitLogin.click();

        AccountPage accountPage = new AccountPage(driver);
        return accountPage;
    }
}
