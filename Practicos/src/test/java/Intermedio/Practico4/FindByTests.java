package Intermedio.Practico4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FindByTests {

    //Ingresar a netflix
    //Mostrar la cantidad de párrafos
    //Mostrar la cantidad de links
    //Imprimir los links que tienen texto
    //Mostrar el texto de todos los botones
    //Obtener una lista con todos los h1s, h2s y h3s
    String URL = "https://www.netflix.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "a")
    public List<WebElement> links;

    @FindBy(tagName = "p")
    public List<WebElement> allParagraphs;

    @FindBy(tagName = "button")
    public List<WebElement> allBtns;

    @FindBy(xpath = "//*[@href='/login']")
    public WebElement loginBtn;

    @FindAll({
        @FindBy(how = How.TAG_NAME, using = "h1"),
        @FindBy(how = How.TAG_NAME, using = "h2"),
        @FindBy(how = How.TAG_NAME, using = "h3")
    })
    public List<WebElement> getAllHs;

    @FindBy(linkText = "Empleo")
    public WebElement empleoLink;

    @Test
    public void ejercicio1(){
        /*for(WebElement p: allParagraphs){
            if(p.getText().isEmpty() == false){
                System.out.println("--> " + p.getText());
            }
        }
        System.out.println("La cantidad de parrafos es " + allParagraphs.size());
        System.out.println("La cantidad de links es " + links.size());
*/
        for(WebElement link: links){
            if (!link.getText().isEmpty()){
                System.out.println("Link: "+ link.getText());
            }
        }

        for(WebElement elementH: getAllHs){
            if (!elementH.getText().isEmpty()){

                System.out.println(elementH.getTagName() + "--> " + elementH.getText());
            }
        }

        empleoLink.click();
        //loginBtn.click();

    }








}
