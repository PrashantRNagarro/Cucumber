package hooks;

import static org.junit.Assume.assumeTrue;

import java.util.Collection;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import utilities.ConfigurationReaders;
import utilities.Driver;

public class ApplicaionHooks {

	private Driver driverFactory;
	private WebDriver driver;
	private ConfigurationReaders configreader;
	Properties prop;
	
	@Before(order=0)
	public void readFile() {
		
		configreader = new ConfigurationReaders();
		prop = configreader.initialiseProperties();
		
	}
	
	@Before(order = 0)
	public void skipScenarios(Scenario scenario) {
		
		Collection<String> scenario_tags = scenario.getSourceTagNames();
		
		if (scenario_tags.contains("@skip")) {
			assumeTrue(false);
		}
		
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		
		String browsername = prop.getProperty("browsername");
		driverFactory = new Driver();
		driver = driverFactory.intialiseDriver(browsername);
		
		
	}
	
	@Given("User launches the application URL.")
	public void user_launches_the_application_url() {
	    
		String application_URL = prop.getProperty("URL");
		driver.get(application_URL);
	}
	
	@After(order = 0)
	public void closeDriver() {
		driver.quit();
		
	}
	
	@After(order = 1)
	public void takeScreenshot(Scenario scenario) {
		
		if (scenario.isFailed()) {
			String scenarioName = scenario.getName().replaceAll(" ", "_");
			byte[] screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotFile, "image/png", scenarioName);
			
			
		}
	}
	
	
	
}
