package Intermedio.ecommerce_site.tests;

import Intermedio.ecommerce_site.utilities.Constants;
import Intermedio.ecommerce_site.page_objects.AuthPage;
import Intermedio.ecommerce_site.page_objects.CreateAccountPage;
import Intermedio.ecommerce_site.page_objects.LandingPage;
import Intermedio.ecommerce_site.page_objects.MyAccountPage;
import Intermedio.ecommerce_site.utilities.FakeDataGenerator;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest{

    @Test
    public void loginTest() throws InterruptedException{

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

        Thread.sleep(2000);
        authPage = myAccountPage.clickOnLogoutBtn();
        authPage.loginWithEmailAndPassword(emailString, Constants.PASSWORD);

        //add asserts...!!
    }

}
