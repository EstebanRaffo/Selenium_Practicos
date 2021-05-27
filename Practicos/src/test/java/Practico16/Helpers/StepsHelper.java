package Practico16.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class StepsHelper {


    public static boolean searchQuickAction(String quickActionName , WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);

        List<WebElement> listaQuickActions = driver.findElements(By.className("quick-access-heading"));
        Assert.assertNotEquals(listaQuickActions.size(), 0, "Deberian haber elementos en la lista");

        boolean quickActionFound = false;

        for (WebElement quickAction : listaQuickActions) {
            if ( quickAction.getText().equals(quickActionName)){
                quickActionFound = true;
            }
        }
        return quickActionFound;
    }



}
