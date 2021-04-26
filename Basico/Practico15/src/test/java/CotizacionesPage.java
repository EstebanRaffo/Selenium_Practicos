import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CotizacionesPage {
    public WebDriver driver;

    public CotizacionesPage(WebDriver remote_driver){
        driver = remote_driver;
    }

    public void validateCurrentSelected(String text) throws InterruptedException {
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.className( "Tab_Active" ));
        Assert.assertEquals(element.getText(), text );
    }

    public void clickToNyse() throws InterruptedException {
        List<WebElement> tabs = driver.findElements(By.className( "Tab" ));
        Thread.sleep(3000);
        for(WebElement tab : tabs) {
            if(tab.getText().equals( "NYSE" )){
                tab.click();
            }
        }
    }

    public void showValues(){
        List<WebElement> dataItems = driver.findElements( By.className( "DataItem" ));
        List <WebElement> dataValues = driver.findElements( By.className( "DataValues" ));

        for( WebElement item : dataItems ) {
            System.out.println(item.getText());
        }
        for( WebElement value : dataValues ){
            System.out.println( "--> " + value.getText());
        }
    }
}
