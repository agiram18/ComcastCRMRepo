package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{
	WebDriver driver;    //object initialization
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@id='dtlview_Organization Name']")
	private WebElement actOrgname;
	
	public WebElement getActOrgname() 
	{
		return actOrgname;
	}
	
	@FindBy(xpath   = "//span[@class='dvHeaderText']")
	private WebElement headerinfo;
	
	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	@FindBy(xpath = "//span[@id='dtlview_Industry']")
	private WebElement actIndustryname;
	
	@FindBy(xpath = "//span[@id='dtlview_Type']")
	private WebElement actType;
	
	@FindBy(xpath = "//span[@id='dtlview_Phone']")
	private WebElement actphno;
	
	public WebElement getActphno() {
		return actphno;
	}

	public WebElement getActIndustryname() {
		return actIndustryname;
	}

	public WebElement getActType() {
		return actType;
	}
	
	
	
	
}
