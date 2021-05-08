package Practico13.practico13_csv;

import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Practico12.Persona;

import java.io.IOException;
import java.util.HashMap;

public class DocusignTest {

    public WebDriver driver;
    public HashMap<String, String> Id_Username_Map;
    public HashMap<String, Persona> Username_Person_Map;

    @BeforeTest
    public void loadData() throws IOException, CsvValidationException {
        CSV_Ejemplo2.loadDataFromCSV();
        Id_Username_Map = CSV_Ejemplo2.ID_USERNAME_MAP;
        Username_Person_Map = CSV_Ejemplo2.USERNAME_PERSON_MAP;
    }

    @BeforeMethod
    public void setDriver(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.docusign.com");
    }

    private int i = 0;
    @Test(invocationCount = 3)
    public void registrationTest(){
        //driver.get("https://www.spotify.com/uy/signup/");


        i++;
    }





}
