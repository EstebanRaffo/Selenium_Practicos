package ecommerce.tests;

import ecommerce.page_objects.AccountPage;
import ecommerce.page_objects.AuthPage;
import ecommerce.page_objects.LandingPage;
import ecommerce.page_objects.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
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


    public AccountPage registerAnUser(String anEmail, String aFirstName, String aLastName, String aCompany){

        /************************************* LandingPage ****************************************/
        LandingPage landingPage = new LandingPage(driver);

        /************************************** AuthPage ***********************************************/
        AuthPage authPage = landingPage.clickOnLoginButton();

        /************************************ RegistrationPage ******************************************/
        RegistrationPage registrationPage = authPage.registerUser(anEmail);
        AccountPage accountPage = registrationPage.fillingRegistrationFormAndSend(aFirstName, aLastName, aCompany);
        return accountPage;
    }

    @AfterMethod
    public void tearDown(){
        //driver.close();
    }

}
