package Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CustomerOpenedAccount {
	
	public static void main(String[] args) throws IOException {
		 FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commondata.property");
		 Properties p=new Properties();
		 p.load(fis);
		String apptext = "690837292";
		 
        String BROWSER = p.getProperty("browser");
        String URL = p.getProperty("urls");
        String USERNAME = p.getProperty("username");
        String PASSWORD = p.getProperty("password");
        
        WebDriver driver=new ChromeDriver();
        driver.get(URL);
        driver.findElement(By.name("staff_id")).sendKeys(USERNAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("staff_login-btn")).click();
        driver.findElement(By.name("apprvac")).click();
        driver.findElement(By.name("application_no")).sendKeys(apptext);
        driver.findElement(By.name("search_application")).click();
        String name = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]")).getText();        
        System.out.println(name);
        if(name.contains(USERNAME))
        {
        	driver.findElement(By.name("approve_cust")).click();
        }
        
        
        
		
	}

}
