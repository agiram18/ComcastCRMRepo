package dummy;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
public class InvoiceTest extends BaseClass
{
	@Test(retryAnalyzer = com.comcast.xrm.listenerimplementation.RetryListenerImp.class)
	public void createInvoiceTest() 
	{
		System.out.println("Execute createInvoiceTest");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	@Test(enabled = false)
	public void createInvoiceWithDateTest() 
	{
		System.out.println("Execute createInvoiceTest");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
