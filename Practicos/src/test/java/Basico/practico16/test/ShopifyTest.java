package Basico.practico16.test;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Basico.practico16.pageObject.Shopify.ShopifyLandingPage;
import Basico.practico16.pageObject.Shopify.ShopifyLoginPage;
import Basico.practico16.pageObject.Shopify.ShopifyPricingPage;

import java.util.List;

public class ShopifyTest extends BaseTest {

    @BeforeMethod
    public void setupShopi(){
        driver.get("https://www.shopify.com");
    }

    @Test
    public void pricingTest() {
        ShopifyLandingPage shopifyLandingPage = new ShopifyLandingPage(driver);
        ShopifyPricingPage shopifyPricingPage = shopifyLandingPage.clickOnPricingButton();

        List<WebElement> h1List = shopifyPricingPage.getH1s();
        boolean h1Found = false;

        for (WebElement h1 : h1List) {
            System.out.println(h1.getText());
            if (h1.getText().equals("Set up your store, pick a plan later")) {
                h1Found = true;
            }
        }

        Assert.assertTrue(shopifyPricingPage.getTitle().contains("Shopify Pricing"));
        Assert.assertEquals(shopifyPricingPage.getCurrentUrl(), "https://www.shopify.com/pricing");
        Assert.assertTrue(h1Found);
    }

    @Test
    public void loginTest() throws InterruptedException {

        ShopifyLandingPage shopifyLandingPage = new ShopifyLandingPage(driver);
        ShopifyLoginPage shopifyLoginPage = shopifyLandingPage.navigateToLoginBtn();
        shopifyLoginPage.clickOnSiguienteBtn();

        WebElement errMsg = shopifyLoginPage.getErrorMsg();
        Assert.assertEquals(errMsg.getText(), "Dirección de tienda incorrecta. Una dirección de tienda válida termina en .myshopify.com, .com, u otra extensión de dominio.");
    }

}
