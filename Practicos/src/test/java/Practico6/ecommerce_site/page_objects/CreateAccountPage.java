package Practico6.ecommerce_site.page_objects;

import Practico6.ecommerce_site.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public CreateAccountPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
    }

    public void fillingPersonaInformationFields(String aFirstName, String aLastName, String aCompany){
        //waiting for the form to load completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));

        //locating all the form fields, this is only possible because the form with fields already loaded by this time so you have the locators working, helps to better reading
        WebElement maleRadioButton = driver.findElement(By.id("id_gender1"));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement daySelector = driver.findElement(By.id("days"));
        WebElement monthSelector = driver.findElement(By.id("months"));
        WebElement yearSelector = driver.findElement(By.id("years"));
        WebElement company = driver.findElement(By.id("company"));
        WebElement address1 = driver.findElement(By.id("address1"));
        WebElement address2 = driver.findElement(By.id("address2"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement stateSelector = driver.findElement(By.id("id_state"));
        WebElement postcode = driver.findElement(By.id("postcode"));
        WebElement countrySelector = driver.findElement(By.id("id_country"));
        WebElement additionalInfo = driver.findElement(By.id("other"));
        WebElement phone = driver.findElement(By.id("phone"));
        WebElement mobile = driver.findElement(By.id("phone_mobile"));
        WebElement addressAlias = driver.findElement(By.id("alias"));
        WebElement registerButton = driver.findElement(By.id("submitAccount"));

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



    }


    public MyAccountPage clickOnRegisterBtn(){
        WebElement registerButton = driver.findElement(By.id("submitAccount"));
        registerButton.click();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }


}
