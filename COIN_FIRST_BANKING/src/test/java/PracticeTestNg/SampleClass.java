package PracticeTestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.GenericTtilities.BaseClass;

@Listeners(Annotations.com.ListnersAnnotationsclass.class)
public class SampleClass extends BaseClass{

	@Test
	public void testMethod() {
		System.out.println("Entered the method");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		Assert.fail();
	}
	
}
