package ecommerce.tests;

import ecommerce.utilities.Constants;
import ecommerce.page_objects.AccountPage;
import ecommerce.page_objects.LoginPage;
import ecommerce.utilities.FakeDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void loginTest(){

        String emailString = FakeDataGenerator.getRandomEmail();
        String fakeFirstName = FakeDataGenerator.getFakeFirstName();
        String fakeLastName = FakeDataGenerator.getFakeLastName();
        String fakeCompany = FakeDataGenerator.getFakeCompany();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        // logout
        accountPage.clickOnLogoutBtn();
        // login
        accountPage.clickOnLoginBtn();

        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPageAfterLogin = loginPage.login(emailString, Constants.PASSWORD);

        String welcome_message_expected = "Welcome to your account";
        String welcome_message_got = accountPageAfterLogin.getWelcomeMsg();
        Assert.assertTrue(welcome_message_got.contains(welcome_message_expected));
    }
}
