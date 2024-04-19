package PracticeTestNg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.netBanking.ObjectRepository.pom.StaffLoginPage;

public class UserLoginDataProiderTesting {
	
	
	@Test (dataProviderClass = UsernamePwdCredentialDataProvider.class,dataProvider = "userData")
	public void userLogin(String username,String password)
	{
		WebDriver driver =new ChromeDriver();
		driver.get("http://rmgtestingserver/domain/Online_Banking_System/staff_login.php");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
		StaffLoginPage slp = new StaffLoginPage(driver);
		slp.enterPasswordFiled(username);
		slp.enterPasswordFiled(password);
		slp.getloginBtn().click();
		
	}

}
