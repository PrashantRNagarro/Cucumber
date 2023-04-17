package runnerPackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {"@target/failedTest.txt"},
		glue = {"stepsDefinitions", "hooks"},
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				}
		
		)


public class FailedRunner {

}
