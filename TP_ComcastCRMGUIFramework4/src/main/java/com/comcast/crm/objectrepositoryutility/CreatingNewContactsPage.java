package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactsPage 
{
	WebDriver driver;    //object initialization
	public CreatingNewContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastname;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement addorgBtn;
	
	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement startdate;

	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement enddate;


	public WebElement getStartdate() {
		return startdate;
	}



	public WebElement getEnddate() {
		return enddate;
	}
	
	public WebElement getAddorgBtn() {
		return addorgBtn;
	}

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	

}
