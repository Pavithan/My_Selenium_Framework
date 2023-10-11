package xemo.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xemo.abstractcomponent.abstractcmpt;

public class orderpage extends abstractcmpt{

	WebDriver driver;
	
	public orderpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productlist;
	
	public Boolean verifyorders(String productname) {
		
		Boolean match = productlist.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
	
	
	
}
