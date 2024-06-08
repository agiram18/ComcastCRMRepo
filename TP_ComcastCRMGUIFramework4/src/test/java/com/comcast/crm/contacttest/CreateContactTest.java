package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactTest {
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

		// Random data generation
		// Fetching data from Excel File
		String lastName = elib.getDataFromExcel("Contact", 1, 2)+jlib.getRandomNumber();

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();

		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);
		driver.get(url);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Clicking on contacts
		driver.findElement(By.xpath("//a[contains(text() , 'Contacts')]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// passing data
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

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

		driver.quit();

	}

}
