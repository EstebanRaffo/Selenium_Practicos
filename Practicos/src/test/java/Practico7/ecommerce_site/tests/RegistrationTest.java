package Practico7.ecommerce_site.tests;

import com.github.javafaker.Faker;
import Practico7.ecommerce_site.utilities.Constants;
import Practico7.ecommerce_site.page_objects.*;
import Practico7.ecommerce_site.utilities.FakeDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTest extends BaseTest{

    @Test
    public void registrationTest() throws InterruptedException {
        SoftAssert softAssertTesting = new SoftAssert();

        String emailString = FakeDataGenerator.getRandomEmail();
        String fakeFirstName = FakeDataGenerator.getFakeFirstName();
        String fakeLastName = FakeDataGenerator.getFakeLastName();
        String fakeCompany = FakeDataGenerator.getFakeCompanyName();

        softAssertTesting.assertTrue(driver.getCurrentUrl().contains("account"));

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
        softAssertTesting.assertEquals(myPersonalInformationPage.getGenderClassAttribute(), "checked");

        softAssertTesting.assertEquals(myPersonalInformationPage.getFirstNameFieldValue(), fakeFirstName);
        softAssertTesting.assertEquals(myPersonalInformationPage.getLastNameFieldValue(), fakeLastName);

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

        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains("MYALIAS"));
        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains(fakeCompany));
        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.FIRST_ADDRESS));
        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.SECOND_ADDRESS));
        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.CITY));
        softAssertTesting.assertTrue(myPersonalInformationPage.getAddressInformation().contains("Mexico"), "Se esperaba que se encuentre el pais 'Mexico' dentro de la direccion.");

        softAssertTesting.assertAll();

    }

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = FakeDataGenerator.getRandomEmail();
        String fakeFirstName = FakeDataGenerator.getFakeFirstName();
        String fakeLastName = FakeDataGenerator.getFakeLastName();
        String fakeCompany = FakeDataGenerator.getFakeCompanyName();

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
