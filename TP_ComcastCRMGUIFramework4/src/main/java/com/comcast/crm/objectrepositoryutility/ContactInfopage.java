package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfopage 
{
	WebDriver driver;    //object initialization
	public ContactInfopage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement actheaderinfo;
	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	private WebElement actlastname;
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgname;
	
	@FindBy(xpath = "//span[@id='dtlview_Support Start Date']")
	private WebElement actstartdate;
	
	@FindBy(xpath = "//span[@id='dtlview_Support End Date']")
	private WebElement actenddate;
	
	public WebElement getActstartdate() {
		return actstartdate;
	}


	public WebElement getActenddate() {
		return actenddate;
	}


	public WebElement getOrgname() {
		return orgname;
	}


	public WebElement getActheaderinfo() {
		return actheaderinfo;
	}


	public WebElement getActlastname() {
		return actlastname;
	}


	
	
	
}
