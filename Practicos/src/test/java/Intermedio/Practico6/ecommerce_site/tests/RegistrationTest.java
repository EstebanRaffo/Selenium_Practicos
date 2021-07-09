package Intermedio.Practico6.ecommerce_site.tests;

import com.github.javafaker.Faker;
import Intermedio.Practico6.ecommerce_site.Constants;
import Intermedio.Practico6.ecommerce_site.page_objects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {

    String URL = "http://automationpractice.com/index.php";
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @Test
    public void registrationTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        LandingPage landingPage = new LandingPage(driver);
        // al hacer click en el login btn, cambiamos de pagina...
        // ahora estaremos en la AuthPage...
        AuthPage authPage = landingPage.clickOnLoginBtn();

        CreateAccountPage createAccountPage = authPage.registerAnUser(emailString);
        createAccountPage.fillingPersonaInformationFields(fakeFirstName, fakeLastName, fakeCompany);
        MyAccountPage myAccountPage = createAccountPage.clickOnRegisterBtn();

        MyPersonalInformationPage myPersonalInformationPage = myAccountPage.clickOnMyPersonalInformationIcon();

        //flow to validate the correct information

        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        Assert.assertEquals(myPersonalInformationPage.getGenderClassAttribute(), "checked");

        Assert.assertEquals(myPersonalInformationPage.getFirstNameFieldValue(), fakeFirstName);
        Assert.assertEquals(myPersonalInformationPage.getLastNameFieldValue(), fakeLastName);

        /*Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailString);
        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-days span")).getText().contains(Constants.BIRTH_DAY));
        //org.testng.Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-months span")).getText().contains(Constants.BIRTH_MONTH));
        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-years span")).getText().contains(Constants.BIRTH_YEAR));
        */
        //assertions for the address information
        myPersonalInformationPage.goToMyAccountInformation();

        //TODO
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        myPersonalInformationPage.goToAddressSection();

        //TODO
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));

        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("MYALIAS"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(fakeCompany));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.FIRST_ADDRESS));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.SECOND_ADDRESS));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.CITY));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("Mexico"), "Se esperaba que se encuentre el pais 'Mexico' dentro de la direccion.");

    }

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        LandingPage landingPage = new LandingPage(driver);
        // al hacer click en el login btn, cambiamos de pagina...
        // ahora estaremos en la AuthPage...
        AuthPage authPage = landingPage.clickOnLoginBtn();

        CreateAccountPage createAccountPage = authPage.registerAnUser(emailString);
        createAccountPage.fillingPersonaInformationFields(fakeFirstName, fakeLastName, fakeCompany);
        MyAccountPage myAccountPage = createAccountPage.clickOnRegisterBtn();

        authPage = myAccountPage.clickOnLogoutBtn();
        authPage.clickOnLoginBtn();

        authPage.fillEmailField(emailString);
        //click on the submit button
        authPage.clickOnCreateAccountBtn();

        Thread.sleep(3000);

        Assert.assertEquals(authPage.getRegistrationErrorMsg(), Constants.EMAIL_ALREADY_REGISTERED_ERROR_MSG);
        Assert.assertTrue(authPage.getRegistrationErrorMsg().contains(Constants.INVALID_PASSWORD_ERROR_MSG));
    }

}
