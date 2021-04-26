package Basico.clase11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class practico11 {

    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void testing(){
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.manage().window().maximize();


        WebElement footer = driver.findElement(By.xpath("//*[@data-referrer='page_footer']"));

        driver.findElement(By.xpath("//*[@value='1'][@name='sex']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Es rápido y fácil')]"));


    }

}
