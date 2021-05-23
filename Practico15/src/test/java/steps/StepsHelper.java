package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class StepsHelper {

    public static boolean searchQuickAccess(WebDriver driver, String quick_access_term) throws InterruptedException {
        Thread.sleep(3000);

        List<WebElement> quick_access_list = driver.findElements(By.className("quick-access-heading"));
        Assert.assertNotEquals(quick_access_list.size(), 0, "El quick access esta vacío");

        boolean quick_access_found = false;

        for(WebElement quick_access : quick_access_list){
            if(quick_access.getText().equals(quick_access_term)){
                quick_access_found = true;
            }
        }

        return quick_access_found;
    }
}
