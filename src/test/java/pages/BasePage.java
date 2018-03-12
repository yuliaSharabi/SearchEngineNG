package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;  
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	BasePage(WebDriver driver) {
		setDriver(driver);	
	}

}
