package Staff;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class InternetBankingRegisterTest {

	public String fetchDatafromPropertyFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("\\src\\test\\resources\\commondata.property");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);

	}

	public static void main(String[] args) throws Exception {

		InternetBankingRegisterTest internetBanking = new InternetBankingRegisterTest();
		String browser = internetBanking.fetchDatafromPropertyFile("browser");

//		Random r = new Random();
//		int randomNum = r.nextInt(100);

		// Getting the data from Excel File
		FileInputStream fis = new FileInputStream("\\src\\test\\resources\\Coin1TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("CustomerAccount");
		Row row = sheet.getRow(1);
		

		// Opening the browser

		WebDriver driver;
		if (browser.equalsIgnoreCase("chorme")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Navigating to the url
		String url = internetBanking.fetchDatafromPropertyFile("url");
		driver.get(url);

		driver.findElement(By.xpath("//li[text()='Apply Debit Card']")).click();

		if (driver.getTitle().contains("Apply Debit Card")) {
			System.out.println("Apply Debit Card Page is Displayed");
		}
        Thread.sleep(2000);
		driver.findElement(By.name("holder_name")).sendKeys(row.getCell(0).getStringCellValue());
        
		Thread.sleep(2000);

		driver.findElement(By.name("dob")).click();
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_3);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_3);
        Thread.sleep(2000);

		driver.findElement(By.name("pan")).sendKeys(row.getCell(2).getStringCellValue());
        Thread.sleep(2000);

		driver.findElement(By.name("mob")).sendKeys(row.getCell(1).getStringCellValue());
        Thread.sleep(2000);

		driver.findElement(By.name("acc_no")).sendKeys(row.getCell(3).getStringCellValue());
        Thread.sleep(2000);

		driver.findElement(By.name("dbt_crd_submit")).click();
		
		
		
		
        String text=driver.switchTo().alert().getText();
        //Printing the text from alert popup
		System.out.println(text);
		//Storing the Debit card number is Pin in the excel
		String[] arr=internetBanking.fetchDebitAndPin(text);
		
		row.createCell(5).setCellValue(arr[0]);
		row.createCell(6).setCellValue(arr[1]);

		internetBanking.saveDataInExcel(workbook);
//		FileOutputStream fOut= new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
//
//		workbook.write(fOut);
//		
		
		//Handling the alert pop up
		
		driver.switchTo().alert().accept();
		
		//Navigating the home page
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		
		if(driver.getTitle().contains("Online Banking System")){
			System.out.println("Navigated to home page and Home page is displayed ");
		}
		
		//Clicking on Internet Banking to register for internet banking
//		driver.findElement(By.id("ebanking")).click();
		 WebElement el=driver.findElement(By.xpath("//a[contains(text(),'Internet Banking')]"));
		    WebElement element= driver.findElement(By.xpath("//li[contains(text(),'Register')]"));
		    JavascriptExecutor js=(JavascriptExecutor)driver;
		    js.executeScript("arguments[0].scrollIntoView(true)", el);
		        Actions action =new Actions(driver);
		    action.moveToElement(el).perform();
		    element.click();
		    //Validating the  Internet banking registration page
		if(driver.getTitle().contains("Internet Banking Registration")) {
			System.out.println("Internet Banking Registration page is displayed");
		}
		
		//Filling the Data in internet banking registration
		//Name
		Thread.sleep(2000);
		driver.findElement(By.name("holder_name")).sendKeys(row.getCell(0).getStringCellValue());
		Thread.sleep(2000);
		//Account 
		Thread.sleep(2000);
		driver.findElement(By.name("accnum")).sendKeys(row.getCell(3).getStringCellValue());
		//Debit Card Number
		driver.findElement(By.name("dbtcard")).sendKeys(row.getCell(5).getStringCellValue());
		    
		Thread.sleep(2000);
		//Debit Card Pin
		driver.findElement(By.name("dbtpin")).sendKeys(row.getCell(6).getStringCellValue());
		
		Thread.sleep(2000);
		//Registerd Mobile Number
		driver.findElement(By.name("mobile")).sendKeys(row.getCell(1).getStringCellValue());
		Thread.sleep(2000);
		//Pan Number
		driver.findElement(By.name("pan_no")).sendKeys(row.getCell(2).getStringCellValue());
		//Date Of Birth
		driver.findElement(By.name("dob")).click();
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_3);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_3);
		
		
//		String cell0=sheet.getRow(5).getCell(0).getStringCellValue();
//		String cell1=sheet.getRow(5).getCell(1).getStringCellValue();
//		System.out.println(cell0);
//		System.out.println(cell1);
		Thread.sleep(2000);
	    //Last Transaction
		driver.findElement(By.name("last_trans")).sendKeys("0");
		Thread.sleep(2000);
		//Password
		driver.findElement(By.name("password")).sendKeys(sheet.getRow(5).getCell(0).getStringCellValue());
		Thread.sleep(2000);
		//Confirm Password
		driver.findElement(By.name("cnfrm_password")).sendKeys(sheet.getRow(5).getCell(1).getStringCellValue());
		Thread.sleep(2000);
		//Submiting the value
		driver.findElement(By.name("submit")).click();
		
		Thread.sleep(2000);
		System.out.println("Internet Banking registration is successfull");
		
		Thread.sleep(2000);
		String internetBankingText=driver.switchTo().alert().getText();
		String internetAccountNumber="";
	     for(int i=0;i<internetBankingText.length();i++) {
	    	 if(Character.isDigit(internetBankingText.charAt(i))) {
	    		 internetAccountNumber+=internetBankingText.charAt(i);
	    	 }
	     }
		
	     System.out.println(internetAccountNumber);
	     sheet.getRow(5).createCell(2).setCellValue(internetAccountNumber);
	     internetBanking.saveDataInExcel(workbook);
	     Thread.sleep(2000);
	     driver.switchTo().alert().accept();
		
	     driver.findElement(By.xpath("//a[text()='Home']")).click();
	     
	     System.out.println("Navigated to Home page");
	    
	     //Clicking on internetbanking and then login 
	     WebElement e2=driver.findElement(By.xpath("//a[contains(text(),'Internet Banking')]"));
		    WebElement element2= driver.findElement(By.xpath("//li[contains(text(),'Login')]"));
		    JavascriptExecutor js1=(JavascriptExecutor)driver;
		    js1.executeScript("arguments[0].scrollIntoView(true)", e2);
		        Actions action1 =new Actions(driver);
		    action.moveToElement(e2).perform();
		    element2.click();
	     
	    if(driver.getTitle().contains("Login Page")) {
	    	System.out.println("Login Page is displayed");
	    }
		
	    driver.findElement(By.name("customer_id")).sendKeys(sheet.getRow(5).getCell(2).getStringCellValue());
	    driver.findElement(By.name("password")).sendKeys(sheet.getRow(5).getCell(0).getStringCellValue());
	    driver.findElement(By.name("login-btn")).click();
	    
	    if(driver.getTitle().equals("My Profile")) {
	    	System.out.println("Login successfull! and My Profile page is displayed");
	    }
	    
	    driver.findElement(By.name("logout_btn")).click();
	    
	    
	    if(driver.getTitle().equals("My Profile")) {
	    	System.out.println("Logout successfull! and My Profile page is displayed");
	    }
	    
	}
	public String[] fetchDebitAndPin(String text) {

		String[] lines = text.split("\\r?\\n");
		System.out.println(Arrays.toString(lines));
		// Search for the line containing the application number

		String debitCardNum = "";
		String pin = "";
		String[] parts = null;
		;
		for (String line : lines) {

			if (line.contains("Your Debit Card No is : ")) {
				// Split the line to get the application number
				parts = line.trim().split(" ");

				for (String part : parts) {
					if (isNumeric(part)) {
						if (debitCardNum.isEmpty()) {
							debitCardNum = part;
						} else {
							pin = part;
							break;
						}
					}
				}

				// Break the loop once the application number is found
			}
		}
		String[] arr= {debitCardNum,pin};
		return arr;
		
	
		
	}
	public boolean isNumeric(String str) {

		for (int i = 0; i < str.length(); i++) {

			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public void saveDataInExcel(Workbook workbook) throws IOException {

		FileOutputStream fOut= new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");

		workbook.write(fOut);
	}
	}
	