package xemo.abstractcomponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import xemo.pageobjects.cartpage;
import xemo.pageobjects.orderpage;

public class abstractcmpt {
	
	WebDriver driver;

	public abstractcmpt(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement oncart;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement myorders;

	public void waitforvisibility(By findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
	}
	
	public void waitforwebelement(WebElement findby) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findby));
		
	}
	
	public void waitforelementdisappear(By elemnt) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(elemnt));
		
	}
	
	public cartpage gotocartpage() {
		oncart.click();
		cartpage ctpg = new cartpage(driver);
		return ctpg;
	}
	
	public orderpage gotoorders() {
		
		myorders.click();
		orderpage ordpg = new orderpage(driver);
		return ordpg;
		
	}
	
	
	
}
