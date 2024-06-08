package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsListPopUpPage 
{
	WebDriver driver;    //object initialization
	public OrganizationsListPopUpPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement Searchtxt;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBtn;
	public WebElement getSearchtxt() {
		return Searchtxt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
	

}
