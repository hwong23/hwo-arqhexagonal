package dev.com;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-result"}, 
    features = "src/test/java/dev/com/features")
public class RunCucumberTest {
}
