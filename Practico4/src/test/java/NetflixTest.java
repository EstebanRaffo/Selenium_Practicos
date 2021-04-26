import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NetflixTest extends BaseTest{
    String URL = "https://www.netflix.com";

    @BeforeMethod
    public void setupAirbnb(){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @FindBy(tagName = "p")
    public List<WebElement> p_list;

    @FindBy(tagName = "a")
    public List<WebElement> a_list;

    @FindBy(tagName = "button")
    public List<WebElement> button_list;

    @FindAll({
            @FindBy(how = How.TAG_NAME, using = "h1"),
            @FindBy(how = How.TAG_NAME, using = "h2"),
            @FindBy(how = How.TAG_NAME, using = "h3")
    })
    public List<WebElement> Hs_list;

    @Test
    public void showWebElements() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Cantidad de parrafos: "+ p_list.size());
        System.out.println("Cantidad de links: " + a_list.size());

        System.out.println("Links con texto: ");
        for(WebElement a : a_list){
            if(!a.getText().isEmpty()){
                System.out.println(a.getText());
            }
        }

        System.out.println("Botones: ");
        for(WebElement button : button_list){
            System.out.println(button.getText());
        }

        System.out.println("Hs: ");
        for(WebElement h : Hs_list){
            System.out.println(h.getText());
        }
    }
}
