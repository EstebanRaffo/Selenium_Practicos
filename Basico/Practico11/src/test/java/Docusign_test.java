import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Docusign_test {
    WebDriver driver;

    /************************************* Ejercicio 1 *******************************************************/
    @Test
    public void completeDocusignRegistrationForm(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://go.docusign.com/o/trial";
        driver.get(baseURL);

        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
        firstName.sendKeys("Esteban");

        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
        lastName.sendKeys("Raffo");

        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("e.fraffo@gmail.com");

        WebElement phone = driver.findElement(By.xpath("//input[@name=\"phone\"]"));
        phone.sendKeys("1122334455");

        WebElement jobTitle = driver.findElement(By.xpath("//input[@name=\"title\"]"));
        jobTitle.sendKeys("Analista QA");

        WebElement selectIndustry = driver.findElement(By.xpath("//select[@name=\"ds_industry\"]"));
        Select industry = new Select(selectIndustry);
        industry.selectByValue("Technology");
    }

    /************************************* Ejercicio 2 *******************************************************/
    @Test
    public void defaultxPath(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://go.docusign.com/o/trial";
        driver.get(baseURL);

        WebElement firstName = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_First_Name\"]/input"));
        firstName.sendKeys("Esteban");

        WebElement lastName = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Last_Name\"]/input"));
        lastName.sendKeys("Raffo");

        WebElement email = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Email\"]/input"));
        email.sendKeys("e.fraffo@gmail.com");

        WebElement phone = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Phone\"]/input"));
        phone.sendKeys("1122334455");

        WebElement jobTitle = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Job_Title\"]/input"));
        jobTitle.sendKeys("Analista QA");

        WebElement selectIndustry = driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Industry\"]/select"));
        Select industry = new Select(selectIndustry);
        industry.selectByValue("Technology");
    }


}
