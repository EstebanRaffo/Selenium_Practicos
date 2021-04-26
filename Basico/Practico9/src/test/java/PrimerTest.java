import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class PrimerTest {
    WebDriver driver;

    @Test
    public void testInWindows(){
        System.out.println("Primer Test");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.bbc.com/mundo";
        driver.get(baseURL);
        System.out.println(driver.getTitle());
        String url = driver.getCurrentUrl();
        System.out.println("La url actual es " + url);
        driver.close();
    }

    @Test
    public void mostrarTitulo(){
        System.out.println("Test mostrarTitulo()");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.bbc.com/mundo";
        driver.get(baseURL);
        System.out.println(driver.getTitle());
        driver.close();
    }

    @Test
    public void mostrarElementos(){
        System.out.println("Test mostrarElementos()");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.spotify.com";
        driver.get(baseURL);
        List<WebElement> listaDeH1s = driver.findElements(By.tagName("h1"));
        System.out.println("La cantidad de elementos h1 es: " + listaDeH1s.size());

        for(WebElement element : listaDeH1s){
            System.out.println("Elemento h1: " + element.getText());
        }
        System.out.println("************************************************");
        List<WebElement> listaDeH2s = driver.findElements(By.tagName("h2"));
        System.out.println("La cantidad de elementos h2 es: " + listaDeH2s.size());

        for(WebElement element : listaDeH2s){
            System.out.println("Elemento h2: " + element.getText());
        }
        System.out.println("************************************************");
        List<WebElement> listaParrafos = driver.findElements(By.tagName("p"));
        System.out.println("La cantidad de parrafos es: " + listaParrafos.size());

        for(WebElement element : listaParrafos){
            System.out.println("Elemento p: " + element.getText());
        }

        driver.close();
    }


    /******************************************** Ejercicio 3 *******************************************************/
    @Test
    public void bbcMundo(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.bbc.com/mundo";
        driver.get(baseURL);
        List<WebElement> listaDeH1s = driver.findElements(By.tagName("h1"));
        System.out.println("La cantidad de elementos h1 es: " + listaDeH1s.size());

        for(WebElement element : listaDeH1s){
            System.out.println("Elemento h1: " + element.getText());
        }

        List<WebElement> listaDePs = driver.findElements(By.tagName("p"));
        System.out.println("La cantidad de elementos p es: " + listaDePs.size());

        for(WebElement element : listaDePs){
            System.out.println("Elemento p: " + element.getText());
        }

        System.out.println("************************************************");
        List<WebElement> listaDeH2s = driver.findElements(By.tagName("h2"));
        System.out.println("La cantidad de elementos h2 es: " + listaDeH2s.size());

        for(WebElement element : listaDeH2s){
            System.out.println("Elemento h2: " + element.getText());
        }

        System.out.println("************************************************");
        List<WebElement> listaDeH3s = driver.findElements(By.tagName("h3"));
        System.out.println("La cantidad de elementos h3 es: " + listaDeH3s.size());

        for(WebElement element : listaDeH3s){
            System.out.println("Elemento h3: " + element.getText());
        }
        driver.manage().window().maximize();
        driver.navigate().refresh();
    }

    /******************************************** Ejercicio 4 *******************************************************/
    @Test
    public void bbcMundoLinks(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.bbc.com/mundo";
        driver.get(baseURL);
        List<WebElement> listaLinks = driver.findElements(By.tagName("a"));
        System.out.println("La cantidad de elementos a es: " + listaLinks.size());

        for(WebElement element : listaLinks){
            System.out.println("Elemento a: " + element.getText());
        }

        List<WebElement> listaDePs = driver.findElements(By.tagName("p"));
        System.out.println("La cantidad de elementos p es: " + listaDePs.size());

        driver.manage().window().maximize();
        driver.navigate().refresh();
    }

    /******************************************** Ejercicio 5 *******************************************************/
    @Test
    public void bbcMundoListas(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.bbc.com/mundo";
        driver.get(baseURL);

        List<WebElement> listalis = driver.findElements(By.tagName("li"));
        System.out.println("La cantidad de elementos li es: " + listalis.size());

        for(WebElement element : listalis){
            System.out.println("Elemento li: " + element.getText());
        }

        List<WebElement> listaPs = driver.findElements(By.tagName("p"));
        System.out.println("La cantidad de parrafos es: " + listaPs.size());
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.close();
    }

    /******************************************** Ejercicio 6 *******************************************************/
    @Test
    public void getGoogleTest(){
        System.out.println("Test getTitleTest()");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.google.com";
        driver.get(baseURL);
        String title = driver.getTitle();

        if(title.equals("Google")){
            System.out.println("Test passed !!");
        }else{
            System.out.println(title);
            System.out.println("Test failed !!");
        }

        driver.close();
    }

    @Test
    public void getTitleTest(){
        WebDriver driver = getGoogleDriver();
        String title = driver.getTitle();

        if(title.equals("Google")){
            System.out.println("Test passed !!");
        }else{
            System.out.println(title);
            System.out.println("Test failed !!");
        }
        driver.close();
    }

    /******************************************** Ejercicio 7 *******************************************************/
    @Test
    public void getWindowsSizes(){
        System.out.println("Test getWindowsSizes()");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.spotify.com";
        driver.get(baseURL);

        int altura = driver.manage().window().getSize().getHeight();
        int ancho = driver.manage().window().getSize().getWidth();

        System.out.println("El ancho de la pantalla es " + ancho);
        System.out.println("El alto de la pantalla es " + altura);

        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();

        System.out.println("Ancho desde Dimension: " + width);
        System.out.println("Alto desde Dimension: " + height);

        driver.manage().window().setSize(new Dimension(1024,768));

        int newWidth = driver.manage().window().getSize().getWidth();
        int newHeight = driver.manage().window().getSize().getHeight();

        Assert.assertEquals(newWidth, 1024);
        System.out.println("El nuevo ancho de la pantalla es " + newWidth);

        Assert.assertEquals(newHeight, 768);
        System.out.println("El nuevo alto de la pantalla es " + newHeight);

        driver.close();
    }

    /******************************************** Ejercicio 8 ************************************************/
    public WebDriver getGoogleDriver(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.google.com";
        driver.get(baseURL);
        return driver;
    }

    @Test
    public void goToGoogle(){
        WebDriver driver = getGoogleDriver();
        String title = driver.getTitle();
        System.out.println(title);
    }

    /******************************************** Ejercicio 9 ************************************************/
    public WebDriver getDriver(String URL){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void goToBBC(){
        WebDriver driver = getDriver("https://www.bbc.com");
        String title = driver.getTitle();
        System.out.println(title);
    }

    /******************************************** Ejercicio 10 ************************************************/
    @Test
    public void searchInGoogle(){
        WebDriver driver = getGoogleDriver();
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("WebElement" + Keys.ENTER);
        System.out.println(search);
    }

    /******************************************** Ejercicio 11 ************************************************/
    @Test
    public void searchInGoogleAndGoBack(){
        WebDriver driver = getGoogleDriver();
        String title = driver.getTitle();
        System.out.println(title);
        driver.findElement(By.name("q")).sendKeys("selenium driver" + Keys.ENTER);
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();
    }

    /******************************************** Ejercicio 12 ************************************************/
    @Test
    public void getBrowserSizes(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.facebook.com";
        driver.get(baseURL);

        int altura = driver.manage().window().getSize().height;
        int ancho = driver.manage().window().getSize().width;

        System.out.println("El ancho de la pantalla es " + ancho);
        System.out.println("El alto de la pantalla es " + altura);

        driver.manage().window().maximize();

        int alturaM = driver.manage().window().getSize().height;
        int anchoM = driver.manage().window().getSize().width;

        System.out.println("El ancho de la pantalla maximizada es " + anchoM);
        System.out.println("El alto de la pantalla maximizada es " + alturaM);
    }

    /******************************************** Ejercicio 13 ************************************************/
    @Test
    public void facebookPageTest(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.facebook.com";
        driver.get(baseURL);

        List<WebElement> listaDivs = driver.findElements(By.tagName("div"));
        System.out.println("Cantidad de elementos <div> es: " + listaDivs.size());

        System.out.println("Elementos <div>: ");
        for(WebElement element : listaDivs){
            System.out.println(element.getText());
        }

        List<WebElement> anchors = driver.findElements(By.className("a"));
        System.out.println("Cantidad de elementos <a> es: " + anchors.size());

        System.out.println("Elementos <a>: ");
        for(WebElement element : anchors){
            System.out.println(element.getText());
        }

        List<WebElement> listaBotones = driver.findElements(By.tagName("button"));
        System.out.println("Cantidad de botones es: " + listaBotones.size());

        System.out.println("Botones: ");
        for(WebElement element : listaBotones){
            System.out.println(element.getText());
        }
    }

    /******************************************** Ejercicio 14 ************************************************/
    @Test
    public void sendKeysToFacebook(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.facebook.com";
        driver.get(baseURL);

        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");

        WebElement pass = driver.findElement(By.name("pass"));
        pass.sendKeys("test@test.com");

        WebElement login = driver.findElement(By.name("login"));
        login.click();

    }

    /******************************************** Ejercicio 15 ************************************************/
    /** CONSULTAR SI HAY QUE HACER ALGO CON GIT **/

    @Test
    public void testNetflix(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.netflix.com/uy/";
        driver.get(baseURL);

        List<WebElement> listaH1s = driver.findElements(By.tagName("h1"));
        List<WebElement> listaH2s = driver.findElements(By.tagName("h2"));
        List<WebElement> listaH3s = driver.findElements(By.tagName("h3"));

        System.out.println("Lista de H1: ");
        for(WebElement element : listaH1s){
            System.out.println("H1: " + element.getText());
        }
        System.out.println("Lista de H2: ");
        for(WebElement element : listaH2s){
            System.out.println("H2: " + element.getText());
        }
        System.out.println("Lista de H3: ");
        for(WebElement element : listaH3s){
            System.out.println("H3: " + element.getText());
        }

        driver.navigate().refresh();

        List<WebElement> botones = driver.findElements(By.tagName("button"));

        System.out.println("Texto de botones: ");
        for(WebElement unBoton : botones){
            System.out.println("botón: " + unBoton.getText());
        }

        driver.manage().window().maximize();

        System.out.println("Titulo: " + driver.getTitle());

        List<WebElement> listaDivs = driver.findElements(By.tagName("div"));
        System.out.println("Cantidad de div: " + listaDivs.size());
        int nroDivs = listaDivs.size();

        List<WebElement> listaInputs = driver.findElements(By.tagName("input"));
        System.out.println("Cantidad de input: " + listaInputs.size());
        int nroInputs = listaInputs.size();

        List<WebElement> listaLinks = driver.findElements(By.tagName("a"));
        System.out.println("Cantidad de links: " + listaLinks.size());
        int nroLinks = listaLinks.size();

        System.out.println("El elemento que mas se repite es: " + getMostRepeated(nroDivs, nroInputs, nroLinks));
        driver.close();
    }

    public String getMostRepeated(int nroDivs, int nroInputs, int nroLinks){
        if(nroDivs > nroInputs && nroDivs > nroLinks){
            return "div";
        }
        if(nroInputs > nroDivs && nroInputs > nroLinks){
            return "input";
        }
        if(nroLinks > nroDivs && nroLinks > nroInputs){
            return "link";
        }
        return "div";
    }

    /******************************************** Ejercicio 16 ************************************************/
    /** CONSULTAR SI HAY QUE HACER ALGO CON GIT **/

    @Test
    public void testSesionNetflix(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.netflix.com/uy";
        driver.get(baseURL);

        driver.findElement(By.className("authLinks")).click();

        List<WebElement> listaH1s = driver.findElements(By.tagName("h1"));

        System.out.println("Lista de H1: ");
        for(WebElement element : listaH1s){
            System.out.println("H1: " + element.getText());
        }

        List<WebElement> listaH2s = driver.findElements(By.tagName("h2"));

        System.out.println("Lista de H2: ");
        for(WebElement element : listaH2s){
            System.out.println("H2: " + element.getText());
        }

        driver.navigate().back();
        driver.navigate().refresh();

        List<WebElement> listaDivs = driver.findElements(By.tagName("div"));

        System.out.println("Lista de divs: ");
        for(WebElement element : listaDivs){
            System.out.println("div: " + element.getText());
        }

        System.out.println("Titulo: " + driver.getTitle());

        List<WebElement> listaInputs = driver.findElements(By.tagName("input"));
        System.out.println("Cantidad de inputs: " + listaInputs.size());

        List<WebElement> listaLink = driver.findElements(By.tagName("a"));
        System.out.println("Cantidad de li: " + listaLink.size());

        driver.close();
    }

}
