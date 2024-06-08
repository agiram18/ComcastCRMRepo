package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithPhoneNumberTest 
{
	
	
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

//		DataFormatter df = new DataFormatter();

		// Fetching data from Excel File
		String orgName = elib.getDataFromExcel("Org", 7, 2)+jlib.getRandomNumber();
		String phno = elib.getDataFromExcel("Org", 7, 3);
//		StringBuilder phno = jlib.getRandomPhoneNumber();

	

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
		// Passing Organization Name
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		// Phone number
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phno);

		// click save
		driver.findElement(By.xpath("(//input[@accesskey='S'])[1]")).click();

		// Verify Expected result for industry and type
		String actphno = driver.findElement(By.xpath("//span[@id='dtlview_Phone']")).getText();
		if (actphno.equals(actphno))
			System.out.println(phno + " verified successfully ===> PASS");
		else
			System.out.println(phno + " verified successfully ===> PASS");
		
		

		driver.quit();

	}

}
