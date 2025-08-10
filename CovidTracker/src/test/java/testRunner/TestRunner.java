package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    		glue = {"stepDefinitions"},  // Ensure this points to the correct package
    		plugin = {
    			    "pretty",
    			    "html:target/cucumber-reports.html",
    			    "json:target/cucumber.json",
    			    "junit:target/cucumber.xml"
    			},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // Enables parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
