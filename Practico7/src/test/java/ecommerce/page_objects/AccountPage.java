package ecommerce.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccountPage extends BasePage{

    public AccountPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "icon-user")
    public WebElement userInformationButton;

    public PersonalInformationPage clickOnMyPersonalInformationIcon(){
        //flow to validate the correct information
        //waiting to see the account page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));

        //clicking on the personal information button in order to validate the information inserted previously
        userInformationButton.click();

        PersonalInformationPage personalInformationPage = new PersonalInformationPage(driver);
        return personalInformationPage;
    }

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logout_button;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement myAccount;

    public void clickOnLogoutBtn(){
        logout_button.click();
    }

    public void clickOnLoginBtn() { myAccount.click(); }

    public String getWelcomeMsg(){
        WebElement p_message = driver.findElement(By.xpath("//p[contains(text(), 'Welcome to your account. Here you can manage all of your personal information and orders.')]"));
        return p_message.getText();
    }

}
