package dummy;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class ScreenShotTest 
{
	@Test
	public void ss() throws Throwable
	{
		WebDriver driver  = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./screenshot/amazon.png");
		FileHandler.copy(temp, perm);
		
	}

}
