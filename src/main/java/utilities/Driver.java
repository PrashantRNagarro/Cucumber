package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Driver {

	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
	
	public WebDriver intialiseDriver(String browsername) {
		if(browsername.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver());
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
}
