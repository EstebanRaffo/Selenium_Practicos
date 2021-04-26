import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSalesforce {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        String baseURL = "https://login.salesforce.com/?locale=eu";
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static final String FIRSTNAME_ERROR = "Enter your first name";
    private static final String LASTNAME_ERROR = "Enter your last name";
    private static final String TITLE_ERROR = "Enter your title";
    private static final String EMAIL_ERROR = "Enter a valid email address";
    private static final String PHONE_ERROR = "Enter a valid phone number";
    private static final String COMPANY_ERROR = "Enter your company name";
    private static final String EMPLOYEES_ERROR = "Select the number of employees";
    private static final String COMPANY_LANGUAGE_ERROR = "Select company language";
    private static final String AGREEMENT_ERROR = "Please read and agree to the Master Subscription Agreement";

    @Test
    public void testingErrorMessages(){
        driver.findElement(By.linkText("Try for Free")).click();
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.name("Start my free trial")).click();

        List<WebElement> errors = driver.findElements(By.className("error-msg"));
        WebElement error_agreement = driver.findElement(By.className("error-msg-block"));

        boolean firstname_message_error = false;
        boolean lastname_message_error = false;
        boolean title_message_error = false;
        boolean email_message_error = false;
        boolean phone_message_error = false;
        boolean company_message_error = false;
        boolean employees_message_error = false;
        boolean company_language_message_error = false;
        boolean agreement_message_error = false;

        if(error_agreement.getText().equals(AGREEMENT_ERROR)){
            agreement_message_error = true;
        }

        for(WebElement anError : errors){
            System.out.println(anError.getText());
            String error_message = anError.getText();
            if(error_message.equals(FIRSTNAME_ERROR)){
                firstname_message_error = true;
            }
            if(error_message.equals(LASTNAME_ERROR)){
                lastname_message_error = true;
            }
            if(error_message.equals(TITLE_ERROR)){
                title_message_error = true;
            }
            if(error_message.equals(EMAIL_ERROR)){
                email_message_error = true;
            }
            if(error_message.equals(PHONE_ERROR)){
                phone_message_error = true;
            }
            if(error_message.equals(COMPANY_ERROR)){
                company_message_error = true;
            }
            if(error_message.equals(EMPLOYEES_ERROR)){
                employees_message_error = true;
            }
            if(error_message.equals(COMPANY_LANGUAGE_ERROR)){
                company_language_message_error = true;
            }
        }

        Assert.assertTrue(firstname_message_error);
        Assert.assertTrue(lastname_message_error);
        Assert.assertTrue(title_message_error);
        Assert.assertTrue(email_message_error);
        Assert.assertTrue(phone_message_error);
        Assert.assertTrue(company_message_error);
        Assert.assertTrue(employees_message_error);
        Assert.assertTrue(company_language_message_error);
        Assert.assertTrue(agreement_message_error);

        Faker faker_peer = new Faker();

        String name = faker_peer.name().firstName();
        String lastname = faker_peer.name().lastName();
        String email = faker_peer.internet().emailAddress();

        driver.findElement(By.name("UserFirstName")).sendKeys(name);
        driver.findElement(By.name("UserLastName")).sendKeys(lastname);
        driver.findElement(By.name("UserEmail")).sendKeys(email);
        driver.findElement(By.name("Start my free trial")).click();
        boolean new_email_message_error = false;
        List<WebElement> new_errors = driver.findElements(By.className("error-msg"));

        for(WebElement anError : new_errors){
            if(anError.getText().equals(EMAIL_ERROR)){
                new_email_message_error = true;
            }
        }

        Assert.assertFalse(new_email_message_error);
    }

    @Test(dataProvider = "persona", dataProviderClass = DataGenerator.class)
    public void completeSalesforceFormTest(String name, String lastname, String title, String email, String phone){
        driver.findElement(By.linkText("Try for Free")).click();
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        driver.findElement(By.name("UserFirstName")).sendKeys(name);
        driver.findElement(By.name("UserLastName")).sendKeys(lastname);
        driver.findElement(By.name("UserTitle")).sendKeys(title);
        driver.findElement(By.name("UserEmail")).sendKeys(email);
        driver.findElement(By.name("UserPhone")).sendKeys(phone);

        driver.findElement(By.name("Start my free trial")).click();

        boolean firstname_message_error = false;
        boolean lastname_message_error = false;
        boolean title_message_error = false;
        boolean email_message_error = false;
        boolean phone_message_error = false;

        List<WebElement> errors = driver.findElements(By.className("error-msg"));

        for(WebElement anError : errors){
            String error_message = anError.getText();
            if(error_message.equals(FIRSTNAME_ERROR)){
                firstname_message_error = true;
            }
            if(error_message.equals(LASTNAME_ERROR)){
                lastname_message_error = true;
            }
            if(error_message.equals(TITLE_ERROR)){
                title_message_error = true;
            }
            if(error_message.equals(EMAIL_ERROR)){
                email_message_error = true;
            }
            if(error_message.equals(PHONE_ERROR)){
                phone_message_error = true;
            }
        }

        Assert.assertFalse(firstname_message_error);
        Assert.assertFalse(lastname_message_error);
        Assert.assertFalse(title_message_error);
        Assert.assertFalse(email_message_error);
        Assert.assertFalse(phone_message_error);
    }

    @Test
    public void fillFormWithFakersTest(){
        DataFactory data_faker = new DataFactory();

        String name = data_faker.getFirstName();
        String lastname = data_faker.getLastName();
        String job = data_faker.getJob();
        String email = data_faker.getEmail();
        String phone = data_faker.getPhone();
        String company = data_faker.getCompany();

        driver.findElement(By.linkText("Try for Free")).click();
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        driver.findElement(By.name("UserFirstName")).sendKeys(name);
        driver.findElement(By.name("UserLastName")).sendKeys(lastname);
        driver.findElement(By.name("UserTitle")).sendKeys(job);
        driver.findElement(By.name("UserEmail")).sendKeys(email);
        driver.findElement(By.name("UserPhone")).sendKeys(phone);
        driver.findElement(By.name("CompanyName")).sendKeys(company);

        Select company_employees = new Select(driver.findElement(By.name("CompanyEmployees")));
        company_employees.selectByValue("50");

        Select company_language = new Select(driver.findElement(By.name("CompanyLanguage")));
        company_language.selectByValue("es");

        driver.findElement(By.name("Start my free trial")).click();

        WebElement error_agreement = driver.findElement(By.className("error-msg-block"));

        boolean agreement_message_error = false;
        String message_error_agreem = error_agreement.getText();

        if(message_error_agreem.equals(AGREEMENT_ERROR)){
            agreement_message_error = true;
        }

        Assert.assertTrue(agreement_message_error);
        System.out.println(message_error_agreem);
    }
}
