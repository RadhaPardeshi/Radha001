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

public class FundTransferByCutomertoBeneficiary {

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

				if (liD.contains("Your Debit Card No is : ")) {
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

					// Break the loop once the application number is found
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
		public static void main(String[] args) throws IOException, AWTException 
		{
			Random random =new Random();
			int num = random.nextInt(500);

			FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\commondata.property");
			Properties p=new Properties();
			p.load(fis);

			String BROWSER = p.getProperty("browser");
			String URL = p.getProperty("url");

			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(URL);
			driver.findElement(By.xpath("//li[text()='Open Account']")).click();


			FileInputStream fes=new FileInputStream(".\\src\\test\\resources\\Coin1TestData.xlsx");
			Workbook wb = WorkbookFactory.create(fes);
			Sheet sheet = wb.getSheet("UserOpenAccount");
			String Name = sheet.getRow(0).getCell(1).getStringCellValue();
			String mobileNo = sheet.getRow(1).getCell(1).getStringCellValue();
			String emailId = sheet.getRow(2).getCell(1).getStringCellValue();
			String panNo = sheet.getRow(3).getCell(1).getStringCellValue();
			String citizenship = sheet.getRow(4).getCell(1).getStringCellValue();
			String homeAdd = sheet.getRow(5).getCell(1).getStringCellValue();
			String offAdd = sheet.getRow(6).getCell(1).getStringCellValue();
			String pin = sheet.getRow(7).getCell(1).getStringCellValue();
			String area = sheet.getRow(8).getCell(1).getStringCellValue();
			String landline = sheet.getRow(9).getCell(1).getStringCellValue();
			String gender = sheet.getRow(0).getCell(5).getStringCellValue();
			String state = sheet.getRow(1).getCell(5).getStringCellValue();
			String city = sheet.getRow(2).getCell(5).getStringCellValue();
			String account = sheet.getRow(3).getCell(5).getStringCellValue();

			driver.findElement(By.name("name")).sendKeys(Name+num);
			WebElement gen = driver.findElement(By.name("gender"));
			Select s=new Select(gen);
			s.selectByVisibleText(gender);
			driver.findElement(By.name("mobile")).sendKeys(mobileNo);
			driver.findElement(By.name("email")).sendKeys(emailId);
			driver.findElement(By.name("landline")).sendKeys(landline);
			driver.findElement(By.name("pan_no")).sendKeys(panNo);
			driver.findElement(By.name("citizenship")).sendKeys(citizenship);
			driver.findElement(By.name("homeaddrs")).sendKeys(homeAdd);
			driver.findElement(By.name("officeaddrs")).sendKeys(homeAdd);
			WebElement stat = driver.findElement(By.name("state"));
			Select s1=new Select(stat);
			s1.selectByVisibleText(state);

			WebElement cit = driver.findElement(By.name("city"));
			Select s2=new Select(cit);
			s2.selectByVisibleText(city);

			driver.findElement(By.name("pin")).sendKeys(pin);
			driver.findElement(By.name("arealoc")).sendKeys(area);

			WebElement accot = driver.findElement(By.name("acctype"));
			Select s3=new Select(accot);
			s3.selectByVisibleText(account);


			driver.findElement(By.name("dob")).click();
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_2);
			r.keyPress(KeyEvent.VK_7);
			r.keyPress(KeyEvent.VK_0);
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			r.keyPress(KeyEvent.VK_2);
			r.keyPress(KeyEvent.VK_0);
			r.keyPress(KeyEvent.VK_2);
			r.keyPress(KeyEvent.VK_4);

			driver.findElement(By.name("submit")).click();

			driver.findElement(By.name("cnfrm-submit")).click();

			Alert a = driver.switchTo().alert();

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
			a.accept();
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
			String USERNAME = p.getProperty("username");
			String PASSWORD = p.getProperty("password");

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

			String txt = driver.switchTo().alert().getText();

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
			Robot ro=new Robot();
			ro.keyPress(KeyEvent.VK_2);
			ro.keyPress(KeyEvent.VK_7);
			ro.keyPress(KeyEvent.VK_0);
			ro.keyPress(KeyEvent.VK_2);
			ro.keyRelease(KeyEvent.VK_2);
			ro.keyPress(KeyEvent.VK_2);
			ro.keyPress(KeyEvent.VK_0);
			ro.keyPress(KeyEvent.VK_2);
			ro.keyPress(KeyEvent.VK_4);

			driver.findElement(By.name("pan")).sendKeys(panNo);
			driver.findElement(By.name("mob")).sendKeys(mobileNo);
			driver.findElement(By.name("acc_no")).sendKeys(accountID);
			driver.findElement(By.name("dbt_crd_submit")).click();
			Alert add = driver.switchTo().alert();
			String debitText =add.getText();
			System.out.println("Debit data"+debitText);
			
			String[] arr=fetchDebit(debitText) ;
			          String debitno = arr[0];
			          String pin1no = arr[1];

		System.out.println(debitno); 
		System.out.println(pin1no);
		add.accept();
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		
		WebElement el=driver.findElement(By.xpath("//a[contains(text(),'Internet Banking')]"));
	    WebElement element= driver.findElement(By.xpath("//li[contains(text(),'Register')]"));
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("arguments[0].scrollIntoView(true)", el);
	        Actions action =new Actions(driver);
	    action.moveToElement(el).perform();
	    element.click();
	    
	driver.findElement(By.name("holder_name")).sendKeys(name);
	driver.findElement(By.name("accnum")).sendKeys(accountID);
	driver.findElement(By.name("dbtcard")).sendKeys(debitno);
	driver.findElement(By.name("dbtpin")).sendKeys(pin1no);
	driver.findElement(By.name("mobile")).sendKeys(mobileNo);
	driver.findElement(By.name("pan_no")).sendKeys(panNo);
	driver.findElement(By.name("dob")).click();
	Robot ro1=new Robot();
	ro1.keyPress(KeyEvent.VK_2);
	ro1.keyPress(KeyEvent.VK_7);
	ro1.keyPress(KeyEvent.VK_0);
	ro1.keyPress(KeyEvent.VK_2);
	ro1.keyRelease(KeyEvent.VK_2);
	ro1.keyPress(KeyEvent.VK_2);
	ro1.keyPress(KeyEvent.VK_0);
	ro1.keyPress(KeyEvent.VK_2);
	ro1.keyPress(KeyEvent.VK_4);
	driver.findElement(By.name("last_trans")).sendKeys("0");

	String password = sheet.getRow(10).getCell(1).getStringCellValue();
	String confpassword = sheet.getRow(11).getCell(1).getStringCellValue();
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("cnfrm_password")).sendKeys(confpassword);
	driver.findElement(By.name("submit")).click();

	 String regpopup = driver.switchTo().alert().getText();
	 String internetAccountNumber="";
	 for(int i=0;i<regpopup.length();i++) {
		 if(Character.isDigit(regpopup.charAt(i))) {
			 internetAccountNumber+=regpopup.charAt(i);
		 }
	 }

	 System.out.println(internetAccountNumber);
	 driver.switchTo().alert().accept();
	 driver.findElement(By.xpath("//a[text()='Home']")).click();
	 driver.findElement(By.xpath("//a[text()='Staff Login']")).click();
	 driver.findElement(By.name("staff_id")).sendKeys(USERNAME);
	 driver.findElement(By.name("password")).sendKeys(PASSWORD);
	 driver.findElement(By.name("staff_login-btn")).click();
	 driver.findElement(By.name("credit_cust_ac")).click();
	 driver.findElement(By.name("customer_account_no")).sendKeys(arr);
	 
		}
	}


