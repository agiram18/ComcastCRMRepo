package com.comcast.crm.contacttest;

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

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Clicking on contacts
		driver.findElement(By.xpath("//a[contains(text() , 'Contacts')]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		String startdate = jlib.getSystemDateYYYYDDMM();
		String enddate   = jlib.getRequiredDateYYYYDDMM(30);
		
		

		// passing data
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		// start and end date
		driver.findElement(By.xpath("//input[@name='support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_start_date']")).sendKeys(startdate);

		driver.findElement(By.xpath("//input[@name='support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@name='support_end_date']")).sendKeys(enddate);
		// Saving
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Verfing w.r.t header
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(lastName))
			System.out.println(lastName + " in header verfied succesfully ===> PASS");
		else
			System.out.println(lastName + " in header not verfied ===> FAIL");

		// Verfing w.r.t LastName
		String actLastName = driver.findElement(By.xpath("//span[@id=\"dtlview_Last Name\"]")).getText();
		if (actLastName.equals(lastName))
			System.out.println(lastName + " created succesfully ===> PASS");
		else
			System.out.println(lastName + " not created ===> FAIL");

		String actstartdate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if (actstartdate.equals(startdate))
			System.out.println(startdate + " verified successfuly");
		else
			System.out.println(startdate + " verified successfuly");

		String actenddate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if (actenddate.equals(enddate))
			System.out.println(enddate + " verified successfuly");
		else
			System.out.println(enddate + " verified successfuly");

		driver.quit();

	}

}
