import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Spotify_test {
    WebDriver driver;

    /************************************* Ejercicio 3 *******************************************************/
    private WebDriver getDriverSpotify(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.spotify.com/uy/signup";
        driver.get(baseURL);
        return driver;
    }

    @Test
    public void spotifyByNameTest(){
        driver = getDriverSpotify();

        WebElement email = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email.sendKeys("e.fraffo@hotmail.com");

        WebElement confirm = driver.findElement(By.xpath("//input[@name=\"confirm\"]"));
        confirm.sendKeys("e.fraffo@hotmail.com");

        WebElement pass = driver.findElement(By.xpath("//input[@name=\"password\"]"));
        pass.sendKeys("abc123456");

        WebElement displayName = driver.findElement(By.xpath("//input[@name=\"displayname\"]"));
        displayName.sendKeys("Esteban");

        WebElement dia = driver.findElement(By.xpath("//input[@name=\"day\"]"));
        dia.sendKeys("06");

        WebElement selectMes = driver.findElement(By.xpath("//select[@name=\"month\"]"));
        Select mes = new Select(selectMes);
        mes.selectByValue("02");

        WebElement año = driver.findElement(By.xpath("//input[@name=\"year\"]"));
        año.sendKeys("1987");

        /*WebElement radioMale = driver.findElement(By.xpath("//input[@name=\"gender\"][@value=\"male\"]"));*/
        WebElement radioMale = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/form/div[6]/div[2]/label[1]/span[1]"));
        radioMale.click();

        /*WebElement checkShare = driver.findElement(By.xpath("//input[@name=\"thirdParty\"]"));*/
        WebElement checkShare = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/form/div[7]/label/span[1]"));
        checkShare.click();
    }

    /************************************* Ejercicio 4 *******************************************************/
    @Test
    public void spotifyByPlaceHolder(){
        driver = getDriverSpotify();

        WebElement email = driver.findElement(By.xpath("//input[@placeholder=\"Introduce tu correo electrónico.\"]"));
        email.sendKeys("e.fraffo@hotmail.com");

        WebElement confirm = driver.findElement(By.xpath("//input[@placeholder=\"Vuelve a introducir tu correo electrónico.\"]"));
        confirm.sendKeys("e.fraffo@hotmail.com");

        WebElement pass = driver.findElement(By.xpath("//input[@placeholder=\"Crea una contraseña.\"]"));
        pass.sendKeys("abc123456");

        WebElement displayName = driver.findElement(By.xpath("//input[@placeholder=\"Introduce un nombre de perfil.\"]"));
        displayName.sendKeys("Esteban");

        WebElement dia = driver.findElement(By.xpath("//input[@placeholder=\"DD\"]"));
        dia.sendKeys("06");

        WebElement selectMes = driver.findElement(By.xpath("//select[@name=\"month\"]"));
        Select mes = new Select(selectMes);
        mes.selectByValue("02");

        WebElement año = driver.findElement(By.xpath("//input[@placeholder=\"AAAA\"]"));
        año.sendKeys("1987");

        WebElement radioMale = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/form/div[6]/div[2]/label[1]/span[1]"));
        radioMale.click();

        WebElement checkShare = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/form/div[7]/label/span[1]"));
        checkShare.click();
    }

    /************************************* Ejercicio 5 *******************************************************/
    @Test
    public void spotifyByName(){
        driver = getDriverSpotify();
        WebElement email = driver.findElement(By.cssSelector("input[name=\"email\"]"));
        email.sendKeys("e.fraffo@hotmail.com");

        WebElement confirm = driver.findElement(By.cssSelector("input[name=\"confirm\"]"));
        confirm.sendKeys("e.fraffo@hotmail.com");

        WebElement pass = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        pass.sendKeys("abc123456");

        WebElement displayName = driver.findElement(By.cssSelector("input[name=\"displayname\"]"));
        displayName.sendKeys("Esteban");

        WebElement dia = driver.findElement(By.cssSelector("input[name=\"day\"]"));
        dia.sendKeys("06");

        WebElement selectMes = driver.findElement(By.cssSelector("select[name=\"month\"]"));
        Select meses = new Select(selectMes);
        meses.selectByValue("02");

        WebElement año = driver.findElement(By.cssSelector("input[name=\"year\"]"));
        año.sendKeys("1987");

        WebElement radioMale = driver.findElement(By.cssSelector("span.Indicator-sc-16vj7o8-0.kSKYRE"));
        radioMale.click();

        WebElement checkShare = driver.findElement(By.cssSelector("span.Indicator-sc-11vkltc-0"));
        checkShare.click();
    }

    /************************************* Ejercicio 6 *******************************************************/
    @Test
    public void checkBoxTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.facebook.com";
        driver.get(baseURL);

        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(3000);

        WebElement radioFemale = driver.findElement(By.name("sex"));
        /*WebElement radioFemale = driver.findElement(By.cssSelector("input[name=\"sex\"]"));*/
        /*WebElement radioFemale = driver.findElement(By.xpath("//input[@name=\"sex\"]"));*/
        /*WebElement radioFemale = driver.findElement(By.xpath("//*[@id=\"u_f_2\"]"));*/
        /*WebElement radioFemale = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[7]/span/span[1]/input"));*/
        boolean radioFemaleIsSelected = radioFemale.isSelected();
        Assert.assertTrue(!radioFemaleIsSelected);

        WebElement radioMale = driver.findElement(By.cssSelector("[name=\"sex\"][value=\"2\"]"));
        boolean radioMaleIsSelected = radioMale.isSelected();
        Assert.assertTrue(!radioMaleIsSelected);

        WebElement radioCustom = driver.findElement(By.cssSelector("[name=\"sex\"][value=\"-1\"]"));
        boolean radioCustomIsSelected = radioCustom.isSelected();
        Assert.assertTrue(!radioCustomIsSelected);
        System.out.println("Ningún sexo seleccionado");

        radioMale.click();
        radioMaleIsSelected = radioMale.isSelected();
        Assert.assertTrue(radioMaleIsSelected);
        System.out.println("Sexo masculino seleccionado");
    }

    /************************************* Ejercicio 7 *******************************************************/
    /** VER GIT **/
    @Test
    public void spotifyByPlaceHolderCssSelector(){
        driver = getDriverSpotify();

        /** email **/
        WebElement email = driver.findElement(By.cssSelector("input[placeholder=\"Introduce tu correo electrónico.\"]"));
        email.sendKeys("e.fraffo@hotmail.com");
        /** confirm **/
        WebElement confirm = driver.findElement(By.cssSelector("input[placeholder=\"Vuelve a introducir tu correo electrónico.\"]"));
        confirm.sendKeys("e.fraffo@hotmail.com");
        /** pass **/
        WebElement pass = driver.findElement(By.cssSelector("input[placeholder=\"Crea una contraseña.\"]"));
        pass.sendKeys("abc123456");
        /** displayName **/
        WebElement displayName = driver.findElement(By.cssSelector("input[placeholder=\"Introduce un nombre de perfil.\"]"));
        displayName.sendKeys("Esteban");
        /** dia **/
        WebElement dia = driver.findElement(By.cssSelector("input[placeholder=\"DD\"]"));
        dia.sendKeys("06");
        /** selectMes **/
        WebElement selectMes = driver.findElement(By.cssSelector("select[name=\"month\"]"));
        Select meses = new Select(selectMes);
        meses.selectByValue("02");
        /** año **/
        WebElement año = driver.findElement(By.cssSelector("input[placeholder=\"AAAA\"]"));
        año.sendKeys("1987");

        /** radioMale **/
        WebElement radioMale = driver.findElement(By.cssSelector("span.Indicator-sc-16vj7o8-0.kSKYRE"));
        radioMale.click();
        /** checkShare **/
        WebElement checkShare = driver.findElement(By.cssSelector("span.Indicator-sc-11vkltc-0"));
        checkShare.click();
    }
}
