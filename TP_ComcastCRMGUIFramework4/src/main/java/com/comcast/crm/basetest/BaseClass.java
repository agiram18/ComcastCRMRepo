package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * Contains TestNg Annotations
 */
@Listeners(com.comcast.xrm.listenerimplementation.ListenerImplementation.class)
public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public DatabaseUtility dlib = new DatabaseUtility();

	@BeforeSuite(groups = { "Smoke", "Regression" })
	public void BS() throws Throwable {
		System.out.println("==Connect to DB==");
		dlib.getDbConnection();

	}

//	@Parameters("Browser")
//	@BeforeClass(alwaysRun = true)
	@BeforeClass(groups = { "Smoke", "Regression" })
	public void BC(/* String Browser */) throws Throwable {
		System.out.println("==Launch Browser==");
		// Read data from properties file
//		String browser = Browser; 

		String browser = flib.getDataFromPropertiesFile("Browser");

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = { "Smoke", "Regression" })
	public void BeforeMethod() throws Throwable {
		System.out.println("==Login==");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("un");
		String password = flib.getDataFromPropertiesFile("pw");

		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);
	}

	@AfterMethod(groups = { "Smoke", "Regression" })
	public void AfterMethod() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();

	}

	@AfterClass(groups = { "Smoke", "Regression" })
	public void AC() {
		System.out.println("==Closing Browser");
		driver.quit();
	}

	@AfterSuite(groups = { "Smoke", "Regression" })
	public void AS() throws Throwable {
		dlib.closeDbConnection();

		System.out.println("==Close DB");
	}

}
