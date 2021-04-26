package Practico7.ecommerce_site.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logoutBtn;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement loginBtn;

    @FindBy(id="email_create")
    public WebElement emailField;

    @FindBy (id="email")
    public WebElement emailLoginField;

    @FindBy (id="passwd")
    public WebElement passwdLoginField;

    @FindBy (id="SubmitCreate")
    public WebElement createAccountBtn;

    @FindBy(id="SubmitLogin")
    public WebElement loginAccountBtn;

    @FindBy (xpath = "//div[@id='create_account_error']/ol/li")
    public WebElement emailErrMsg;


    public LandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public AuthPage clickOnLoginBtn(){
        //waiting for the SignIn button to be clickable, otherwise it will fail
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("login"))));

        //clicking the SignIn button
        loginBtn.click();

        AuthPage authPage = new AuthPage(driver);
        return authPage;
    }



}
