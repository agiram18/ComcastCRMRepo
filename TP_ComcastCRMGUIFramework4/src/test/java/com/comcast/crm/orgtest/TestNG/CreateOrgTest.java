package com.comcast.crm.orgtest.TestNG;

/**
 * Contains Create Organization Scripts
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.xrm.listenerimplementation.ListenerImplementation.class)
public class CreateOrgTest extends BaseClass {
	@Test(groups = "Smoke")
	/**
	 * Contains test script for create organization
	 * 
	 * @throws Throwable
	 */
	public void CreateOrg() throws Throwable {

		// Fetching data from Excel File
		String orgName = elib.getDataFromExcel("Org", 1, 2) + jlib.getRandomNumber();

		// HomePage Pom class
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// OrganizationsPage Pom class
		OrganizationsPage ogpage = new OrganizationsPage(driver);
		ogpage.getCreateorg().click();

		// CreatingNewOrganizationPage Pom class
		CreatingNewOrganizationPage newog = new CreatingNewOrganizationPage(driver);
		newog.getorgName().sendKeys(orgName);
		newog.getSaveBtn().click();

		// OrganizationInfoPage Pom class
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getActOrgname().getText();
		String headerinfo = oip.getHeaderinfo().getText();

		boolean headerVal = headerinfo.contains(actOrgName);
		boolean OrgVal = actOrgName.equals(orgName);
		Assert.assertTrue(headerVal);
		Assert.assertTrue(OrgVal);
		Reporter.log("Validation Successfull");

//		Reporter.log(actOrgName.equals(orgName) ? " " + orgName + " created successfully"
//				: " " + orgName + "not created successfully");
//		Reporter.log(headerinfo.contains(orgName) ? " " + orgName + " verified in header"
//				: " " + orgName + " not verified in header");

	}

	@Test(groups = "Regression")
	/**
	 * Contains test Script for Create Organization with Industry
	 * 
	 * @throws Throwable
	 */
	public void CreateOrgWithIndustry() throws Throwable {

		// Fetching data from Excal File
		String orgName = elib.getDataFromExcel("Org", 4, 2) + jlib.getRandomNumber();
		String Industry = elib.getDataFromExcel("Org", 4, 3);
		String Type = elib.getDataFromExcel("Org", 4, 4);

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

		boolean indstryVal = actIndustry.equals(Industry);
		boolean TypeVal = actType.equals(Type);
		Assert.assertTrue(indstryVal);
		Assert.assertTrue(TypeVal);
		Reporter.log("Validation Successfull");

//		if (actIndustry.equals(Industry))
//			Reporter.log(Industry + " verified successfully ===> PASS");
//		else
//			Reporter.log(Industry + " verified successfully ===> PASS");
//		if (actType.equals(Type))
//			Reporter.log(Type + " verified successfully ===> PASS");
//		else
//			Reporter.log(Type + " verified successfully ===> PASS");

	}

	@Test(groups = "Regression")
	/**
	 * Contains test Script for Create Organization with Phone Number
	 * 
	 * @throws Throwable
	 */
	public void CreateOrgWithPhoneNumber() throws Throwable {

		// Fetching data from Excel File
		String orgName = elib.getDataFromExcel("Org", 7, 2) + jlib.getRandomNumber();
		String phno = elib.getDataFromExcel("Org", 7, 3);
//		StringBuilder phno = jlib.getRandomPhoneNumber();

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

		boolean phnoVal = actphno.equals(phno);
		Assert.assertTrue(phnoVal);
		Reporter.log("Validation Successfull");
//		// Verify Expected result for industry and type
//		if (actphno.equals(phno))
//			Reporter.log(phno + " verified successfully ===> PASS");
//		else
//			Reporter.log(phno + " verified successfully ===> PASS");

	}

	@Test
	/**
	 * Contains test Script for Deleting a Organization.
	 * 
	 * @throws Throwable
	 */
	public void DeleteOrg() throws Throwable {

		// Fetching data from Excel File
		String orgName = elib.getDataFromExcel("Org", 1, 2) + jlib.getRandomNumber();

		// HomePage Pom class
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// OrganizationsPage Pom class
		OrganizationsPage ogpage = new OrganizationsPage(driver);
		ogpage.getCreateorg().click();

		// CreatingNewOrganizationPage Pom class
		CreatingNewOrganizationPage newog = new CreatingNewOrganizationPage(driver);
		newog.getorgName().sendKeys(orgName);
		newog.getSaveBtn().click();

		// OrganizationInfoPage Pom class
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getActOrgname().getText();
		String headerinfo = oip.getHeaderinfo().getText();

		Reporter.log(actOrgName.equals(orgName) ? " " + orgName + " created successfully"
				: " " + orgName + "not created successfully");
		Reporter.log(headerinfo.contains(orgName) ? " " + orgName + " verified in header"
				: " " + orgName + " not verified in header");

		// Searching Organization
		hp.getOrglink().click();

		ogpage.getSearchtxt().sendKeys(orgName);
		wlib.select(ogpage.getSearchDD(), "Organization Name");
		ogpage.getSearchBtn().click();

		WebElement delete = driver
				.findElement(By.xpath("//a[text() = '" + orgName + "']/../../td[8]/a[text() = 'del']"));
		delete.click();
		driver.switchTo().alert().accept();
		Reporter.log(orgName + " deleted Successfully");

	}
	/**
	 * author Akshay
	 */
}
