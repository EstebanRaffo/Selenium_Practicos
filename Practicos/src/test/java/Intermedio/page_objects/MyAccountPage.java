package Intermedio.ecommerce_site.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {

    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logoutBtn;

    public MyAccountPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);  //para utilizar los @Find By...
    }

    public MyPersonalInformationPage clickOnMyPersonalInformationIcon(){
        //waiting to see the account page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        //clicking on the personal information button in order to validate the information inserted previously
        WebElement userInformationButton = driver.findElement(By.className("icon-user"));
        userInformationButton.click();

        MyPersonalInformationPage myPersonalInformationPage = new MyPersonalInformationPage(driver);
        return myPersonalInformationPage;
    }


    public AuthPage clickOnLogoutBtn(){
        logoutBtn.click();

        AuthPage authPage = new AuthPage(driver);
        return authPage;
    }

}
