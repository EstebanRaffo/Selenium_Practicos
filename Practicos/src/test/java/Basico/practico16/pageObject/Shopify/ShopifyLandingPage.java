package Basico.practico16.pageObject.Shopify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Basico.practico16.pageObject.BaseUITest;

public class ShopifyLandingPage extends BaseUITest {

    public ShopifyLandingPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public ShopifyPricingPage clickOnPricingButton(){
        //driver.findElement(By.xpath("//*[@href='/pricing']")).click();
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Pricing")).click();
        ShopifyPricingPage nextPage = new ShopifyPricingPage(driver);
        return nextPage;
    }

    public ShopifyLoginPage navigateToLoginBtn(){
        driver.navigate().to("https://accounts.shopify.com/store-login");
        ShopifyLoginPage nextPage = new ShopifyLoginPage(driver);
        return nextPage;
    }


}


