package com.comcast.crm.orgtest.POM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
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
		String orgName = elib.getDataFromExcel("Org", 1, 2)+jlib.getRandomNumber();

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		wlib.waitForPageToLoad(driver);
		wlib.maximize(driver);
		driver.get(url);

		//Login page action method from login page obj repository
		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);
		
		//HomePage Pom class 
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		
		//OrganizationsPage Pom class
		OrganizationsPage ogpage = new OrganizationsPage(driver);
		ogpage.getCreateorg().click();
		
		//CreatingNewOrganizationPage Pom class
		CreatingNewOrganizationPage newog = new CreatingNewOrganizationPage(driver);
		newog.getorgName().sendKeys(orgName);
		newog.getSaveBtn().click();
		
		//OrganizationInfoPage Pom class
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getActOrgname().getText();
		String headerinfo = oip.getHeaderinfo().getText();
		
		System.out.println(actOrgName.equals(orgName)? " "+orgName+" created successfully" : " "+orgName+"not created successfully");
		System.out.println(headerinfo.contains(orgName)? " "+orgName+" verified in header" : " "+orgName+" not verified in header");
		
		
		//Searching Organization
		hp.getOrglink().click();

		ogpage.getSearchtxt().sendKeys(orgName);
		wlib.select(ogpage.getSearchDD(), "Organization Name");
		ogpage.getSearchBtn().click();
	
		WebElement delete = driver.findElement(By.xpath("//a[text() = '"+orgName+"']/../../td[8]/a[text() = 'del']"));
		delete.click();
		driver.switchTo().alert().accept(); 
		System.out.println(orgName + " deleted Successfully");
		
		hp.logout();
		
		driver.quit();
	}
}
