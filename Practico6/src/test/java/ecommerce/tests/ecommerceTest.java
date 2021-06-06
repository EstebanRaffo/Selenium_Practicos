package ecommerce.tests;

import com.github.javafaker.Faker;
import ecommerce.Constants;
import ecommerce.page_objects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

        /****************************** PersonalInformationPage ******************************************/
        PersonalInformationPage myPersonalInformationPage = accountPage.clickOnMyPersonalInformationIcon();

        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        Assert.assertEquals(myPersonalInformationPage.getGenderClassAttribute(), "checked");

        Thread.sleep(3000);
        Assert.assertEquals(myPersonalInformationPage.getFirstNameFieldValue(), fakeFirstName);
        Assert.assertEquals(myPersonalInformationPage.getLastNameFieldValue(), fakeLastName);
        Assert.assertEquals(myPersonalInformationPage.getEmailForm(), emailString);

        System.out.println("Birthdate: " + myPersonalInformationPage.getBirthdate());
        System.out.println("Birthmonth: " + myPersonalInformationPage.getBirthmonth());
        System.out.println("Birthyear: " + myPersonalInformationPage.getBirthyear());

        Assert.assertTrue(myPersonalInformationPage.getBirthdate().contains(Constants.BIRTH_DAY));
        Assert.assertTrue(myPersonalInformationPage.getBirthmonth().contains(Constants.BIRTH_MONTH));
        Assert.assertTrue(myPersonalInformationPage.getBirthyear().contains(Constants.BIRTH_YEAR));

        //assertions for the address information
        myPersonalInformationPage.navigateToAccountInformation();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));

        myPersonalInformationPage.goToAddressSection();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));

        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("MYALIAS"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(fakeCompany));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.FIRST_ADDRESS));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.SECOND_ADDRESS));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains(Constants.CITY));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("Alaska"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("10000"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("United States"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("123456"));
        Assert.assertTrue(myPersonalInformationPage.getAddressInformation().contains("123456789"));
    }

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        accountPage.clickOnLogoutBtn();
        accountPage.clickOnLoginBtn();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAndSend(emailString);

        Thread.sleep(3000);
        String error_message_got = loginPage.getErrorMessage();

        Assert.assertEquals(error_message_got, "An account using this email address has already been registered. Please enter a valid password or request a new one.");
        Assert.assertTrue(error_message_got.contains("Please enter a valid password or request a new one."));
    }

    @Test
    public void loginTest(){
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        AccountPage accountPage = registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        // logout
        accountPage.clickOnLogoutBtn();
        // login
        accountPage.clickOnLoginBtn();

        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPageAfterLogin = loginPage.login(emailString, Constants.PASSWORD);

        String welcome_message_got = accountPageAfterLogin.getWelcomeMsg();
        Assert.assertTrue(welcome_message_got.contains(Constants.WELCOME_MESSAGE_EXPECTED));
    }

}
