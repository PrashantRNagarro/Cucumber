package runnerPackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {"src\\test\\resources\\features"},
		glue = {"stepsDefinitions", "hooks"},
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failedTest.txt"}
		
		
		)

//tags="not @skip"
// use rerun plugin to run the failed scenarios.
// create new runner class just to run only failed scenarios.

public class TestRunner {

	
	
	
	
}
