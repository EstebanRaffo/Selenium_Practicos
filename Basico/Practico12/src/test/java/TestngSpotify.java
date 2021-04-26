import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.*;

public class TestngSpotify {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String baseURL = "https://www.spotify.com/uy/";
        driver.get(baseURL);
    }

    @Test (priority = 5)
    public void verifySpotifyTitle(){
        String title = "Escuchar es todo - Spotify";
        String currentTitle = driver.getTitle();
        Assert.assertEquals(currentTitle, title);
        System.out.println("Titulo obtenido: " + currentTitle);
    }

    @Test (priority = 4)
    public void verifyDignupUrl(){
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.spotify.com/uy/signup/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test (priority = 3)
    public void invalidEmailTest() throws InterruptedException {
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("test.com");
        driver.findElement(By.id("confirm")).sendKeys("test.com");
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), \"Este correo electrónico no es válido.\")]"));
        String expectedErrorMessage = "Este correo electrónico no es válido. Asegúrate de que tenga un formato como este: ejemplo@email.com";
        String errorMessageText = errorMessage.getText();
        Assert.assertEquals(errorMessageText, expectedErrorMessage);
    }

    @Test (priority = 2)
    public void validateExistingEmail() throws InterruptedException {
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("test@test.com");
        driver.findElement(By.id("confirm")).sendKeys("test@test.com");
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(),\"Este correo electrónico ya está conectado a una cuenta.\")]"));
        String expectedErrorMessage = "Este correo electrónico ya está conectado a una cuenta. Inicia sesión.";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }

    @Test (priority = 1)
    public void checkEqualEmailsError() throws InterruptedException {
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();

        Thread.sleep(3000);
        driver.findElement(By.id("email")).sendKeys("test999a@test.com");
        driver.findElement(By.id("confirm")).sendKeys("hola@hola.com");
        driver.findElement(By.id("password")).sendKeys("123");
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),\"Las direcciones de correo electrónico no coinciden.\")]"));
        String expectedErrorMessage = "Las direcciones de correo electrónico no coinciden.";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }

    private static final String EMAIL_ERROR = "Es necesario que introduzcas tu correo electrónico.";
    private static final String CONFIRMATION_ERROR = "Es necesario que confirmes tu correo electrónico.";
    private static final String PASS_ERROR = "Debes introducir una contraseña.";
    private static final String PROFILE_ERROR = "Introduce un nombre para tu perfil.";
    private static final String BIRTH_DAY_ERROR = "Indica un día del mes válido.";
    private static final String BIRTH_MONTH_ERROR = "Selecciona tu mes de nacimiento.";
    private static final String BIRTH_YEAR_ERROR = "Indica un año válido.";
    private static final String SEX_ERROR = "Selecciona tu sexo.";
    private static final String CAPTCHA_ERROR = "Confirma que no eres un robot.";
    
    @Test (priority = 0)
    public void checkErrorMessages() throws InterruptedException {
        WebElement registrar = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/nav/ul/li[5]/a"));
        registrar.click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(),\"Registrarte\")]")).click();

        WebElement email_message = driver.findElement(By.xpath("//span[contains(text(),\"Es necesario que introduzcas tu correo electrónico.\")]"));
        WebElement confirm_message = driver.findElement(By.xpath("//div[contains(text(),\"Es necesario que confirmes tu correo electrónico.\")]"));
        WebElement pass_message = driver.findElement(By.xpath("//div[contains(text(),\"Debes introducir una contraseña.\")]"));
        WebElement profile_name_message = driver.findElement(By.xpath("//div[contains(text(),\"Introduce un nombre para tu perfil.\")]"));
        WebElement birth_date_message = driver.findElement(By.xpath("//div[contains(text(),\"Indica un día del mes válido.\")]"));
        WebElement birth_month_message = driver.findElement(By.xpath("//div[contains(text(),\"Selecciona tu mes de nacimiento.\")]"));
        WebElement birth_year_message = driver.findElement(By.xpath("//div[contains(text(),\"Indica un año válido.\")]"));
        WebElement sex_message = driver.findElement(By.xpath("//div[contains(text(),\"Selecciona tu sexo.\")]"));
        WebElement captcha_message = driver.findElement(By.xpath("//div[contains(text(),\"Confirma que no eres un robot.\")]"));

        Assert.assertEquals(email_message.getText(), EMAIL_ERROR);
        Assert.assertEquals(confirm_message.getText(), CONFIRMATION_ERROR);
        Assert.assertEquals(pass_message.getText(), PASS_ERROR);
        Assert.assertEquals(profile_name_message.getText(), PROFILE_ERROR);
        Assert.assertEquals(birth_date_message.getText(), BIRTH_DAY_ERROR);
        Assert.assertEquals(birth_month_message.getText(), BIRTH_MONTH_ERROR);
        Assert.assertEquals(birth_year_message.getText(), BIRTH_YEAR_ERROR);
        Assert.assertEquals(sex_message.getText(), SEX_ERROR);
        Assert.assertEquals(captcha_message.getText(), CAPTCHA_ERROR);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Ejecución de los test finalizada.");
/*        driver.close();*/
    }
}
