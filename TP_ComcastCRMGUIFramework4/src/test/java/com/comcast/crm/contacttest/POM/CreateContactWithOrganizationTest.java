package com.comcast.crm.contacttest.POM;

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
import com.comcast.crm.objectrepositoryutility.ContactInfopage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsListPopUpPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

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

		LoginPage lp = new LoginPage(driver);
		lp.login(url, username, password);

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		OrganizationsPage ogpage = new OrganizationsPage(driver);
		ogpage.getCreateorg().click();
		CreatingNewOrganizationPage newog = new CreatingNewOrganizationPage(driver);
		newog.getorgName().sendKeys(orgName);
		newog.getSaveBtn().click();
		
		System.out.println(orgName + " created");
		Thread.sleep(2000);
		
		// Navigate to contact module
		hp.getConlink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateconBtn().click();

		CreatingNewContactsPage cnew = new CreatingNewContactsPage(driver);
		cnew.getLastname().sendKeys(lastName);
	
		cnew.getAddorgBtn().click();
		
		// Switch to child window
		wlib.switchToTabOnUrl(driver, "module=Accounts");
		OrganizationsListPopUpPage orgpop = new OrganizationsListPopUpPage(driver);
		orgpop.getSearchtxt().sendKeys(orgName);
		orgpop.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text() = '" + orgName + "']")).click();
		// Switch to parent window
		wlib.switchToTabOnTitle(driver, "module=Contacts");
	
		cnew.getSaveBtn().click();
		
		ContactInfopage cip = new ContactInfopage(driver);
		String headerinfo = cip.getActheaderinfo().getText();
		
		if (headerinfo.contains(lastName))
			System.out.println(lastName + " in header verfied succesfully ===> PASS");
		else
			System.out.println(lastName + " in header not verfied ===> FAIL");
		
		// Verfing w.r.t LastName
		String actOrgName = cip.getOrgname().getText();
		if (actOrgName.trim().equals(orgName))
			System.out.println(orgName + " created succesfully ===> PASS");
		else
			System.out.println(orgName + " not created ===> FAIL");

		driver.quit();
		
		
	}

}
