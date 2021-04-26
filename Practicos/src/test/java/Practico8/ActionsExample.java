package Practico8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsExample {

    String URL = "https://www.google.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
    }

    @Test
    public void moveToElement(){
        WebElement gmailLink = driver.findElement(By.xpath("//*[@href='https://mail.google.com/mail/&ogbl']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gmailLink).click().build().perform();
    }

    @Test
    public void doubleClickTest(){
        WebElement gmailLink = driver.findElement(By.xpath("//*[@href='https://mail.google.com/mail/&ogbl']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(gmailLink).build().perform();
    }

    @Test
    public void contextClickTest(){
        WebElement gmailLink = driver.findElement(By.xpath("//*[@href='https://mail.google.com/mail/&ogbl']"));
        Actions actions = new Actions(driver);
        actions.contextClick(gmailLink).build().perform();
    }
}
