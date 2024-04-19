package Customer;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTest {
	
	@Test(priority = 0,dependsOnMethods = "modifyCustomer")
	public void createCustomer()
	{
		Reporter.log("Creating bank cutomer" , true);
	}
	
	@Test(invocationCount = 1 )
	public void modifyCustomer()
	{
		Reporter.log("Modifying bank cutomer" , true);
	}
	
	@Test
	public void deleteCustomer()
	{
		Reporter.log("Deleting bank cutomer" , true);
	}
	

}
