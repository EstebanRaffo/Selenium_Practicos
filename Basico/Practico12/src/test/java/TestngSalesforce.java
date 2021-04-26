import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestngSalesforce {
    WebDriver driver;
    private static final String SALESFORCE_URL = "https://login.salesforce.com/";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(SALESFORCE_URL);
    }

    @Test (enabled=false, priority = 3)
    public void setUser(){
        driver.findElement(By.id("username")).sendKeys("e.fraffo@hotmail.com");
    }

    @Test (priority = 1)
    public void validateSalesforceLogoTest(){
        WebElement logo = driver.findElement(By.id("logo"));
        System.out.println("Es tagName del logo es " + logo.getTagName() + " y el alt es " + logo.getAttribute("alt"));
    }

    @Test (priority = 4)
    public void rememberMeIsSelected(){
        WebElement check_remember = driver.findElement(By.id("rememberUn"));
        check_remember.click();
        Assert.assertTrue(check_remember.isSelected());
    }

    @Test (priority = 2)
    public void footerIsValid(){
        driver.get("https://login.salesforce.com/?locale=eu");
        WebElement footer = driver.findElement(By.id("footer"));
        Assert.assertTrue(footer.getText().contains("All rights reserved"));
    }

    @Test (priority = 3)
    public void loginFailureTest() throws InterruptedException {
        /*************************** VER GIT ******************************/
        driver.get("https://login.salesforce.com/?locale=eu");
        WebElement logo = driver.findElement(By.id("logo"));
        Assert.assertTrue(logo.getAttribute("src").contains("/img/logo"));
        driver.findElement(By.id("username")).sendKeys("test@test.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("Login")).click();
        Thread.sleep(3000);
        String error_message = driver.findElement(By.id("error")).getText();
        System.out.println("El mensaje de error es: " + error_message);
        String expected_error = "Your access to salesforce.com has been disabled by your system administrator. Please contact your Administrator for more information.";
        Assert.assertEquals(error_message, expected_error);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Ejecución de los test finalizada.");
        /*driver.close();*/
    }
}
