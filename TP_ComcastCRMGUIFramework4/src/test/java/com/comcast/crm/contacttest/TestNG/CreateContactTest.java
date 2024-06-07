package com.comcast.crm.contacttest.TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInfopage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsListPopUpPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.xrm.listenerimplementation.ListenerImplementation.class)
/**
 * Contains Create Contact Automation Test Scripts
 */
public class CreateContactTest extends BaseClass {
	@Test(groups = "Smoke")
	/**
	 * Contains test Script for Create a contact
	 * 
	 * @throws Throwable
	 */
	public void CreateContact() throws Throwable {

		// Fetching data from Excel File
		UtilityClassObject.getTest().log(Status.INFO, "Reading data from Excel");
		String lastName = elib.getDataFromExcel("Contact", 1, 2) + jlib.getRandomNumber();

		// Clicking on contacts
		HomePage hp = new HomePage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Navigating to contacts Page");

		hp.getConlink().click();

		ContactsPage cp = new ContactsPage(driver);
		UtilityClassObject.getTest().log(Status.INFO, "Navigating to  create contacts Page");

		cp.getCreateconBtn().click();

		CreatingNewContactsPage cnew = new CreatingNewContactsPage(driver);
		cnew.getLastname().sendKeys(lastName);
		cnew.getSaveBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Creating a contact");

		// Verfing w.r.t header

		ContactInfopage cip = new ContactInfopage(driver);
		String headerinfo = cip.getActheaderinfo().getText();
		String actLastName = cip.getActlastname().getText();

		boolean HeaderVal = headerinfo.contains(lastName);
		boolean lastNameVal = actLastName.equals(lastName);

		Assert.assertTrue(HeaderVal);
		Assert.assertTrue(lastNameVal);
		Reporter.log("CreateContact done");
		UtilityClassObject.getTest().log(Status.INFO, "CreateContact done");

//		if (headerinfo.contains(lastName))
//			System.out.println(lastName + " in header verfied succesfully ===> PASS");
//		else
//			System.out.println(lastName + " in header not verfied ===> FAIL");
//
//		// Verfing w.r.t LastName
//		if (actLastName.equals(lastName))
//			System.out.println(lastName + " created succesfully ===> PASS");
//		else
//			System.out.println(lastName + " not created ===> FAIL");

	}

	@Test(groups = "Regression")
	/**
	 * Contains test Script for Create a contact with organization
	 * 
	 * @throws Throwable
	 */
	public void CreateContactWithOrganization() throws Throwable {
		// Fetching data from Excel File with random number
		UtilityClassObject.getTest().log(Status.INFO, "Reading data from Excel");

		String orgName = elib.getDataFromExcel("Contact", 7, 2) + jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("Contact", 7, 3);

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
		String actOrgName = cip.getOrgname().getText();

		boolean headerVal = headerinfo.contains(lastName);
		boolean orgNameVal = actOrgName.trim().equals(orgName);
		Assert.assertTrue(headerVal);
		Assert.assertTrue(orgNameVal);
		UtilityClassObject.getTest().log(Status.INFO, "CreateContactWithOrganization done");

//			if (headerinfo.contains(lastName))
//				System.out.println(lastName + " in header verfied succesfully ===> PASS");
//			else
//				System.out.println(lastName + " in header not verfied ===> FAIL");
//			
//			// Verfing w.r.t LastName
//			if (actOrgName.trim().equals(orgName))
//				System.out.println(orgName + " created succesfully ===> PASS");
//			else
//				System.out.println(orgName + " not created ===> FAIL");

	}

	@Test(groups = "Regression")
	/**
	 * Contains test Script for Create a contact with support date
	 * 
	 * @throws Throwable
	 */
	public void CreateContactWithSupportDate() throws Throwable {

		// Fetching data from Excel File with random number
		String lastName = elib.getDataFromExcel("Contact", 1, 3) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getConlink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateconBtn().click();

		CreatingNewContactsPage cnew = new CreatingNewContactsPage(driver);
		cnew.getLastname().sendKeys(lastName);

		String startdate = jlib.getSystemDateYYYYDDMM();
		String enddate = jlib.getRequiredDateYYYYDDMM(30);

		cnew.getStartdate().clear();
		cnew.getStartdate().sendKeys(startdate);
		cnew.getEnddate().clear();
		cnew.getEnddate().sendKeys(enddate);
		cnew.getSaveBtn().click();

		ContactInfopage cip = new ContactInfopage(driver);
		// Verfing w.r.t header
		String headerinfo = cip.getActheaderinfo().getText();
		boolean headerVal = headerinfo.contains(lastName);
		Assert.assertTrue(headerVal);

//				if (headerinfo.contains(lastName))
//					System.out.println(lastName + " in header verfied succesfully ===> PASS");
//				else
//					System.out.println(lastName + " in header not verfied ===> FAIL");

		// Verfing w.r.t LastName
		String actLastName = cip.getActlastname().getText();
		boolean LastName = actLastName.equals(lastName);

//				if (actLastName.equals(lastName))
//					System.out.println(lastName + " created succesfully ===> PASS");
//				else
//					System.out.println(lastName + " not created ===> FAIL");

		String actstartdate = cip.getActstartdate().getText();
		boolean StartDateVal = actstartdate.equals(startdate);
		Assert.assertTrue(StartDateVal);

//				if (actstartdate.equals(startdate))
//					System.out.println(startdate + " verified successfuly");
//				else
//					System.out.println(startdate + " verified successfuly");

		String actenddate = cip.getActenddate().getText();
		boolean EndDateVal = actenddate.equals(enddate);
		Assert.assertTrue(EndDateVal);

//				if (actenddate.equals(enddate))
//					System.out.println(enddate + " verified successfuly");
//				else
//					System.out.println(enddate + " verified successfuly");

		Reporter.log("CreateContactWithSupportDate done", true);

	}

}
