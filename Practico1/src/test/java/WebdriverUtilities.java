import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebdriverUtilities {

    public static List<WebElement> getElementsByClassName(WebDriver remote_driver, String class_element){
        List<WebElement> elements = remote_driver.findElements(By.className(class_element));
        return elements;
    }
}
