package utilities;

import java.util.Properties;
import java.io.*;

public class ConfigurationReaders {

	private Properties prop;
	
	
	public Properties initialiseProperties() {
		prop = new Properties();
		try {
			FileInputStream reader = new FileInputStream("C:\\Users\\prashantrautela\\eclipse-workspace\\Framework\\Framework_Cucumber_A001\\src\\main\\java\\utilities\\config.properties");
			prop.load(reader);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	
}
