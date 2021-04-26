import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class SpotifyTestWithParameters {
    public WebDriver driver;
    public String URL = "https://www.spotify.com/uy/";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    @Parameters({"specificTag"})
    public void spotifyTags(@Optional("h1") String tag){
        List<WebElement> list_elements = driver.findElements(By.tagName(tag));

        System.out.println("Lista de " + tag);
        for(WebElement element : list_elements){
            System.out.println(tag + ": " + element.getText());
        }
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Ejecución de los test finalizada.");
        /*driver.close();*/
    }
}
