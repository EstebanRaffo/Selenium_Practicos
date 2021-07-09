package Intermedio.Practico9;

import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

public class multipleWindowsTest {
    String URL = "https://www.naukri.com/";
    String SALESFORCE_URL = "https://www.salesforce.com/";
    public WebDriver driver;


    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
    }

    @Test
    public void closeAllWindows() throws InterruptedException {
        driver.get(URL);

        String mainWindow = driver.getWindowHandle();
        System.out.println("Ventana principal: " + mainWindow);


        Set<String> allWindows = driver.getWindowHandles();
        for (String currentWindow : allWindows){
            System.out.println("---> " + currentWindow);
            if (!currentWindow.equals(mainWindow)){
                Thread.sleep(2000);
                driver.switchTo().window(currentWindow);
                driver.close();
            }
        }
    }


    @Test
    public void salesforceWindowTest() throws InterruptedException {
        HashMap<String, String> tabsMap = new HashMap<String, String>();

        /// ORDENES = TIEMPO DE ESPERA.... lo que demora un programa en encontrar los elementos
        /// ORDEN DE UNA LISTA DE TAMAÑO N ---> N
        /// ORDEN DE UNA MAPA DE TAMAÑO N ---> 1

        HashMap<Integer, String> mapaPersonas = new HashMap<Integer, String>();
        mapaPersonas.put(51111, "32332" );
        mapaPersonas.put(666666, "c2");
        mapaPersonas.put(666666, "c1");
        mapaPersonas.put(777777, "Jorge Ramirez");

        driver.get(SALESFORCE_URL);
        Thread.sleep(3000);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        Thread.sleep(5000);
        String mainPageId = getCurrentWindowId(driver);
        tabsMap.put("mainPage", mainPageId);

        System.out.println("main window ---> " + mainPageId);

        driver.findElement(By.xpath("//a[@href='/mx/form/signup/freetrial-sales-pe/?d=70130000000EqoP']")).click();

        Set<String> tabList = getAllWindowsIds(driver);
        Assert.assertEquals(tabList.size(), 2);
        String registrationIdTab = "";

        for (String aTab : tabList) {
            if (aTab.equals(mainPageId) == false){
                driver.switchTo().window(aTab);
                registrationIdTab = driver.getWindowHandle(); // si se abren muchas tab conviene guardar el id de c/u
                tabsMap.put("registrationPage", aTab);
            }
        }


        Thread.sleep(2000);

        driver.findElement(By.name("UserFirstName")).sendKeys("Jhon");
        driver.findElement(By.name("UserLastName")).sendKeys("Rogger");
        driver.findElement(By.name("UserTitle")).sendKeys("Sr. Developer");
        System.out.println("Identificador pagina principal " + tabsMap.get("mainPage"));
        System.out.println("Identificador pagina principal " + tabsMap.get("registrationPage"));

        driver.switchTo().window(tabsMap.get("mainPage"));
        String tabTitle = driver.getTitle();
        //Assert.assertEquals(tabTitle, "");
        Thread.sleep(3000);

        driver.switchTo().window(tabsMap.get("registrationPage"));
        Thread.sleep(3000);

        driver.switchTo().window(tabsMap.get("mainPage"));
        Thread.sleep(3000);
        driver.switchTo().window(tabsMap.get("registrationPage"));
        Thread.sleep(3000);


    }


    private static String getCurrentWindowId(WebDriver driver){
        return driver.getWindowHandle();
    }

    private static Set<String> getAllWindowsIds(WebDriver driver){
        return driver.getWindowHandles();
    }




}
