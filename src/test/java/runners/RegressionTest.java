package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", glue = "com.matrixmedicalnetwork.bdd.stepdefinitions", monochrome = true, tags = "@regression", plugin = {
        "pretty", "json:target/cucumber.json" })

public class RegressionTest {

}