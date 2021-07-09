package Intermedio.ecommerce_site.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasePage{

    public AuthPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);  //para utilizar los @Find By...
    }

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement loginBtn;

    @FindBy(id="email_create")
    public WebElement emailField;

    @FindBy (id="SubmitCreate")
    public WebElement createAccountBtn;

    @FindBy (id="email")
    public WebElement emailLoginField;

    @FindBy (id="passwd")
    public WebElement passwdLoginField;

    @FindBy (xpath = "//div[@id='create_account_error']/ol/li")
    public WebElement emailErrMsg;

    @FindBy(id="SubmitLogin")
    public WebElement loginAccountBtn;

    public CreateAccountPage registerAnUser(String anEmail) throws InterruptedException {
        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        driver.findElement(By.id("email_create")).sendKeys(anEmail);
        //click on the submit button
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(15000);
        Assert.assertEquals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation", driver.getCurrentUrl());

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        return createAccountPage;
    }

    public void clickOnLoginBtn(){
        loginBtn.click();
    }

    public void fillEmailField(String email){
        emailField.sendKeys(email);
    }

    public void clickOnCreateAccountBtn() {
        createAccountBtn.click();
    }

    public String getRegistrationErrorMsg(){
        return emailErrMsg.getText();
    }

    public void loginWithEmailAndPassword(String email, String password){
        emailLoginField.sendKeys(email);
        passwdLoginField.sendKeys(password);
        loginAccountBtn.click();
    }


}
