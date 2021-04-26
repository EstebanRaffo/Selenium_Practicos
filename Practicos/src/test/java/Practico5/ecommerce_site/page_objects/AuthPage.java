package Practico5.ecommerce_site.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public AuthPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);  //para utilizar los @Find By...
    }

    public CreateAccountPage registerAnUser(String anEmail) throws InterruptedException {
        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        driver.findElement(By.id("email_create")).sendKeys(anEmail);
        //click on the submit button
        driver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(5000);
        Assert.assertEquals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation", driver.getCurrentUrl());

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        return createAccountPage;
    }





}
