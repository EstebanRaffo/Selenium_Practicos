package Practico8;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPracticeWithActions {

    String URL = "http://www.automationpractice.com/index.php";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get(URL);
    }

    @Test
    public void actionsTest() throws InterruptedException {
        WebElement loginBtn = driver.findElement(By.className("login"));

        Actions actions = new Actions(driver);
        actions.moveToElement(loginBtn).click().perform();

        Thread.sleep(4000);
        String emailToSend = "cursoSelenium" + Math.random() + "@gmail.com";
        WebElement emailField = driver.findElement(By.id("email_create"));

        Action completeEmailAddressField = actions.moveToElement(emailField)
                .click()
                .sendKeys(emailToSend)
                .build();
        completeEmailAddressField.perform();

        WebElement createAccountBtn = driver.findElement(By.id("SubmitCreate"));
        Action submitForm = actions.moveToElement(createAccountBtn).click().build();
        submitForm.perform();

        Thread.sleep(2000);
        WebElement firstNameInputField = driver.findElement(By.id("customer_firstname"));
        WebElement lastNameInputField = driver.findElement(By.id("customer_lastname"));
        WebElement genderCheckbox = driver.findElement(By.id("id_gender1"));

        Action clickGenderAction = actions.moveToElement(genderCheckbox).click().build();
        clickGenderAction.perform();

        Action fillFirstNameAction = actions.moveToElement(firstNameInputField)
                .click()
                .keyDown(firstNameInputField, Keys.SHIFT)
                .sendKeys("Martin")
                .keyUp(firstNameInputField, Keys.SHIFT)
                .build();
        fillFirstNameAction.perform();

        Action fillLastNameAction = actions.moveToElement(lastNameInputField)
                .click()
                .keyDown(lastNameInputField, Keys.SHIFT)
                .sendKeys("Smith")
                .keyUp(lastNameInputField, Keys.SHIFT)
                .build();

        fillLastNameAction.perform();
    }
}
