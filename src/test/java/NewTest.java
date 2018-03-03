import org.testng.annotations.Test;

import Utils.Mail;
import pages.ProductPage;
import pages.ResultsPage;
import pages.SearchPage;

import org.testng.annotations.BeforeTest;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class NewTest {
	String searchTxt = "amazon echo";
	String URLuk = "https://www.amazon.co.uk";////to move to paramters 
	String URLCom = "https://www.amazon.com";//to move to paramters
	double price,prevPrice;
	String partEmailBody="" ,totalEmailBody="", delimiter;//TODO - remove and use testng parameter will be $ or £ accordinngly
	WebDriver driver;
	SearchPage search;
	ResultsPage results;
	ProductPage product;
	
	@BeforeTest
	public void setup()
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//init the delimiter'£' 
	}
	@AfterTest
	public void tireUp()
	{
		driver.manage().deleteAllCookies();
		driver.quit();
		Reporter.log(getClass().getName()+ ":Quiting browser. ==", true);
	}
	
	//@Parameters({ "rate", "Url" }) double rate, String Url
	public void senario(double exRate , String URL , String delimiter) throws Exception
	{	
		
		//setup();
		driver.get(URL);
		
		//*************create Page Objects************* 
		search = new SearchPage(driver);
		results = new ResultsPage(driver);
		product = new ProductPage(driver);
		
		//**********Run actions on Page Objects*************
		search.typeText(searchTxt);
		search.clickOnSerachBtn();
		partEmailBody += "Result count in " + URL + " is " + results.resultsCount();		
		results.clickOnresultLnk();
		partEmailBody += ". Stock availability is " + product.getItemStockdate();
		getCheaperProduct(product.getItemPrice(), exRate , URL);

		//tireUp();
	}
	@Test
	public void runSenarios( )
	{
		try {
			senario(4.83, URLuk, "'£'");
			senario(3.41, URLCom, "'$'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!partEmailBody.isEmpty())
		{
			Reporter.log("Email partEmailBody is: " + partEmailBody +"==", true);
			validateResults();
			
		
		}
	}
	/**
	 * priceIsLower - Check price is lower or equals to previous one
	 * @param price - current price
	 * @param prevPrice - price from previous run
	 * @return true if price is lower
	 */
	public boolean priceIsLower(double price ,double prevPrice)
	{	
		return prevPrice >= price;
	}
	
	/**
	 * getCheaperProduct - addes to the mail partEmailBody the cheaper product and where can be bought
	 * @param exRate - the exchanging rate from currency to NIS
	 */
	public void getCheaperProduct(String Sprice , double exRate,String URL)
	{
		price = Double.parseDouble(Sprice);
		if (priceIsLower(price*exRate ,prevPrice*exRate))
			partEmailBody += ". Product Price " + price*exRate + " is cheaper , recommand to buy in " + URL;
		else if (prevPrice !=0) 
		{
			partEmailBody += ". Product Price " + prevPrice*exRate + " is cheaper , recommand to buy in " + URL;
		}
		prevPrice = price;
		totalEmailBody += partEmailBody;
	}
	
	@AfterTest
	public void validateResults()
	{
		Mail mail = new Mail();
		try {
			mail.sendMail(totalEmailBody, "Testing Amazon.Uk Vs Amazon.US");
			
		} catch (EmailException e) {
			Reporter.log("==========Unable to send an Email Please check email details such as host, port ", true);
		}
		Reporter.log("==Email sent , please check your mailbox. ==", true);
	}

}
