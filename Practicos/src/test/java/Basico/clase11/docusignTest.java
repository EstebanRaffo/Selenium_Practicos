package Basico.clase11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class docusignTest {

    //
    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void completeDocusignRegistrationForm(){
        WebDriver driver = getDriver("https://go.docusign.com/o/trial");
        driver.findElement(By.xpath("//*[@name='first_name']")).sendKeys("Alan");
        driver.findElement(By.xpath("//*[@name='last_name']")).sendKeys("Smith");
        driver.findElement(By.xpath("//*[@name='email']")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//*[@name='phone']")).sendKeys("123123");
        driver.findElement(By.xpath("//*[@name='title']")).sendKeys("QA");
        WebElement industry = driver.findElement(By.xpath("//*[@name='ds_industry']"));
        Select industryDropdown = new Select(industry);
        industryDropdown.selectByVisibleText("Education");
    }

    @Test
    public void defaultXpath() {
        WebDriver driver = getDriver("https://go.docusign.com/o/trial");
        driver.findElement(By.xpath("//*[@id=\"dsFormLabel_First_Name\"]/input")).sendKeys("Alan");
        driver.findElement(By.xpath("//*[@id=\"dsFormLabel_Last_Name\"]/input")).sendKeys("Alan");
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/section[2]/div/div/div/div/div/div[2]/form/div[1]/label[2]/input")).sendKeys("Alan");

        driver.findElement(By.cssSelector("#dsFormLabel_Last_Name > input"));
        //
    }


    }
