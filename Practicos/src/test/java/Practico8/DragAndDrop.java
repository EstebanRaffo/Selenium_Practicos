package Practico8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDrop {

    String URL = "https://crossbrowsertesting.github.io/drag-and-drop";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
    }

    @Test
    public void dragAndDrop(){
        WebElement originElement = driver.findElement(By.id("draggable"));
        WebElement dropAreaElement = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(originElement, dropAreaElement).perform();

    }
}
