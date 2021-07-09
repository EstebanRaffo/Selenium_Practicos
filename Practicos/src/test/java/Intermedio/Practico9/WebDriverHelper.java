package Intermedio.Practico9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebDriverHelper {

    public static List<WebElement> getElementsWithNames(List<WebElement> elements){
        List<WebElement> elementsWithNames = new ArrayList<>();
        for (WebElement ele : elements){
            if (ele.getAttribute("name").isEmpty() == false){
                elementsWithNames.add(ele);
            }
        }
        return elementsWithNames;
    }

    public static List<WebElement> getElementsWithNames(WebDriver driver, String tagName){
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        return getElementsWithNames(elements);
    }

    public static List<WebElement> getValidLinks(WebDriver driver){
        List<WebElement> linksList = driver.findElements(By.tagName("a"));
        List<WebElement> validLinksList = new ArrayList<>();
        for (WebElement linkElement : linksList){
            if (linkElement.getAttribute("href").isEmpty() == false) {
                validLinksList.add(linkElement);
            }
        }
        return validLinksList;
    }

    public static List<WebElement> getBrokenLinks(WebDriver driver){
        List<WebElement> linksList = driver.findElements(By.tagName("a"));
        List<WebElement> nullLinksList = new ArrayList<>();
        for (WebElement linkElement : linksList){
            if (linkElement.getAttribute("href").isEmpty() == true) {
                nullLinksList.add(linkElement);
            }
        }
        return nullLinksList;
    }






}
