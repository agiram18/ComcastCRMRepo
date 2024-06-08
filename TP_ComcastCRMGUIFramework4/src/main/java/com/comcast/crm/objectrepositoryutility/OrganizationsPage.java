package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createorg;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchtxt;
	
	@FindBy(xpath = "//select[@name='search_field']")
	private WebElement searchDD;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement searchBtn;
	
	
	
	
	
	
	public WebElement getCreateorg() {
		return createorg;
	}



	

	public WebElement getSearchtxt() {
		return searchtxt;
	}



	public WebElement getSearchDD() {
		return searchDD;
	}



	public WebElement getSearchBtn() {
		return searchBtn;
	}

}
