package Practico3;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
    }

    @Test
    public void registrationTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        //flow to validate the correct information

        //waiting to see the account page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        //clicking on the personal information button in order to validate the information inserted previously
        WebElement userInformationButton = driver.findElement(By.className("icon-user"));
        userInformationButton.click();
        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        Assert.assertEquals(driver.findElement(By.cssSelector("#uniform-id_gender1 span")).getAttribute("class"), "checked");
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), fakeFirstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), fakeLastName);

        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailString);

        System.out.println("Birthdate: " + driver.findElement(By.cssSelector("#uniform-days span")).getText());
        System.out.println("birthmonth: " + driver.findElement(By.cssSelector("#uniform-months span")).getText());
        System.out.println("Birthyear: " + driver.findElement(By.cssSelector("#uniform-years span")).getText());

        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-days span")).getText().contains(Constants.BIRTH_DAY));
        //Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-months span")).getText().contains(Constants.BIRTH_MONTH));
        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-years span")).getText().contains(Constants.BIRTH_YEAR));

        //assertions for the address information
        driver.get("http://automationpractice.com/index.php?controller=my-account");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        WebElement addressInformationButton = driver.findElement(By.className("icon-building"));
        addressInformationButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("MYALIAS"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains(fakeCompany));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains(Constants.FIRST_ADDRESS));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains(Constants.SECOND_ADDRESS));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains(Constants.CITY));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("Alaska"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("10000"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("United States"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("123456"));
        Assert.assertTrue(driver.findElement(By.className("bloc_adresses")).getText().contains("123456789"));
    }

    //verifyUsernameIsUniqueOnRegistrationTest
    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']")).click();
        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']")).click();

        driver.findElement(By.id("email_create")).sendKeys(emailString);
        //click on the submit button
        driver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(3000);
        WebElement emailErrMsg = driver.findElement(By.xpath("//div[@id='create_account_error']/ol/li"));
        System.out.println(emailErrMsg.getText());

        Assert.assertEquals(emailErrMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
        Assert.assertTrue(emailErrMsg.getText().contains("Please enter a valid password or request a new one."));
    }

    @Test
    public void loginTest(){
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        //logout...
        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']")).click();
        //login...
        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']")).click();

        driver.findElement(By.id("email")).sendKeys(emailString);
        driver.findElement(By.id("passwd")).sendKeys(Constants.PASSWORD);
        driver.findElement(By.id("SubmitLogin")).click();

        String welcome_message = "Welcome to your account";
        WebElement p_welcome = driver.findElement(By.xpath("//p[contains(text(), 'Welcome to your account. Here you can manage all of your personal information and orders.')]"));
        Assert.assertTrue(p_welcome.getText().contains(welcome_message));
    }

    private void registerAnUser(String anEmail, String aFirstName, String aLastName, String aCompany){
        //going to the site
        String siteURL = "http://automationpractice.com/index.php";
        driver.get(siteURL);

        //waiting for the SignIn button to be clickable, otherwise it will fail
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("login"))));

        //clicking the SignIn button
        driver.findElement(By.className("login")).click();

        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        driver.findElement(By.id("email_create")).sendKeys(anEmail);
        //click on the submit button
        driver.findElement(By.id("SubmitCreate")).click();
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

        //clicking on register button
        registerButton.click();
    }


}
