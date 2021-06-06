package ecommerce;

import com.github.javafaker.Faker;
import ecommerce.page_objects.AccountPage;
import ecommerce.page_objects.AuthPage;
import ecommerce.page_objects.LandingPage;
import ecommerce.page_objects.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ecommerceTest {

    String URL = "http://automationpractice.com/index.php";
    public WebDriver driver;
    public WebDriverWait wait;

    public static String email = "seleniumcurso" + Math.random() + "@hotmail.com";

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }


    private AccountPage registerAnUser(String anEmail, String aFirstName, String aLastName, String aCompany){

        /************************************* LandingPage ****************************************/
        LandingPage landingPage = new LandingPage(driver);

        /************************************** AuthPage ***********************************************/
        AuthPage authPage = landingPage.clickOnLoginButton();

        /************************************ RegistrationPage ******************************************/
        RegistrationPage registrationPage = authPage.registerUser(anEmail);
        AccountPage accountPage = registrationPage.fillingRegistrationFormAndSend(aFirstName, aLastName, aCompany);
        return accountPage;
    }


    @Test
    public void registrationTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        /************************************ AccountPage ******************************************/
        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);
        accountPage.validateUserInformation(fakeFirstName, fakeLastName, fakeCompany, emailString);

    }

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);
        accountPage.validateUserEmail(emailString);
    }

    @Test
    public void loginTest(){
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);
        accountPage.validateLogin(emailString);
    }

}
