package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage 
{
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgName;
	
	public WebElement getorgName() 
	{
		return orgName;
	}
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industry;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement acctype;
	
	@FindBy(xpath = "//input[@name='phone']")
	private WebElement phno;
	
	



	public WebElement getOrgName() {
		return orgName;
	}



	public WebElement getSaveBtn() {
		return saveBtn;
	}



	public WebElement getPhno() {
		return phno;
	}



	public WebElement getIndustry() {
		return industry;
	}



	public WebElement getAcctype() {
		return acctype;
	}



	

}
