package xemo.stepdefenition;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import xemo.Testcomponents.basetest;
import xemo.pageobjects.cartpage;
import xemo.pageobjects.checkoutpage;
import xemo.pageobjects.confirmationpage;
import xemo.pageobjects.landingpage;
import xemo.pageobjects.productcatalg;

public class stepdefenition extends basetest{
	
	public landingpage lndpage;
	public productcatalg pdtcata;
	public confirmationpage getconfirmation;
	
	@Given("I landed on Ecommerce page.")
	public void landing_Page() throws Exception{
		
		lndpage = launchapplication();
			
	}
	
	@Given("Logged in with {string} and {string}")
	public void loggin_with_username_and_password(String username, String password) {
		
	pdtcata = lndpage.loginapp(username, password);
		
	}
	
	
	@When("^I add the product (.+) to cart$")
	public void add_the_product(String product) {
		
		List<WebElement> shoplist = pdtcata.getshoplist();
		pdtcata.addtocart(product);
		
	}
	
	@When("^Checkout (.+) and submit the order$") //for AND also we usually use WHEN
	public void submit_the_order(String product) {
		
		cartpage ctpg = pdtcata.gotocartpage();
		
		Boolean match = ctpg.productsoncart(product);
		Assert.assertTrue(match);
		
		checkoutpage chout =ctpg.gtcheckout();
		chout.selectcountry("india");
		getconfirmation = chout.submit();	
	}
	
	@Then("{string} message is displayed in the confirmation page.")
	public void confirmation_message(String string) {
		
		String ordermsg = getconfirmation.confimationpage();
		Assert.assertTrue(ordermsg.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed.")
	public void Error_validation(String string1) {
		
		Assert.assertEquals(string1,lndpage.geterrormsg());
		driver.close();
	}
	

}
