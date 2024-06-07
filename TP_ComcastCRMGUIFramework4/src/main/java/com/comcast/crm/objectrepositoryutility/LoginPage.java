package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='user_name']")
	private WebElement uname;
	
	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement pwd;
	
	@FindBy(xpath = "//input[@id='submitButton']")
	private WebElement loginBtn;
	
	
	public WebElement getUname() 
	{
		return uname;
	}

	public WebElement getPwd() 
	{
		return pwd;
	}

	public WebElement getLoginBtn() 
	{
		return loginBtn;
	}
	
	/**
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	public void login(String url ,String username , String password)
	{
		waitForPageToLoad(driver);
		maximize(driver);
		driver.get(url);
		uname.sendKeys(username);
		pwd.sendKeys(password);
		loginBtn.click();
	}
	
	


}
