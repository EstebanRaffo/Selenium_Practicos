package ecommerce.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    public LandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "login")
    public WebElement login_button;

    public AuthPage clickOnLoginButton(){
        //waiting for the SignIn button to be clickable, otherwise it will fail
        wait.until(ExpectedConditions.elementToBeClickable(login_button));

        //clicking the SignIn button
        login_button.click();

        AuthPage authPage = new AuthPage(driver);
        return authPage;
    }
}
