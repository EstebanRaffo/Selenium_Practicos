
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:/Users/Usuario/Documents/SeleniumAcademy/IdeaProjects/Practico14/src/test/java/Feature",
        glue = "Steps",
        plugin = {"pretty"},
        tags = "@important or @billing"
)


public class runner {
}
