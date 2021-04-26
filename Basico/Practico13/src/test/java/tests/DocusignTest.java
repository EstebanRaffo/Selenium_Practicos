package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DocusignTest {
    WebDriver driver;
    public String URL_DOCUSIGN = "https://go.docusign.com/o/trial/";
    private final String instance;

    public DocusignTest(String instance){
        this.instance = instance;
    }

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL_DOCUSIGN);
    }

    @Test
    public void validateTitle(){
        String expected_title = "DocuSign Free Trial";
        Assert.assertEquals(driver.getTitle(), expected_title);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("Test finalizado: " + this.instance);
        driver.close();
    }

}
