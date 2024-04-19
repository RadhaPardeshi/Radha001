package Staff;



import java.awt.AWTException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.GenericTtilities.ExcelUtility;
import com.GenericTtilities.FileUtility;
import com.GenericTtilities.JavaUtility;
import com.GenericTtilities.WebdiverUtility;
import com.netBanking.ObjectRepository.pom.ApprovePendingAccountPage;
import com.netBanking.ObjectRepository.pom.HomePage;
import com.netBanking.ObjectRepository.pom.OnlineActOpeningPage;
import com.netBanking.ObjectRepository.pom.StaffHomePage;
import com.netBanking.ObjectRepository.pom.StaffLoginPage;

	public class UserAccountApprovalStaffPOMTest {
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

			HomePage homePage =new HomePage(driver);
			OnlineActOpeningPage onlineActOpeningPage = new OnlineActOpeningPage(driver);
			//driver.findElement(By.xpath("//li[text()='Open Account']")).click();
			homePage.getOpenAccountBtn().click();



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

			//driver.findElement(By.name("name")).sendKeys(Name+jUtility.random());
			String CustName = Name+jUtility.random();
			onlineActOpeningPage.selectNameTxtfld(CustName);

			//			WebElement gen = driver.findElement(By.name("gender"));
			//	      wUtility.selectdropDownByVisibleText(gen, gender);
			onlineActOpeningPage.selectgenderDropDown(gender, wUtility);

			//driver.findElement(By.name("mobile")).sendKeys(mobileNo);
			onlineActOpeningPage.enetrMobileTxtfld(mobileNo);
			//driver.findElement(By.name("email")).sendKeys(emailId);
			onlineActOpeningPage.enetrEmailTxtfld(emailId);
			//driver.findElement(By.name("landline")).sendKeys(landline);
			onlineActOpeningPage.enetrlandlineTxtField(landline);
			//driver.findElement(By.name("pan_no")).sendKeys(panNo);
			onlineActOpeningPage.eneterPanTxtField(panNo);
			//driver.findElement(By.name("citizenship")).sendKeys(citizenship);
			onlineActOpeningPage.eneterCitizenNoTxtField(citizenship);
			//driver.findElement(By.name("homeaddrs")).sendKeys(homeAdd);
			onlineActOpeningPage.eneterHomeAddsTxtField(homeAdd);
			//driver.findElement(By.name("officeaddrs")).sendKeys(homeAdd);
			onlineActOpeningPage.enterOfficeAddTxtField(offAdd);


			//			WebElement stat = driver.findElement(By.name("state"));
			//			wUtility.selectdropDownByVisibleText(stat, state);
			onlineActOpeningPage.enterStateDropDown(state, wUtility);

			//			WebElement cit = driver.findElement(By.name("city"));
			//			wUtility.selectdropDownByVisibleText(cit, city);
			onlineActOpeningPage.eneterCityDropDown(city, wUtility);

			//driver.findElement(By.name("pin")).sendKeys(pin);
			onlineActOpeningPage.eneterPinTxtField(pin);

			//driver.findElement(By.name("arealoc")).sendKeys(area);
			onlineActOpeningPage.eneterareaLocTxtField(area);

			//			WebElement accot = driver.findElement(By.name("acctype"));
			//			wUtility.selectdropDownByVisibleText(accot, account);
			onlineActOpeningPage.eneterAccTypeDropDown(account, wUtility);


			//driver.findElement(By.name("dob")).click();
			onlineActOpeningPage.enetrDobTxtField();


			//driver.findElement(By.name("submit")).click();
			onlineActOpeningPage.getSubmitBtn().click();

			//driver.findElement(By.name("cnfrm-submit")).click();
			onlineActOpeningPage.clickcnfSubmitBtn();

			Alert a = wUtility.objectAlert(driver);

			String apptext=a.getText();

			String[] lines = apptext.split("\\r?\\n");

			System.out.println(Arrays.toString(lines));
			// Search for the line containing the application number
			
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
			//driver.findElement(By.xpath("//a[text()='Staff Login']")).click();
			homePage.getStaffLoginLink().click();

			StaffLoginPage staffLoginPage = new StaffLoginPage(driver);

			String USERNAME= fUtility.readDataFromPropertyFile("username");
			String PASSWORD = fUtility.readDataFromPropertyFile("password");


			//driver.findElement(By.name("staff_id")).sendKeys(USERNAME);
			staffLoginPage.enterStaffIdFiled(USERNAME);

			//driver.findElement(By.name("password")).sendKeys(PASSWORD);
			staffLoginPage.enterPasswordFiled(PASSWORD);

			//driver.findElement(By.name("staff_login-btn")).click();
			staffLoginPage.getloginBtn().click();
			//Alert addpopup = driver.switchTo().alert();
			//addpopup.accept();

			StaffHomePage staffHomePage =new StaffHomePage(driver);
			//driver.findElement(By.name("apprvac")).click();
			staffHomePage.getApprovePenAccBtn().click();


			ApprovePendingAccountPage approvePendingAccountPage=new ApprovePendingAccountPage(driver);
			//driver.findElement(By.name("application_no")).sendKeys(applicationNumberStr);
			approvePendingAccountPage.eneterApplicationNoTxt(applicationNumberStr);

			//driver.findElement(By.name("search_application")).click();
			approvePendingAccountPage.getSearchAppBtn().click();
			//Thread.sleep(2000);
			//WebElement eleapp = approvePendingAccountPage.getApproveCustBtn();
			//String popupText = eleapp.getText();
			//driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]")).getText();

			//System.out.println(popupText);
			approvePendingAccountPage.getApproveCustBtn().click();Thread.sleep(5000);
			Alert aler = wUtility.objectAlert(driver);Thread.sleep(5000);
			 String txt=aler.getText();Thread.sleep(5000);
			String[] line = txt.split("\\r?\\n");
			System.out.println(Arrays.toString(line));
			// Search for the line containing the application number
			String accountID =null ;
			for (String li : line) {
				if (li.startsWith("Account no :")) {
					// Split the line to get the application number
					String[] parts = li.split(":");
					// Extract the application number string
					accountID = parts[1].trim();
					// Parse the application number string to integer
					//accountNumber = Integer.parseInt(account);
					break;
				}
				System.out.println(accountID);

			}
			Thread.sleep(5000);
			wUtility.acceptAlert(driver);
			//driver.findElement(By.name("logout_btn")).click();
			staffLoginPage.getloginBtn().click();;
			System.out.println("Customer Approved successfully with account Id"+accountID+"For further Process");
		}
	}

