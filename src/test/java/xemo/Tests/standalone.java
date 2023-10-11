package xemo.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalone {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("sripavithran99@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Kuttima#1");
		driver.findElement(By.cssSelector("#login")).click();
		
		List<WebElement> shoplist = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prodct = shoplist.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prodct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> oncart = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean torf = oncart.stream().anyMatch(prod->prod.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(torf);
		
		driver.findElement(By.cssSelector(".totalRow .btn.btn-primary")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-results")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String ordermsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ordermsg.equalsIgnoreCase("THANK YOU FOR THE ORDER."));
	}

}
