package Staff;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.protobuf.Value;

public class AnotherwayToHandleApplicationPopup {

	
	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		String applNo="";
		String confirmPopup = "Application submitted successfully\r\n"
				+ "\r\n"
				+ "Application number : 393028305\r\n"
				+ "\r\n"
				+ "Please visit bank with application number for account approval\r\n"
				+ "\r\n"
				+ "Hint: From staff login, approve application\r\n"
				+ "Online Banking System";
		        String text = driver.switchTo().alert().getText();
		        for (int i = 0; i < confirmPopup.length(); i++) {
					if(Character.isDigit(text.charAt(i))) {
						String appl = text.charAt(i)+applNo;
						break;
					}
				}
		        
		
	}
}
