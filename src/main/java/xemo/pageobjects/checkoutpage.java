package xemo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import xemo.abstractcomponent.abstractcmpt;

public class checkoutpage extends abstractcmpt{
	
	
	WebDriver driver;
	
	public checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder*='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickcountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectcountry(String countryname) {
		
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryname).build().perform();
		waitforvisibility(results);
		clickcountry.click();
	}
	
	public confirmationpage submit() {
		
		submit.click();
		return new confirmationpage(driver);
	}
	

}
