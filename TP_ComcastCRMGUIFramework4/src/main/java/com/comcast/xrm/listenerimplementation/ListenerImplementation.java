package com.comcast.xrm.listenerimplementation;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementation implements ITestListener,ISuiteListener
{
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) 
	{
		String time = new Date().toString().replace(" ","_").replace(":", "_");

		spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		
		//Add environmental and Create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("Browser", "Chrome");
		System.out.println("Report Configured");
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		report.flush();
		System.out.println("Report Backedup");
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("==="+result.getMethod().getMethodName()+"==Start==");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.generateLog(Status.INFO, "==="+result.getMethod().getMethodName()+"==Start==");


	}

	@Override
	public void onTestSuccess(ITestResult result) 

	{		System.out.println("==="+result.getMethod().getMethodName()+"==End==");
	test.generateLog(Status.INFO, "==="+result.getMethod().getMethodName()+"==Completed==");

	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
//		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
//		File temp = ts.getScreenshotAs(OutputType.FILE);
//		File perm = new File("./screenshots/"+Mname+"+"+time+".png");
//		try {
//			FileHandler.copy(temp, perm);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		UtilityClassObject.setDriver(BaseClass.sdriver);
		TakesScreenshot ts = (TakesScreenshot)UtilityClassObject.getDriver();
		String ss = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		String Mname = result.getMethod().getMethodName();
		test.addScreenCaptureFromBase64String(ss, Mname+"_"+time);
		test.generateLog(Status.FAIL, "==="+result.getMethod().getMethodName()+"==Failed==");


		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
	System.out.println("==="+result.getMethod().getMethodName()+"==Skipped==");
	test.generateLog(Status.INFO, "==="+result.getMethod().getMethodName()+"==Skipped==");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

}
