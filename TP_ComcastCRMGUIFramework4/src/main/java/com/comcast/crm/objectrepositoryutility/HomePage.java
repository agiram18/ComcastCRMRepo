package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

			//Create separate class for each page
public class HomePage 
{
	WebDriver driver;    //object initialization
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement conlink;
	
	public WebElement getConlink() {
		return conlink;
	}
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutBtn;
	
	public WebElement getOrglink() 
	{
		return orglink;
	}

	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getSignoutBtn() {
		return signoutBtn;
	}
	
	public void logout() 
	{
		Actions act = new Actions(driver);
		act.moveToElement(adminimg).perform();
		signoutBtn.click();
	}
	

}
