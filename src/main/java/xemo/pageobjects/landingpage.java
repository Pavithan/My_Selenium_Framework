package xemo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xemo.abstractcomponent.abstractcmpt;

public class landingpage extends abstractcmpt{

	WebDriver driver;
	
	public landingpage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}


//WebElement email = driver.findElement(By.cssSelector("#userEmail"));
	
	@FindBy(css="#userEmail")
	WebElement userEmail;
	
	@FindBy(css="#userPassword")
	WebElement userPassword;
	
	@FindBy(css="#login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	public productcatalg loginapp(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		productcatalg pdtcata = new productcatalg(driver);
		return pdtcata;
	}
	
	public String geterrormsg() {
		
		waitforwebelement(errormsg);
		return errormsg.getText();
	}
	
	public void gotoo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	

}
