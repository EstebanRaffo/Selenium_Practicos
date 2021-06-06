package ecommerce.page_objects;

import ecommerce.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public RegistrationPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    //locating all the form fields, this is only possible because the form with fields already loaded by this time so you have the locators working, helps to better reading
    @FindBy(id = "id_gender1")
    public WebElement maleRadioButton;

    @FindBy(id = "customer_firstname")
    public WebElement firstName;

    @FindBy(id = "customer_lastname")
    public WebElement lastName;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "days")
    public WebElement daySelector;

    @FindBy(id = "months")
    public WebElement monthSelector;

    @FindBy(id = "years")
    public WebElement yearSelector;

    @FindBy(id = "company")
    public WebElement company;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id = "address2")
    public WebElement address2;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "id_state")
    public WebElement stateSelector;

    @FindBy(id = "postcode")
    public WebElement postcode;

    @FindBy(id = "id_country")
    public WebElement countrySelector;

    @FindBy(id = "other")
    public WebElement additionalInfo;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "phone_mobile")
    public WebElement mobile;

    @FindBy(id = "alias")
    public WebElement addressAlias;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;

    public AccountPage fillingRegistrationFormAndSend(String aFirstName, String aLastName, String aCompany){
        //waiting for the form to load completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));

        //start interacting with the fields and sending the respective values
        maleRadioButton.click();
        firstName.sendKeys(aFirstName);
        lastName.sendKeys(aLastName);
        password.sendKeys(Constants.PASSWORD);
        Select selectDays = new Select(daySelector);
        selectDays.selectByValue(Constants.BIRTH_DAY);
        Select selectMonths = new Select(monthSelector);
        selectMonths.selectByValue(Constants.BIRTH_MONTH);
        Select selectYears = new Select(yearSelector);
        selectYears.selectByValue(Constants.BIRTH_YEAR);
        company.sendKeys(aCompany);
        address1.sendKeys(Constants.FIRST_ADDRESS);
        address2.sendKeys(Constants.SECOND_ADDRESS);
        city.sendKeys(Constants.CITY);

        Select selectState = new Select(stateSelector);
        selectState.selectByValue("2");
        postcode.sendKeys("10000");
        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByValue("21");
        additionalInfo.sendKeys("Additional information");
        phone.sendKeys("123456");
        mobile.sendKeys("123456789");
        addressAlias.clear();
        addressAlias.sendKeys("MyAlias");

        //clicking on register button
        registerButton.click();

        AccountPage accountPage = new AccountPage(driver);
        return accountPage;
    }
}
