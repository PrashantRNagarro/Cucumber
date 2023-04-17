package stepsDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Driver;
import pages.LoginPage;

public class loginStepsDefinitions {
	
	
	
	LoginPage loginPage = new LoginPage(Driver.getDriver());

	

	@When("User clicks on Service link.")
	public void user_clicks_on_service_link() {
	    
		loginPage.clickServiceLink();
	}
	
	@When("User clicks on Accelerated Quality service link.")
	public void user_clicks_on_Accelerated() {
	    
		 {
			loginPage.clickAcceleratedQualityService();
			loginPage.clickROI();
			loginPage.clickLetFindOut();
		} 
	}
	
	@When("User clicks on Lets Find Out button.")
	public void user_clicks_on_FindOutButton() {
	    
		 {
			
			loginPage.clickLetFindOut();
		} 
	}
	
	
}
