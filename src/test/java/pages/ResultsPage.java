package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ResultsPage extends BasePage{

	 
	By results = By.cssSelector("span[id*='s-result-count']");//old value by xpath://span[@id='s-result-count']
	By resultImg = By.cssSelector("img[alt*='Black'][alt*='2nd']"); 
	
	public ResultsPage(WebDriver driver) {
	
		super(driver);
	}
	 
		/**
		 * clickOnresultLnk - Click on the results link locator
		 */
		public void clickOnresultLnk(){
		
         driver.findElement(resultImg).click();
         Reporter.log("==========On Results page=========", true);
         Reporter.log("==========Click on choosen Item=========", true);
         }
		/**
		 * Count the number of results after search
		 * @return
		 */
		public String resultsCount(){
			 String result;
			 String elemVal = driver.findElement(results).getText();
	         result =  Utils.Spliter.splitByDelimiter(elemVal," ",3);
	         Reporter.log("==========resultsCount is " + result +"=========", true);
	         return result;
	         }
		/**
		 * titlePresent - Check for given title , if it presents
		 * @param title - title of the page
		 * @return - true if title presents
		 */
		public boolean isTitlePresent(String title){
			 boolean pageRedirected = false;
	         pageRedirected = driver.getTitle().equals(title);
	         Reporter.log("==========txtSearch find element=========" + driver.getTitle() +"=========", true);
	   
	         return pageRedirected;
	         }
		

}
