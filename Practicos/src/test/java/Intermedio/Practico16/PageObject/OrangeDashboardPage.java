package Intermedio.Practico16.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class OrangeDashboardPage {

    public WebDriver driver;

    public OrangeDashboardPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }

    public String getDashboardTitle(){
        return driver.getTitle();
    }

    public String getDashboardURL(){
        return driver.getCurrentUrl();
    }


    public boolean searchQuickAction(String quickActionName) throws InterruptedException {
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

    public String getLeaveRequestToApproveNumber() throws InterruptedException {
        Thread.sleep(3000);
        WebElement cantidadLeaveRequestElement = driver.findElement(By.className("record-count"));
        String cantidad = cantidadLeaveRequestElement.getText();
        System.out.println(cantidad);

        String cantidadDeElementos = cantidad.replace("(", "");
        cantidadDeElementos = cantidadDeElementos.replace(")", "");

        return cantidadDeElementos;
    }

    public OrangeLeavePage goToLeavePage(){
        driver.findElement(By.className("link-title")).click();
        OrangeLeavePage orangeLeavePage = new OrangeLeavePage(driver);
        return orangeLeavePage;
    }

}
