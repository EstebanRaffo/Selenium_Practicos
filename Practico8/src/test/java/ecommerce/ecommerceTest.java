package ecommerce;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(className = "login")
    public WebElement login_button;

    @FindBy(tagName = "h1")
    public WebElement h1_authentication;

    @FindBy(id = "email_create")
    public WebElement email_input;

    @FindBy(id = "SubmitCreate")
    public WebElement create_button;

    //locating all the form fields, this is only possible because the form with fields already loaded by this time so you have the locators working, helps to better reading
    @FindBy(id = "id_gender1")
    public WebElement maleRadioButton;

    @FindBy(id = "customer_firstname")
    public WebElement firstName;

    @FindBy(id = "customer_lastname")
    public WebElement lastName;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "days")
    public WebElement daySelector;

    @FindBy(id = "months")
    public WebElement monthSelector;

    @FindBy(id = "years")
    public WebElement yearSelector;

    @FindBy(id = "company")
    public WebElement company;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id = "address2")
    public WebElement address2;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "id_state")
    public WebElement stateSelector;

    @FindBy(id = "postcode")
    public WebElement postcode;

    @FindBy(id = "id_country")
    public WebElement countrySelector;

    @FindBy(id = "other")
    public WebElement additionalInfo;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "phone_mobile")
    public WebElement mobile;

    @FindBy(id = "alias")
    public WebElement addressAlias;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;

    private void registerAnUser(String anEmail, String aFirstName, String aLastName, String aCompany){
        //going to the site
        String siteURL = "http://automationpractice.com/index.php";
        driver.get(siteURL);
        Actions actionProvider = new Actions(driver);

        //waiting for the SignIn button to be clickable, otherwise it will fail
        wait.until(ExpectedConditions.elementToBeClickable(login_button));

        //clicking the SignIn button
        actionProvider.moveToElement(login_button).click().build().perform();

        //waiting until the h1 element is visible on the site "Authentication"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        actionProvider.moveToElement(email_input).click().sendKeys(anEmail).build().perform();
        //click on the submit button
        actionProvider.moveToElement(create_button).click().build().perform();

        //waiting for the form to load completely
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));

        //start interacting with the fields and sending the respective values
        actionProvider.moveToElement(maleRadioButton).click().build().perform();
        actionProvider.moveToElement(firstName).click().sendKeys(aFirstName).build().perform();
        actionProvider.moveToElement(lastName).click().sendKeys(aLastName).build().perform();
        actionProvider.moveToElement(password).click().sendKeys(Constants.PASSWORD).build().perform();

        actionProvider.moveToElement(daySelector).click().build().perform();
        Select selectDays = new Select(daySelector);
        selectDays.selectByValue(Constants.BIRTH_DAY);

        actionProvider.moveToElement(monthSelector).click().build().perform();
        Select selectMonths = new Select(monthSelector);
        selectMonths.selectByValue(Constants.BIRTH_MONTH);

        actionProvider.moveToElement(yearSelector).click().build().perform();
        Select selectYears = new Select(yearSelector);
        selectYears.selectByValue(Constants.BIRTH_YEAR);

        actionProvider.moveToElement(company).click().sendKeys(aCompany).build().perform();
        actionProvider.moveToElement(address1).click().sendKeys(Constants.FIRST_ADDRESS).build().perform();
        actionProvider.moveToElement(address2).click().sendKeys(Constants.SECOND_ADDRESS).build().perform();
        actionProvider.moveToElement(city).click().sendKeys(Constants.CITY).build().perform();

        actionProvider.moveToElement(stateSelector).click().build().perform();
        Select selectState = new Select(stateSelector);
        selectState.selectByValue("2");

        actionProvider.moveToElement(postcode).click().sendKeys("10000").build().perform();
        actionProvider.moveToElement(countrySelector).click().build().perform();
        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByValue("21");

        actionProvider.moveToElement(additionalInfo).click().sendKeys("Additional information").build().perform();
        actionProvider.moveToElement(phone).click().sendKeys("123456").build().perform();
        actionProvider.moveToElement(mobile).click().sendKeys("123456789").build().perform();
        actionProvider.moveToElement(addressAlias).click().sendKeys("MyAlias").build().perform();

        //clicking on register button
        actionProvider.moveToElement(registerButton).click().build().perform();
    }

    @FindBy(className = "icon-user")
    public WebElement userInformationButton;

    // personal information
    @FindBy(id = "firstname")
    public WebElement firstname_input;

    @FindBy(id = "lastname")
    public WebElement lastname_input;

    @FindBy(id = "email")
    public WebElement email_form;

    @FindBy(className = "icon-building")
    public WebElement addressInformationButton;

    @FindBy(className = "bloc_adresses")
    public WebElement bloc_adresses;

    @Test
    public void registrationTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();
        Actions actionProvider = new Actions(driver);

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        //flow to validate the correct information

        //waiting to see the account page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));

        //clicking on the personal information button in order to validate the information inserted previously
        actionProvider.moveToElement(userInformationButton).click().build().perform();

        //waiting to load all the data in the personal information page
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "YOUR PERSONAL INFORMATION"));

        //assertions for the personal information
        Assert.assertEquals(driver.findElement(By.cssSelector("#uniform-id_gender1 span")).getAttribute("class"), "checked");

        Assert.assertEquals(firstname_input.getAttribute("value"), fakeFirstName);
        Assert.assertEquals(lastname_input.getAttribute("value"), fakeLastName);
        Assert.assertEquals(email_form.getAttribute("value"), emailString);

        System.out.println("Birthdate: " + driver.findElement(By.cssSelector("#uniform-days span")).getText());
        System.out.println("birthmonth: " + driver.findElement(By.cssSelector("#uniform-months span")).getText());
        System.out.println("Birthyear: " + driver.findElement(By.cssSelector("#uniform-years span")).getText());

        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-days span")).getText().contains(Constants.BIRTH_DAY));
        //Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-months span")).getText().contains(Constants.BIRTH_MONTH));
        Assert.assertTrue(driver.findElement(By.cssSelector("#uniform-years span")).getText().contains(Constants.BIRTH_YEAR));

        //assertions for the address information
        driver.get("http://automationpractice.com/index.php?controller=my-account");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ACCOUNT"));
        actionProvider.moveToElement(addressInformationButton).click().build().perform();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "MY ADDRESSES"));

        Assert.assertTrue(bloc_adresses.getText().contains("MYALIAS"));
        Assert.assertTrue(bloc_adresses.getText().contains(fakeCompany));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.FIRST_ADDRESS));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.SECOND_ADDRESS));
        Assert.assertTrue(bloc_adresses.getText().contains(Constants.CITY));
        Assert.assertTrue(bloc_adresses.getText().contains("Alaska"));
        Assert.assertTrue(bloc_adresses.getText().contains("10000"));
        Assert.assertTrue(bloc_adresses.getText().contains("United States"));
        Assert.assertTrue(bloc_adresses.getText().contains("123456"));
        Assert.assertTrue(bloc_adresses.getText().contains("123456789"));
    }

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logout_button;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement myAccount;

    @FindBy(xpath = "//div[@id='create_account_error']/ol/li")
    public WebElement emailErrMsg;

    @Test
    public void verifyUsernameOnRegistrationIsUniqueTest() throws InterruptedException {
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();
        Actions actionProvider = new Actions(driver);

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        actionProvider.moveToElement(logout_button).click().build().perform();
        actionProvider.moveToElement(myAccount).click().build().perform();

        actionProvider.moveToElement(email_input).click().sendKeys(emailString).build().perform();

        //click on the submit button
        actionProvider.moveToElement(create_button).click().build().perform();

        Thread.sleep(3000);

        System.out.println(emailErrMsg.getText());

        Assert.assertEquals(emailErrMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
        Assert.assertTrue(emailErrMsg.getText().contains("Please enter a valid password or request a new one."));
    }

    @FindBy(id = "SubmitLogin")
    public WebElement submitLogin;

    @Test
    public void loginTest(){
        String emailString = "emiliano+" + Math.random() + "@gmail.com";
        Faker faker = new Faker();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakeCompany = faker.company().name();
        Actions actionProvider = new Actions(driver);

        registerAnUser(emailString, fakeFirstName, fakeLastName, fakeCompany);

        //logout...
        actionProvider.moveToElement(logout_button).click().build().perform();
        //login...
        actionProvider.moveToElement(myAccount).click().build().perform();

        actionProvider.moveToElement(email_form).click().sendKeys(emailString).build().perform();
        actionProvider.moveToElement(password).click().sendKeys(Constants.PASSWORD).build().perform();

        actionProvider.moveToElement(submitLogin).click().build().perform();

        String welcome_message = "Welcome to your account";
        WebElement p_welcome = driver.findElement(By.xpath("//p[contains(text(), 'Welcome to your account. Here you can manage all of your personal information and orders.')]"));
        Assert.assertTrue(p_welcome.getText().contains(welcome_message));
    }

}
