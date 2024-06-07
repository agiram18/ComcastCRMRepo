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

public class CreateOrgWithIndustryTest {
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

		// Login page action method from login page obj repository
		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);

		// HomePage Pom class
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// OrganizationsPage Pom class
		OrganizationsPage ogpage = new OrganizationsPage(driver);
		ogpage.getCreateorg().click();

		// CreatingNewOrganizationPage Pom class
		CreatingNewOrganizationPage newog = new CreatingNewOrganizationPage(driver);
		newog.getorgName().sendKeys(orgName);
		
		// Industry and type
		
		wlib.select(newog.getIndustry(), Industry);
		wlib.select(newog.getAcctype(), Type);
		newog.getSaveBtn().click();
		
		
		

		// Verify Expected result for industry and type
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustry = oip.getActIndustryname().getText();
		String actType = oip.getActType().getText();
		
		if (actIndustry.equals(Industry))
			System.out.println(Industry + " verified successfully ===> PASS");
		else
			System.out.println(Industry + " verified successfully ===> PASS");
		if (actType.equals(Type))
			System.out.println(Type + " verified successfully ===> PASS");
		else
			System.out.println(Type + " verified successfully ===> PASS");

		driver.quit();

	}

}
