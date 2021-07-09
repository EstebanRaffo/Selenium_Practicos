package Intermedio.ecommerce_site.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPersonalInformationPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public MyPersonalInformationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);  //para utilizar los @Find By...
    }




}
