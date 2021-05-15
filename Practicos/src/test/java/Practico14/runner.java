package Practico14;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
     features = "C:/Users/Usuario/Documents/SeleniumAcademy/IdeaProjects/Practicos/src/test/java/Practico14/Feature",
     glue = "practico14/StepDef",
     plugin = {"pretty"},
     tags = "@error"

)
public class runner {

}
