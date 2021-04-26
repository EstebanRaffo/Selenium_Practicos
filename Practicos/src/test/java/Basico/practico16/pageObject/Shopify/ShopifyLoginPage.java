package Basico.practico16.pageObject.Shopify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Basico.practico16.pageObject.BaseUITest;

import java.util.List;

public class ShopifyLoginPage extends BaseUITest {

    public ShopifyLoginPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public void clickOnSiguienteBtn(){
        driver.findElement(By.name("commit")).click();
    }

    public WebElement getErrorMsg(){
        List<WebElement> errorMessageList = driver.findElements(By.className("validation-error__message"));
        WebElement errMsg = errorMessageList.get(0);
        return errMsg;
    }



}
