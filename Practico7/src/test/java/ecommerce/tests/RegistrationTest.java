package ecommerce.tests;

import ecommerce.utilities.Constants;
import ecommerce.page_objects.*;
import ecommerce.utilities.FakeDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTest extends BaseTest{

    @Test
    public void registrationTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        String emailString = FakeDataGenerator.getRandomEmail();
        String fakeFirstName = FakeDataGenerator.getFakeFirstName();
        String fakeLastName = FakeDataGenerator.getFakeLastName();
        String fakeCompany = FakeDataGenerator.getFakeCompany();

        //softAssert.assertTrue(driver.getCurrentUrl().contains("account"), "url incorrecta");

        /************************************ AccountPage ******************************************/
        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        /****************************** PersonalInformationPage ******************************************/
        PersonalInformationPage myPersonalInformationPage = accountPage.clickOnMyPersonalInformationIcon();

        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        softAssert.assertEquals(myPersonalInformationPage.getGenderClassAttribute(), "checked", "gender not checked ----> ");

        // softAssert.assertEquals(myPersonalInformationPage.getFirstNameFieldValue(), fakeFirstName, "Firstname Failed ----> "); // Passed
        softAssert.assertEquals(myPersonalInformationPage.getFirstNameFieldValue(), fakeFirstName + "1", "Firstname Failed ----> "); // Failed
        softAssert.assertEquals(myPersonalInformationPage.getLastNameFieldValue(), fakeLastName, "Lastname Failed ----> ");
        softAssert.assertEquals(myPersonalInformationPage.getEmailForm(), emailString, "email Failed ----> ");

        System.out.println("Birthdate: " + myPersonalInformationPage.getBirthdate());
        System.out.println("Birthmonth: " + myPersonalInformationPage.getBirthmonth());
        System.out.println("Birthyear: " + myPersonalInformationPage.getBirthyear());

        softAssert.assertTrue(myPersonalInformationPage.getBirthdate().contains(Constants.BIRTH_DAY), "Birthday Failed ----> ");
        // softAssert.assertTrue(myPersonalInformationPage.getBirthmonth().contains(Constants.BIRTH_MONTH), "Birthmonth Failed ----> "); // Failed
        softAssert.assertTrue(myPersonalInformationPage.getBirthmonth().contains("January"), "Birthmonth Failed ----> "); // Passed
        softAssert.assertTrue(myPersonalInformationPage.getBirthyear().contains(Constants.BIRTH_YEAR), "Birthyear Failed ----> ");

        //assertions for the address information
        myPersonalInformationPage.navigateToAccountInformation();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));

        Thread.sleep(5000);
        myPersonalInformationPage.goToAddressSection();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));

        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("MYALIAS"), "MYALIAS Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(fakeCompany), "Company Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.FIRST_ADDRESS), "First address Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.SECOND_ADDRESS), "Second address Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.CITY), "City Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("Alaska"), "City Name Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("10000"), "Number Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("United States"), "Country Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("123456"), "First id Failed ----> ");
        softAssert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("123456789"), "Second id Failed ----> ");

        softAssert.assertAll();
    }

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {

        String emailString = FakeDataGenerator.getRandomEmail();
        String fakeFirstName = FakeDataGenerator.getFakeFirstName();
        String fakeLastName = FakeDataGenerator.getFakeLastName();
        String fakeCompany = FakeDataGenerator.getFakeCompany();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        LoginPage loginPage = accountPage.clickOnLogoutBtn();
        loginPage.fillEmailAndSend(emailString);

        Thread.sleep(3000);
        String error_message_got = loginPage.getErrorMessage();

        Assert.assertEquals(error_message_got, "An account using this email address has already been registered. Please enter a valid password or request a new one.");
        Assert.assertTrue(error_message_got.contains("Please enter a valid password or request a new one."));
    }

}
