package xemo.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import xemo.pageobjects.landingpage;

@SuppressWarnings("unused")
public class basetest {
	
	public WebDriver driver;
	public landingpage lndpage;
		
	public WebDriver  inidriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream filepath = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\xemo\\resourses\\global.properties");
		prop.load(filepath);
		
		String browsername = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browsername = prop.getProperty("browser");
		
		if (browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("chrome")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browsername.equalsIgnoreCase("firefox")){
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
		}
		else if(browsername.equalsIgnoreCase("edge")){
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		return driver;
		
	}
	
	public List<HashMap<String, String>> getjsondata(String filepath) throws IOException
	{
		//read json as string
		String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		// String to Hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){
		});
		
		return data;
		
	}
	
	public String takescreenshot(String testcasename,WebDriver driver) throws IOException {
		
		TakesScreenshot scrnshot = (TakesScreenshot)driver;
		File source = scrnshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
		
	}
	
	
		@BeforeMethod
		public landingpage launchapplication() throws Exception{
		
			driver = inidriver();
			lndpage = new landingpage(driver);
			lndpage.gotoo();
			return lndpage;
		
			}
		@AfterMethod
			public void driverclose() {
			
				driver.close();
			}
	


	}

