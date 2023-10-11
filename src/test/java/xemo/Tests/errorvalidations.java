package xemo.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import xemo.Testcomponents.basetest;
import xemo.Testcomponents.retry;
import xemo.pageobjects.cartpage;
import xemo.pageobjects.productcatalg;

public class errorvalidations extends basetest{
	
	
	@Test(retryAnalyzer=retry.class)
	public void errorcatch() {
		
	String productname = "ADIDAS ORIGINAL";
	productcatalg pdtcata = lndpage.loginapp("sripavith99@gmail.com", "Kuttia#1"); //testing with wrong email and passwd
	Assert.assertEquals("Incorrect email or password.",lndpage.geterrormsg());
	
	}
	
	@Test
	public void errorcatching() {
		
		String productname = "ADIDAS ORIGINAL";
		
		productcatalg pdtcata = lndpage.loginapp("sripavithran99@gmail.com", "Kuttima#1");
		List<WebElement> shoplist = pdtcata.getshoplist();
		pdtcata.addtocart(productname);
		
		cartpage ctpg = pdtcata.gotocartpage();
		
		Boolean match = ctpg.productsoncart("ADIDAS ORIGINAL111"); //intentionally making error
		Assert.assertFalse(match);
		
		}
	
}
