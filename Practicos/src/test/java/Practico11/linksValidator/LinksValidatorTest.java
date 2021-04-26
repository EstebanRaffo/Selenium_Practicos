package Practico11.linksValidator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LinksValidatorTest {

    public WebDriver driver;
    private int invalidLinksCount;
/*
ADD
<!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.3.0</version>
            <scope>test</scope>
        </dependency>
 */

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
        driver = new OperaDriver();
        driver.get("http://www.seleniumacademy.net");
    }

    @Test
    public void validateInvalidLinks() {
        invalidLinksCount = 0;
        List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
        System.out.println("Total de links: " + anchorTagsList.size());
        for (WebElement anchorTagElement : anchorTagsList) {
            if (anchorTagElement != null) {
                String url = anchorTagElement.getAttribute("href");
                if (url != null && !url.contains("javascript")) {
                    verifyURLStatus(url);
                } else {
                    invalidLinksCount++;
                }
            }
        }
        System.out.println("Total de links invalidos: " + invalidLinksCount);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public void verifyURLStatus(String URL) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        try {
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200)
                invalidLinksCount++;
        } catch (Exception e) {
            System.out.println("Ha ocurrido una excepcion");
            e.printStackTrace();
        }
    }



}
