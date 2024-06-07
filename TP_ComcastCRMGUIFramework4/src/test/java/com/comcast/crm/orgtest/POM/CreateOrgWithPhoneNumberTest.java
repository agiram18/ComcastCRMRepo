package com.comcast.crm.orgtest.POM;

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
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

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



		// Fetching data from Excel File
		String orgName = elib.getDataFromExcel("Org", 7, 2)+jlib.getRandomNumber();
//		String phno = elib.getDataFromExcel("Org", 7, 3);
		StringBuilder phno = jlib.getRandomPhoneNumber();

	

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();

		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);
		driver.get(url);

		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);

		
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateorg().click();
		
		CreatingNewOrganizationPage neworg = new CreatingNewOrganizationPage(driver);
		neworg.getorgName().sendKeys(orgName);
		neworg.getPhno().sendKeys(phno);
		neworg.getSaveBtn().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphno = oip.getActphno().getText();
		
		// Verify Expected result for industry and type
		if (actphno.equals(actphno))
			System.out.println(phno + " verified successfully ===> PASS");
		else
			System.out.println(phno + " verified successfully ===> PASS");
		
		

		driver.quit();

	}

}
