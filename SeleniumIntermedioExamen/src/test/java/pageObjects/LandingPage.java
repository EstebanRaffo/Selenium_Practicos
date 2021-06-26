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

    public void clickOnPremium(){
        WebElement linkPremium = driver.findElement(By.xpath("//a[contains(text(),'Premium')]"));
        linkPremium.click();
    }

    public String getPlanIndividual(){
        WebElement planIndividual = driver.findElement(By.xpath("//h3[contains(text(),'Individual')]"));
        return planIndividual.getText();
    }

    public String getPlanDuo(){
        WebElement planDuo = driver.findElement(By.xpath("//h3[contains(text(),'Duo')]"));
        return planDuo.getText();
    }

    public String getPlanFamiliar(){
        WebElement planFamiliar = driver.findElement(By.xpath("//h3[contains(text(),'Familiar')]"));
        return planFamiliar.getText();
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
