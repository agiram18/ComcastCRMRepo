package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws Throwable 
	{
		// Read data from properties file
		FileUtility flib  		= new FileUtility();
		ExcelUtility elib  		= new ExcelUtility();
		JavaUtility jlib 		= new JavaUtility();
		WebDriverUtility wlib 	= new WebDriverUtility();

		String browser  = flib.getDataFromPropertiesFile("Browser");
		String url 		= flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("un");
		String password = flib.getDataFromPropertiesFile("pw");

		

		// Fetching data from Excel File with random number

		String orgName = elib.getDataFromExcel("Contact", 7, 2)+jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("Contact", 7, 3);


		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();

		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);
		
		driver.get(url);

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Clicking on organizations and entering data

		driver.findElement(By.xpath("(//a[contains(text() , 'Organizations')])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);

		// click save
		driver.findElement(By.xpath("(//input[@accesskey='S'])[1]")).click();

		// Verify Expected result for header
		String headerinfo = driver.findElement(By.className("dvHeaderText")).getText();
		if (headerinfo.contains(orgName))
			System.out.println(orgName + " header is verified ===> PASS");
		else
			System.out.println(orgName + " header is not verified ===> FAIL");

		// Navigate to contact module

		// Clicking on contacts
		driver.findElement(By.xpath("//a[contains(text() , 'Contacts')]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// passing data
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to child window
		wlib.switchToTabOnUrl(driver, "module=Accounts");
		
		

		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text() = '" + orgName + "']")).click();
		Thread.sleep(3000);

		// Switch to parent window
		wlib.switchToTabOnTitle(driver, "module=Contacts");
	
		// Saving
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(3000);

		// Verfing w.r.t header
		String headerinfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo1.contains(lastName))
			System.out.println(lastName + " in header verfied succesfully ===> PASS");
		else
			System.out.println(lastName + " in header not verfied ===> FAIL");

		// Verfing w.r.t LastName
		String actOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText();
		if (actOrgName.trim().equals(orgName))
			System.out.println(orgName + " created succesfully ===> PASS");
		else
			System.out.println(orgName + " not created ===> FAIL");

		driver.quit();

	}

}
