package ecommerce.page_objects;

import ecommerce.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AccountPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public AccountPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "icon-user")
    public WebElement userInformationButton;

    // personal information
    @FindBy(id = "firstname")
    public WebElement firstname_input;

    @FindBy(id = "lastname")
    public WebElement lastname_input;

    @FindBy(id = "email")
    public WebElement email_form;

    @FindBy(className = "icon-building")
    public WebElement addressInformationButton;

    @FindBy(className = "bloc_adresses")
    public WebElement bloc_adresses;

    public void validateUserInformation(String fakeFirstName, String fakeLastName, String fakeCompany, String emailString){
        //flow to validate the correct information
        //waiting to see the account page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));

        //clicking on the personal information button in order to validate the information inserted previously
        userInformationButton.click();

        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        Assert.assertEquals(driver.findElement(By.cssSelector("#uniform-id_gender1 span")).getAttribute("class"), "checked");

        Assert.assertEquals(firstname_input.getAttribute("value"), fakeFirstName);
        Assert.assertEquals(lastname_input.getAttribute("value"), fakeLastName);
        Assert.assertEquals(email_form.getAttribute("value"), emailString);

        System.out.println("Birthdate: " + driver.findElement(By.cssSelector("#uniform-days span")).getText());
        System.out.println("birthmonth: " + driver.findElement(By.cssSelector("#uniform-months span")).getText());
        System.out.println("Birthyear: " + driver.findElement(By.cssSelector("#uniform-years span")).getText());

        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-days span")).getText().contains(Constants.BIRTH_DAY));
        //Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-months span")).getText().contains(Constants.BIRTH_MONTH));
        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-years span")).getText().contains(Constants.BIRTH_YEAR));

        //assertions for the address information
        driver.get("http://automationpractice.com/index.php?controller=my-account");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        addressInformationButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));

        Assert.assertTrue(bloc_adresses.getText().contains("MYALIAS"));
        Assert.assertTrue(bloc_adresses.getText().contains(fakeCompany));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.FIRST_ADDRESS));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.SECOND_ADDRESS));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.CITY));
        Assert.assertTrue(bloc_adresses.getText().contains("Alaska"));
        Assert.assertTrue(bloc_adresses.getText().contains("10000"));
        Assert.assertTrue(bloc_adresses.getText().contains("United States"));
        Assert.assertTrue(bloc_adresses.getText().contains("123456"));
        Assert.assertTrue(bloc_adresses.getText().contains("123456789"));
    }


    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logout_button;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement myAccount;

    public void validateUserEmail(String emailString) throws InterruptedException {
        logout_button.click();
        myAccount.click();

        /************************************ LoginPage ******************************************/
        LoginPage loginPage = new LoginPage(driver);
        loginPage.validateEmail(emailString);
    }


    public void validateLogin(String emailString){
        //logout...
        logout_button.click();
        //login...
        myAccount.click();

        /************************************ LoginPage ******************************************/
        LoginPage loginPage = new LoginPage(driver);
        loginPage.validateLogin(emailString);
    }
}
