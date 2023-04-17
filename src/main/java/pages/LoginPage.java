package pages;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;

public class LoginPage {

	private WebDriver driver;

	// locators

	private By lnkService = By.linkText("services");
	private By linkAcceleratedQuality = By.linkText("accelerated quality & test-engineering");
	private By ROIElement = By.xpath("//span[contains(text(),'Find out your ROI')]");
	Actions action;
	private By cardROI = By.xpath("(//div[@class='card-overlay'])[2]");
	private By letsFindOut = By.linkText("Let’s find out!");
	
	
	
	// Methods

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
	}

	public void clickServiceLink() {

		action.moveToElement(driver.findElement(lnkService)).build().perform();
	}

	public void clickAcceleratedQualityService() {

		action.moveToElement(driver.findElement(linkAcceleratedQuality)).build().perform();
		action.click().build().perform();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickROI() {

		WebElement cardROIs = driver.findElement(cardROI);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", cardROIs);
		
		action.moveToElement(driver.findElement(cardROI)).build().perform();
		//action.moveToElement(driver.findElement(ROIElement)).build().perform();
		action.click().build().perform();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println(driver.getCurrentUrl());
	}
	
	
	public void clickLetFindOut() {
		
		driver.findElement(letsFindOut).click();
		
	}

}
