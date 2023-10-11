package xemo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xemo.abstractcomponent.abstractcmpt;

public class cartpage extends abstractcmpt{
	
	
WebDriver driver;
	
	public cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> listofproducts;
	
	@FindBy(css=".totalRow .btn.btn-primary")
	WebElement checkout;
	
	

	public Boolean productsoncart(String productname) {
		
		Boolean match = listofproducts.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productname));
		return match;
		
	}
	
	public checkoutpage gtcheckout() {
		
		checkout.click();
		return new checkoutpage(driver);
		
	}
	
	

}
