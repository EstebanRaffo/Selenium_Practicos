import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WebDriverHelper {

    // getElementsWithIds
    // getElementsWithNames
    public static List<WebElement> getElementsWithAttribute(WebDriver remoteDriver, String tagName, String attribute){
        List<WebElement> elements = remoteDriver.findElements(By.tagName(tagName));
        List<WebElement> elementsWithAttribute = new ArrayList<WebElement>();

        for(WebElement element : elements){
            if(!element.getAttribute(attribute).isEmpty()){
                elementsWithAttribute.add(element);
            }
        }

        return elementsWithAttribute;
    }

    public static List<WebElement> getValidLinks(WebDriver remoteDriver){
        List<WebElement> links = remoteDriver.findElements(By.tagName("a"));
        List<WebElement> valid_links = new ArrayList<WebElement>();

        for(WebElement element : links){
            if(!element.getAttribute("href").isEmpty()){
                valid_links.add(element);
            }
        }

        return valid_links;
    }

    public static List<WebElement> getBrokenLinks(WebDriver remoteDriver){
        List<WebElement> links = remoteDriver.findElements(By.tagName("a"));
        List<WebElement> broken_links = new ArrayList<WebElement>();

        for(WebElement element : links){
            if(element.getAttribute("href").isEmpty()){
                 broken_links.add(element);
            }
        }

        return broken_links;
    }

    public static List<WebElement> getButtons(WebDriver remoteDriver){
        List<WebElement> buttons = remoteDriver.findElements(By.tagName("button"));
        List<WebElement> buttonsWithText = new ArrayList<WebElement>();

        for(WebElement element : buttons){
            if(!element.getText().isEmpty()){
                buttonsWithText.add(element);
            }
        }

        return buttonsWithText;
    }

    public static List<WebElement> getDisplayedButtons(WebDriver remoteDriver){
        List<WebElement> buttons = remoteDriver.findElements(By.tagName("button"));
        List<WebElement> displayed_buttons = new ArrayList<WebElement>();

        for(WebElement element : buttons){
            if(element.isDisplayed()){
                displayed_buttons.add(element);
            }
        }

        return displayed_buttons;
    }

    public static WebElement getElementByText(WebDriver remoteDriver, String text){
        return remoteDriver.findElement(By.xpath("//*[contains(text(), '"+ text +"')]"));
    }

    public static WebElement getElementByName(WebDriver remoteDriver, String name){
        return remoteDriver.findElement(By.name(name));
    }

    public static WebElement getElementById(WebDriver remoteDriver, String id){
        return remoteDriver.findElement(By.id(id));
    }

    public static List<WebElement> getElementsByTagName(WebDriver remoteDriver, String tagName){
        return remoteDriver.findElements(By.tagName(tagName));
    }

    public static WebElement getElementByXpath(WebDriver remoteDriver, String attribute, String value){
        return remoteDriver.findElement(By.xpath("//*[@"+attribute+"='"+value+"']"));
    }

    public static WebElement getElementByXpathWithTagName(WebDriver remoteDriver, String tagName, String attribute, String value){
        return remoteDriver.findElement(By.xpath("//"+tagName+"[@"+attribute+"='"+value+"']"));
    }

    public static List<WebElement> getElementsByXpath(WebDriver remoteDriver, String attribute, String value){
        return remoteDriver.findElements(By.xpath("//*[@"+attribute+"='"+value+"']"));
    }

    public static List<WebElement> getElementsByXpathWithTagName(WebDriver remoteDriver, String tagName, String attribute, String value){
        return remoteDriver.findElements(By.xpath("//"+tagName+"[@"+attribute+"='"+value+"']"));
    }

    public static void sleepSeconds(int delay) throws InterruptedException {
        try{
            Thread.sleep(delay * 1000);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver implicitWait(WebDriver remoteDriver, int delay){
        remoteDriver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
        return remoteDriver;
    }

    public static void validateInvalidLinks(WebDriver remoteDriver){
        List<WebElement> allLinks = remoteDriver.findElements(By.tagName("a"));
        int invalid_links = 0;
        System.out.println("Total de links: " + allLinks.size());

        int invalid_status = 0;


        for(WebElement aLink : allLinks){
            if(aLink != null){
                String url = aLink.getAttribute("href");
                if(url != null && !url.contains("javascript")){
                    invalid_status = verifyURLStatus(url);
                }
                else{
                    invalid_links++;
                }
            }
        }
        int total_invalid_links = invalid_status + invalid_links;
        System.out.println("Total de Links inválidos: " + total_invalid_links);
    }

    // https://www.vogella.com/tutorials/ApacheHttpClient/article.html
    // https://docs.microsoft.com/en-us/java/api/org.apache.hc.client5.http.impl.classic.httpclientbuilder?view=azure-java-preview
    public static int verifyURLStatus(String URL){
        int invalid_status = 0;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);

        try{
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() != 200){
                invalid_status++;
            }
        }catch (Exception e){
            System.out.println("Ha ocurrido una excepcion");
            e.printStackTrace();
        }
        return invalid_status;
    }

    public static void showInvalidButtons(WebDriver remoteDriver){
        List<WebElement> allButtons = remoteDriver.findElements(By.tagName("button"));
        System.out.println("Total de Botones: " + allButtons.size());

        int empty_buttons = 0;
        int no_id_buttons = 0;
        int no_name_buttons = 0;

        for(WebElement aButton : allButtons){
            if(aButton != null){
                if(aButton.getText().isEmpty()){
                    empty_buttons++;
                }
                if(aButton.getAttribute("id").isEmpty()){
                    no_id_buttons++;
                }
                if(aButton.getAttribute("name").isEmpty()){
                    no_name_buttons++;
                }
            }
        }

        System.out.println("Total de botones sin texto: " + empty_buttons);
        System.out.println("Total de botones sin is: " + no_id_buttons);
        System.out.println("Total de botones sin nombre: " + no_name_buttons);
    }

    public static String getNameAttribute(WebElement element){
        return element.getAttribute("name");
    }

    public static String getIdAttribute(WebElement element){
        return element.getAttribute("id");
    }

    public static String getClassAttribute(WebElement element){
        return element.getAttribute("class");
    }

    public static String getLabelAttribute(WebElement element){
        return element.getAttribute("aria-label");
    }

    public static String getAttribute(WebElement element, String attribute){
        return element.getAttribute(attribute);
    }
}
