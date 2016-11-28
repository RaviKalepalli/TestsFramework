package page;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;
import util.PropertiesLoader;

public abstract class AbstractPage {
	
	static protected PropertiesLoader props = new PropertiesLoader();

	protected Logger LOGGER;
	
	protected String url;
	protected WebDriver driver;
	protected WebDriverWait wdwait;
	
	protected AbstractPage(){
		LOGGER = Logger.getLogger(this.getClass());
		
		driver = DriverManager.getDriver();
		wdwait = new WebDriverWait(driver, 5);
	}
	
	public void openPage(){
		if (url==null){
			String errorMessage = "AbstractPage url needs to be initialized - it was null";
			throw new RuntimeException(errorMessage);
		}
		
		LOGGER.info("Opening page "+url);
		driver.get(url);
	}
	
	abstract protected void verifyPage(); 
}
