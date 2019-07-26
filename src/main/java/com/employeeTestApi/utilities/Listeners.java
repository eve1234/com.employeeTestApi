package com.employeeTestApi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//suppose testcase fails, what next action
public class Listeners extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/empReport.html") ; //specify location
		
		htmlReporter.config().setDocumentTitle("employee api report");  // title of report
		htmlReporter.config().setReportName("employ rest API report" ); //name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "eve");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName()); //create new entry in the report
		
		test.log(Status.PASS, "Test Case Passed is "+ result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());//create new entry in the report
		
		test.log(Status.FAIL, "Test Case FAILED is "+ result.getName()); //TO ADD name to extend report
		test.log(Status.FAIL, "Test Case FAILED is "+ result.getThrowable()); //TO add error/exception in extent report
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED is "+ result.getName()); //TO ADD name to extend report
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
