package Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

public class DriverManager {
	
	public WebDriver Instance  = null;
	private String browser  = "ff";
	private long timeout = 5;
	private FirefoxOptions firefoxOptions = null;
	public String URL = null;
	
	public WebDriver getDriverInstance() {
	return Instance;
	}
	
	@BeforeTest
	public void initialize() {
		Reporter.log("==========driver Initalization=========", true);
		if (Instance != null )
			return;
			switch (browser)
			{
			case "ff":
				firefoxOptions = new FirefoxOptions();
			    firefoxOptions.setCapability("marionette", true);
			    Instance = new FirefoxDriver(firefoxOptions);
			    break;
			case "chrome":
				//System.getProperty("user.dir");
				//TODO - define path in the configuratonFile
				break;
				default:
			}
			Instance.manage().window().maximize();
			Instance.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

}