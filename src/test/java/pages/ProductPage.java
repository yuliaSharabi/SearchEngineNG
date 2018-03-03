package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ProductPage {
	 
	WebDriver driver;
	 
	By productStockDate= By.cssSelector("#availability span");//old val xpath:"//span[contains(., 'stock') or contains(., 'Stock') ]");
	By productPrice = By.cssSelector("span[id*='_ourprice']");
	
	
	public ProductPage(WebDriver driver) {
	
		this.driver = driver;
	}
		/**
		 * getItemPrice - get the String locator for item price
		 * @return String with resu
		 */
		public String getItemPrice(){
			 String priceStr;
			 String elemVal = driver.findElement(productPrice).getText();
	         Reporter.log("==========Item Price is " + elemVal + "=======", true);
	         priceStr = elemVal.substring(1, elemVal.length());
	         return priceStr;
	        }
		/**
		 * getItemStockdate - Get the Item's stock date.
		 * Default value is "In Stock"
		 * @return date if product in stock, otherwise returns "In Stock"
		 */
		public String getItemStockdate(){
			String elemVal = "In Stock";
			 String temp = driver.findElement(productStockDate).getText();
	         if (!elemVal.equals(null))
	        	 elemVal = temp;
			 Reporter.log("==========Stockdate is " + elemVal +"=========", true);
	         return elemVal;
	         }
	
		/**
		 * titlePresent - Check for given title , if it presents
		 * @param title - title of the page
		 * @return - true if title presents
		 */
		public boolean titlePresent(String title){
			 boolean pageRedirected = false;
	         pageRedirected = driver.getTitle().equals(title);
	         Reporter.log("==========title Presnt for ProductPage======", true);
	   
	         return pageRedirected;
	         }

}
