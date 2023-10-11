package xemo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xemo.abstractcomponent.abstractcmpt;

public class productcatalg extends abstractcmpt{


	WebDriver driver;
	
	public productcatalg(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> shoplist;
	
	By shoplistby = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastcont = By.cssSelector("#toast-container");
	By routerlink = By.cssSelector("[routerlink*='cart']");
	
	public List<WebElement> getshoplist(){
		
			waitforvisibility(shoplistby);
			return shoplist;
		
	}
	
	public WebElement getprodname(String productname) {
		
		WebElement prodct = shoplist.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prodct;
		
	}
	
	public void addtocart(String productname) {
		
		WebElement prodct = getprodname(productname);
		prodct.findElement(addtocart).click();
		waitforelementdisappear(toastcont);
		waitforvisibility(routerlink);
	}
	
	


}
