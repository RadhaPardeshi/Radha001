package Customer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.GenericTtilities.DatabaseUtility;
import com.GenericTtilities.ExcelUtility;
import com.GenericTtilities.FileUtility;
import com.GenericTtilities.JavaUtility;
import com.GenericTtilities.WebdiverUtility;

public class OpenCustomerBankAccountUtilityTest {
	public static WebDriver driver;

	public static String[] fetchDebit(String text) {
		String[] lines1 = text.split("\\r?\\n");
		System.out.println(Arrays.toString(lines1));
		// Search for the line containing the application number

		String debitCardNum = "";
		String pin1 = "";
		String[] parts = null;
		;
		for (String liD : lines1) {

			if (liD.contains("Your Debit Card No is : ")) 
			{
				// Split the line to get the application number
				parts = liD.trim().split(" ");

				for (String part : parts) {
					if (isNumeric(part)) {
						if (debitCardNum.isEmpty()) {
							debitCardNum = part;
						} else {
							pin1 = part;
							break;
						}
					}
				}
			}
		}
		String[] arr= {debitCardNum,pin1};
		return arr;	
	}
	public static boolean isNumeric(String str) {

		for (int i = 0; i < str.length(); i++) {

			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public void saveDataInExcel(Workbook workbook) throws IOException {

		FileOutputStream fOut= new FileOutputStream(".\\src\\test\\resources\\Coin1TestData.xlsx");

		workbook.write(fOut);
	}
	public static void main(String[] args) throws IOException, AWTException, InterruptedException 
	{
		ExcelUtility eUtility=new ExcelUtility();
		FileUtility fUtility =new FileUtility();
		JavaUtility jUtility =new JavaUtility();
		WebdiverUtility wUtility = new WebdiverUtility();

		fUtility.readDataFromPropertyFile("browser");
		String URL = fUtility.readDataFromPropertyFile("url");

		driver=new ChromeDriver();

		wUtility.maximizebrowser(driver);
		wUtility.implicitlyWaitStatement(driver, 10);
		driver.get(URL);
		driver.findElement(By.xpath("//li[text()='Open Account']")).click();


		String Name= eUtility.readDataFromExcelFile("useropenAccount", 0, 1);
		String mobileNo = eUtility.readDataFromExcelFile("useropenAccount", 1, 1);
		String emailId = eUtility.readDataFromExcelFile("useropenAccount", 2, 1);
		String panNo= eUtility.readDataFromExcelFile("useropenAccount", 3, 1);
		String citizenship= eUtility.readDataFromExcelFile("useropenAccount", 4, 1);
		String homeAdd=eUtility.readDataFromExcelFile("useropenAccount", 5, 1);
		String offAdd=eUtility.readDataFromExcelFile("useropenAccount", 6, 1);
		String pin= eUtility.readDataFromExcelFile("useropenAccount", 7, 1);
		String area= eUtility.readDataFromExcelFile("useropenAccount", 8, 1);
		String landline =eUtility.readDataFromExcelFile("useropenAccount", 9, 1);
		String gender = eUtility.readDataFromExcelFile("useropenAccount", 0, 5);
		String state = eUtility.readDataFromExcelFile("useropenAccount", 1, 5);
		String city = eUtility.readDataFromExcelFile("useropenAccount", 2, 5);
		String account= eUtility.readDataFromExcelFile("useropenAccount", 3, 5);


		driver.findElement(By.name("name")).sendKeys(Name+jUtility.random());
		WebElement gen = driver.findElement(By.name("gender"));
		wUtility.selectdropDownByVisibleText(gen, gender);
		driver.findElement(By.name("mobile")).sendKeys(mobileNo);
		driver.findElement(By.name("email")).sendKeys(emailId);
		driver.findElement(By.name("landline")).sendKeys(landline);
		driver.findElement(By.name("pan_no")).sendKeys(panNo);
		driver.findElement(By.name("citizenship")).sendKeys(citizenship);
		driver.findElement(By.name("homeaddrs")).sendKeys(homeAdd);
		driver.findElement(By.name("officeaddrs")).sendKeys(homeAdd);
		WebElement stat = driver.findElement(By.name("state"));
		wUtility.selectdropDownByVisibleText(stat, state);

		WebElement cit = driver.findElement(By.name("city"));
		wUtility.selectdropDownByVisibleText(cit, city);

		driver.findElement(By.name("pin")).sendKeys(pin);
		driver.findElement(By.name("arealoc")).sendKeys(area);

		WebElement accot = driver.findElement(By.name("acctype"));
		wUtility.selectdropDownByVisibleText(accot, account);


		driver.findElement(By.name("dob")).click();
		wUtility.enetrkeyPress();

		driver.findElement(By.name("submit")).click();

		driver.findElement(By.name("cnfrm-submit")).click();

		Alert a = wUtility.objectAlert(driver);

		String apptext=a.getText();

		String[] lines = apptext.split("\\r?\\n");

		System.out.println(Arrays.toString(lines));
		// Search for the line containing the application number
		int applicationNumber ;
		String applicationNumberStr = null;
		for (String line : lines) {
			if (line.startsWith("Application number :")) {
				// Split the line to get the application number
				String[] parts = line.split(":");
				// Extract the application number string
				applicationNumberStr = parts[1].trim();
				// Parse the application number string to integer
				//applicationNumber = Integer.parseInt(applicationNumberStr);

				break; // Break the loop once the application number is found
			}
		}
		wUtility.acceptAlert(driver);
		Thread.sleep(5000);
		String finaltitle = driver.getTitle();

		if(finaltitle.equalsIgnoreCase("Online banking System"))
		{
			System.out.println("Account opened with unique application id and test case pass");
		}
		else
		{
			System.out.println("Account not opened with unique application id and test case fail");
		}
		driver.findElement(By.xpath("//a[text()='Staff Login']")).click();
		
		String USERNAME= fUtility.readDataFromPropertyFile("username");
		String PASSWORD = fUtility.readDataFromPropertyFile("password");
		
		
		driver.findElement(By.name("staff_id")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		driver.findElement(By.name("staff_login-btn")).click();
		//Alert addpopup = driver.switchTo().alert();
		//addpopup.accept();
		driver.findElement(By.name("apprvac")).click();
		driver.findElement(By.name("application_no")).sendKeys(applicationNumberStr);
		driver.findElement(By.name("search_application")).click();
		String name = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]")).getText();        
		System.out.println(name);
		String accountID =null ;
		if(name.contains(Name))
		{
			System.out.println("Customer is present");
			driver.findElement(By.name("approve_cust")).click();

		}

		String txt = wUtility.objectAlert(driver).getText();
		Thread.sleep(5000);

		String[] line = txt.split("\\r?\\n");

		System.out.println(Arrays.toString(line));
		// Search for the line containing the application number

		for (String li : line) {
			if (li.startsWith("Account no :")) {
				// Split the line to get the application number
				String[] parts = li.split(":");
				// Extract the application number string
				accountID = parts[1].trim();
				// Parse the application number string to integer
				//accountNumber = Integer.parseInt(account);
				a.accept();
				break;
			}
			System.out.println(accountID);

		}
		driver.findElement(By.name("logout_btn")).click();
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		driver.findElement(By.xpath("//li[text()='Apply Debit Card']")).click();
		driver.findElement(By.name("holder_name")).sendKeys(name);
		driver.findElement(By.name("dob")).click();
		wUtility.enetrkeyPress();

		driver.findElement(By.name("pan")).sendKeys(panNo);
		driver.findElement(By.name("mob")).sendKeys(mobileNo);
		driver.findElement(By.name("acc_no")).sendKeys(accountID);
		driver.findElement(By.name("dbt_crd_submit")).click();
		Alert add = wUtility.objectAlert(driver);
		Thread.sleep(5000);
		String debitText =add.getText();
		System.out.println("Debit data"+debitText);

		String[] arr=fetchDebit(debitText) ;
		String debitno = arr[0];
		String pin1no = arr[1];

		System.out.println(debitno); 
		System.out.println(pin1no);
		wUtility.acceptAlert(driver);
		driver.findElement(By.xpath("//a[text()='Home']")).click();

		WebElement el=driver.findElement(By.xpath("//a[contains(text(),'Internet Banking')]"));
	    wUtility.scrollTillEleToBeVisible(driver, el);
		WebElement element= driver.findElement(By.xpath("//li[contains(text(),'Register')]"));
		
//		Actions action =new Actions(driver);
//		action.moveToElement(el).perform();
//		element.click();
		wUtility.mousehoveraction(driver, el);
		element.click();
		driver.findElement(By.name("holder_name")).sendKeys(name);
		driver.findElement(By.name("accnum")).sendKeys(accountID);
		driver.findElement(By.name("dbtcard")).sendKeys(debitno);
		driver.findElement(By.name("dbtpin")).sendKeys(pin1no);
		driver.findElement(By.name("mobile")).sendKeys(mobileNo);
		driver.findElement(By.name("pan_no")).sendKeys(panNo);
		driver.findElement(By.name("dob")).click();
		wUtility.enetrkeyPress();
		driver.findElement(By.name("last_trans")).sendKeys("0");

		String password = eUtility.readDataFromExcelFile("useropenAccount", 10, 1);
		String confpassword = eUtility.readDataFromExcelFile("useropenAccount", 11, 1);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("cnfrm_password")).sendKeys(confpassword);
		driver.findElement(By.name("submit")).click();

		String regpopup = wUtility.objectAlert(driver).getText();
		Thread.sleep(5000);
		String internetAccountNumber="";
		for(int i=0;i<regpopup.length();i++) {
			if(Character.isDigit(regpopup.charAt(i))) {
				internetAccountNumber+=regpopup.charAt(i);
			}
		}

		System.out.println(internetAccountNumber);
		wUtility.acceptAlert(driver);
		driver.findElement(By.xpath("//a[text()='Home']")).click();
	}


}
