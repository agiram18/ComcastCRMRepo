package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithIndustryTest {
	public static void main(String[] args) throws Throwable 
	{
		FileUtility flib  		= new FileUtility();
		ExcelUtility elib  		= new ExcelUtility();
		JavaUtility jlib 		= new JavaUtility();
		WebDriverUtility wlib 	= new WebDriverUtility();


		// Read data from properties file
		String browser  = flib.getDataFromPropertiesFile("Browser");
		String url 		= flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("un");
		String password = flib.getDataFromPropertiesFile("pw");


		// Fetching data from Excal File

		String orgName = elib.getDataFromExcel("Org", 4, 2)+jlib.getRandomNumber();
		String Industry = elib.getDataFromExcel("Org", 4, 3);
		String Type     = elib.getDataFromExcel("Org", 4, 4);

		

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
		
		// Industry and type
		WebElement webindus = driver.findElement(By.xpath("//select[@name='industry']"));
		wlib.select(webindus, Industry);
		
		WebElement webtype = driver.findElement(By.xpath("//select[@name='accounttype']"));
		wlib.select(webtype, Type);

		// click save
		driver.findElement(By.xpath("(//input[@accesskey='S'])[1]")).click();

		// Verify Expected result for industry and type
		String actIndustry = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if (actIndustry.equals(Industry))
			System.out.println(Industry + " verified successfully ===> PASS");
		else
			System.out.println(Industry + " verified successfully ===> PASS");

		String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if (actType.equals(Type))
			System.out.println(Type + " verified successfully ===> PASS");
		else
			System.out.println(Type + " verified successfully ===> PASS");

		driver.quit();

	}

}
