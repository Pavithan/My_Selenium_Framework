package xemo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import xemo.abstractcomponent.abstractcmpt;

public class confirmationpage extends abstractcmpt{
	
	
	WebDriver driver;

	public confirmationpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement textview;
	
	public String confimationpage() {
		
		return textview.getText();
	}
	
	

}
