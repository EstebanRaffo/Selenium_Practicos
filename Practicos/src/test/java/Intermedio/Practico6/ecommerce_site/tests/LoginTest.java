package Intermedio.Practico6.ecommerce_site.tests;

import com.github.javafaker.Faker;
import Intermedio.Practico6.ecommerce_site.Constants;
import Intermedio.Practico6.ecommerce_site.page_objects.AuthPage;
import Intermedio.Practico6.ecommerce_site.page_objects.CreateAccountPage;
import Intermedio.Practico6.ecommerce_site.page_objects.LandingPage;
import Intermedio.Practico6.ecommerce_site.page_objects.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

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
    public void loginTest() throws InterruptedException{
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
        authPage.loginWithEmailAndPassword(emailString, Constants.PASSWORD);

        //add asserts...!!
    }

}
