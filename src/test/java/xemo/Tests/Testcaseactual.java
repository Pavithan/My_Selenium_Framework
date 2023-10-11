package xemo.Tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import xemo.Testcomponents.basetest;
import xemo.abstractcomponent.abstractcmpt;
import xemo.pageobjects.cartpage;
import xemo.pageobjects.checkoutpage;
import xemo.pageobjects.confirmationpage;
import xemo.pageobjects.landingpage;
import xemo.pageobjects.orderpage;
import xemo.pageobjects.productcatalg;

@SuppressWarnings("unused")
public class Testcaseactual extends basetest{
	
	String productname = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getdata",groups={"purchase"})
	public void finaltest(HashMap<String,String> input) throws IOException {
		
		productcatalg pdtcata = lndpage.loginapp(input.get("email"),input.get("password"));
		List<WebElement> shoplist = pdtcata.getshoplist();
		pdtcata.addtocart(input.get("productname"));
		
		cartpage ctpg = pdtcata.gotocartpage();
		
		Boolean match = ctpg.productsoncart(input.get("productname"));
		Assert.assertTrue(match);
		
		checkoutpage chout =ctpg.gtcheckout();
		chout.selectcountry("india");
		confirmationpage getconfirmation = chout.submit();
		String ordermsg = getconfirmation.confimationpage();
		Assert.assertTrue(ordermsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"finaltest"})
	public void orderhistory() {
		
		productcatalg pdtcata = lndpage.loginapp("sripavithran99@gmail.com", "Kuttima#1");
		orderpage ordpg = pdtcata.gotoorders();
		Assert.assertTrue(ordpg.verifyorders(productname));
		
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException 
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "sripavithran99@gmail.com");
//		map.put("password", "Kuttima#1");
//		map.put("productname", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "pavithran.n@slxlealrning.com");
//		map1.put("password", "Kuttima#1");
//		map1.put("productname", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getjsondata(System.getProperty("user.dir")+"\\src\\test\\java\\xemo\\data\\purchaseorder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	

}
