import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:/Users/Usuario/Documents/SeleniumAcademy/IdeaProjects/Practico15/src/test/java/features",
        glue = "steps",
        plugin = {"pretty"},
        tags = "@basic"
)

public class runner {

}
