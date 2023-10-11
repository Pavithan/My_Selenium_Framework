package xemo.resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentReports report() {
		
		//ExtentReports always needs ExtentSparkReporter
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);  //this is responsile for configuring the html report
		reporter.config().setReportName("Web Application Automation");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extreport = new ExtentReports();
		extreport.attachReporter(reporter);
		extreport.setSystemInfo("Tester", "Pavithran");
		return extreport;
		
		
	}
	
}
