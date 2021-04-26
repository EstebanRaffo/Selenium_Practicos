import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpotifyTest extends BaseTest{
    String URL = "https://www.spotify.com";

    @BeforeMethod
    public void setupSpotify(){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @FindBy(tagName = "button")
    public List<WebElement> button_list;

    @FindBy(linkText = "Ayuda")
    public WebElement link_help;

    @FindBy(xpath = "//input[@placeholder='Buscar']")
    public WebElement input_buscar;

    @FindBy(linkText = "Estado de tu suscripción")
    public WebElement link_suscripcion;

    @FindBy(linkText = "Ayuda con la cuenta")
    public WebElement link_help_account;



    @Test
    public void showWebElements(){
        System.out.println("Botones: ");
        for(WebElement button : button_list){
            System.out.println(button.getText());
        }

        link_help.click();

        input_buscar.sendKeys("Suscripcion");

        link_suscripcion.click();
        link_help_account.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("account_help"));
    }
}
