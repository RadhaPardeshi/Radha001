package PracticeTestNg;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AnotationTest {
	
	@Test
	public void test1()
	{
		System.out.println("-- Executing test case-1(testCases) --");
	}
 
	@BeforeSuite
	public void bs()
	{
		System.out.println("--- Before Suite(Connect the database) ---");
	}
	@AfterSuite
	public void as()
	{
		System.out.println("--- After Suite (Close the Database connection) ---");
	}
	@BeforeClass
	public void bc()
	{
		System.out.println("--- Before class (Launch the browser)---");
	}
	
	@AfterClass
    public void ac()
    {
    	System.out.println("--- After Class(Close the browser  ---");
    }
    
	@BeforeMethod
    public void bm()
    {
    	System.out.println("--- Before Method(Login) ---");
    }
    
	@AfterMethod
    public void am()
    {
    	System.out.println("--- After Method(Logout)---");
    }
    
    @Test 
    public void test2()
	{
		System.out.println("-- Executing test case-2 --");
	}
    
    @BeforeSuite
	public void bs1()
	{
		System.out.println("--- Before Suite-1 ---");
	}
	@AfterSuite
	public void as1()
	{
		System.out.println("--- After Suite-1 ---");
	}
	@AfterClass
    public void ac1()
    {
    	System.out.println("--- After Class-1 ---");
    }
	
	
}

