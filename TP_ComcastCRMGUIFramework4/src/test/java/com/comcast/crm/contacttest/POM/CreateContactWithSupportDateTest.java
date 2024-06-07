package com.comcast.crm.contacttest.POM;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfopage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactsPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Throwable 
	{
		FileUtility flib  		= new FileUtility();
		ExcelUtility elib  		= new ExcelUtility();
		JavaUtility jlib 		= new JavaUtility();
		WebDriverUtility wlib 	= new WebDriverUtility();


		// Read data from properties file
		String browser = flib.getDataFromPropertiesFile("Browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("un");
		String password = flib.getDataFromPropertiesFile("pw");


		// Fetching data from Excel File with random number
		String lastName = elib.getDataFromExcel("Contact", 1, 3)+jlib.getRandomNumber();

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();

		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);
		driver.get(url);

		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);
		HomePage hp =new HomePage(driver);
		hp.getConlink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateconBtn().click();

		CreatingNewContactsPage cnew = new CreatingNewContactsPage(driver);
		cnew.getLastname().sendKeys(lastName);

		String startdate = jlib.getSystemDateYYYYDDMM();
		String enddate   = jlib.getRequiredDateYYYYDDMM(30);
		
		cnew.getStartdate().clear();
		cnew.getStartdate().sendKeys(startdate);
		cnew.getEnddate().clear();
		cnew.getEnddate().sendKeys(enddate);
		cnew.getSaveBtn().click();

		
		ContactInfopage cip = new ContactInfopage(driver);
		// Verfing w.r.t header
		String headerinfo = cip.getActheaderinfo().getText();
		if (headerinfo.contains(lastName))
			System.out.println(lastName + " in header verfied succesfully ===> PASS");
		else
			System.out.println(lastName + " in header not verfied ===> FAIL");

		// Verfing w.r.t LastName
		String actLastName = cip.getActlastname().getText();
		if (actLastName.equals(lastName))
			System.out.println(lastName + " created succesfully ===> PASS");
		else
			System.out.println(lastName + " not created ===> FAIL");

		String actstartdate = cip.getActstartdate().getText();
		if (actstartdate.equals(startdate))
			System.out.println(startdate + " verified successfuly");
		else
			System.out.println(startdate + " verified successfuly");

		String actenddate = cip.getActenddate().getText();
		if (actenddate.equals(enddate))
			System.out.println(enddate + " verified successfuly");
		else
			System.out.println(enddate + " verified successfuly");

		driver.quit();

	}

}
