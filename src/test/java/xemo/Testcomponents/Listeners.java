package xemo.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import xemo.resourses.ExtentReportTestNG;

public class Listeners extends basetest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extnt = ExtentReportTestNG.report();
	ThreadLocal<ExtentTest> threadid = new ThreadLocal<ExtentTest>();
	
	    // This method will be invoked before a test case starts
	    @Override
	    public void onTestStart(ITestResult result) {
	    	
	    	test = extnt.createTest(result.getMethod().getMethodName());
	    	threadid.set(test);
	        
	    }

	    // This method will be invoked after a test case succeeds
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        
	        test.log(Status.PASS, "Test Passed");
	    }

	    // This method will be invoked after a test case fails
	    @Override
	    public void onTestFailure(ITestResult result) {
	        //SCREENSHOT
	    	threadid.get().fail(result.getThrowable());
	        try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        String filepath = null;
			try {
				filepath = takescreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			threadid.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

	        
	    }

	    // This method will be invoked after a test case is skipped
	    @Override
	    public void onTestSkipped(ITestResult result) {
	    }
	    
	    // This method will be invoked after a test case is retried
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    }
	    
	    // This method will be invoked after a test case fails due to a timeout
	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {
	    }
	    
	    // This method will be invoked before the test suite starts
	    @Override
	    public void onStart(ITestContext context) {
	    }

	    // This method will be invoked after the test suite finishes
	    @Override
	    public void onFinish(ITestContext context) {
	        
	        extnt.flush();
	        
	    }

}
