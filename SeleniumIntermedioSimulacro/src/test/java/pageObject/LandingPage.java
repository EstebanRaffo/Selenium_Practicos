package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LandingPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public LandingPage(WebDriver remoteDriver){ this.driver = remoteDriver; }

    public void inicializarAirbnbPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.airbnb.com.ar");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        driver.findElement(By.xpath("//button[@data-testid='accept-btn']")).click();
    }

    @FindBy(xpath = "//input[@placeholder='¿A dónde vas?']")
    public WebElement lugar;

    @FindBy(xpath = "//div[contains(text(), 'Check-in')]")
    public WebElement checkin;

    @FindBy(xpath = "//div[@data-testid='datepicker-day-2021-06-25']")
    public WebElement datein;

    @FindBy(xpath = "//div[@data-testid='datepicker-day-2021-07-10']")
    public WebElement dateout;

    @FindBy(xpath = "//div[contains(text(), 'Check-out')]")
    public WebElement checkout;

    @FindBy(xpath = "//div[contains(text(), 'Huéspedes')]")
    public WebElement huespedes;

    @FindBy(xpath = "//button[@data-testid='stepper-adults-increase-button']")
    public WebElement increaseAdults;

    @FindBy(xpath = "//button[@data-testid='structured-search-input-search-button']")
    public WebElement searchButton;

    public void setLugar(String unLugar){ lugar.sendKeys(unLugar); }

    public void setDateCheckin(){
        wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(checkin));
        checkin.click();
        datein.click();
    }

    public void setDateCheckout(){ dateout.click(); }

    public void setHuespedes(Integer cantidad){
        huespedes.click();

        for(int i = 0; i < cantidad; i++){
            increaseAdults.click();
        }
    }

    public HousingPage clickOnSearchButton(){
        searchButton.click();
        HousingPage housingPage = new HousingPage(driver);

        return housingPage;
    }

    public void selectExperiencias(){ driver.findElement(By.xpath("(//span[contains(text(), 'Experiencias')])[1]")).click(); }

    public String getLugarInput(){ return driver.findElement(By.xpath("//div[contains(text(), 'Lugar')]")).getText(); }

    public String getFechaInput(){ return driver.findElement(By.xpath("//div[contains(text(), 'Fecha')]")).getText(); }


    public void setDateOfExperience(){
        driver.findElement(By.xpath("//div[contains(text(), 'Agregá cuándo querés ir')]")).click();
        datein.click();
    }

    public String getTitleResult(){ return driver.findElement(By.xpath("//h1[contains(text(), 'experiencia')]")).getText(); }

    public List<WebElement> getLugaresCercanos(){ return driver.findElements(By.xpath("//span[@class='_1i13tcg']")); }



}
