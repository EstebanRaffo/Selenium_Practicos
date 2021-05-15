package Practico14;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
     features = "/Users/Selenium/Desktop/CursoMartesIntermedio/src/test/java/practico14/Feature",
     glue = "practico14/StepDef",
     plugin = {"pretty"},
     tags = "@error"

)
public class runner {

}
