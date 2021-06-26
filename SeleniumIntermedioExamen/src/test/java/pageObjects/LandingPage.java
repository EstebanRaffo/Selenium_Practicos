package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver remoteDriver){ this.driver = remoteDriver; }

    public void inicializarSpotifyPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.spotify.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public PremiumPage clickOnPremium(){
        WebElement linkPremium = driver.findElement(By.xpath("//a[contains(text(),'Premium')]"));
        linkPremium.click();
        PremiumPage premiumPage = new PremiumPage(driver);
        return premiumPage;
    }

    public RegistrationPage clickOnRegistrarse(){
        WebElement linkRegistrarse = driver.findElement(By.xpath("//a[contains(text(),'Regístrate')]"));
        linkRegistrarse.click();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        return registrationPage;
    }

    public TermsPage goTerms(){
        driver.navigate().to("https://www.spotify.com/uy/legal/end-user-agreement/");
        TermsPage termsPage = new TermsPage(driver);
        return termsPage;
    }
}
