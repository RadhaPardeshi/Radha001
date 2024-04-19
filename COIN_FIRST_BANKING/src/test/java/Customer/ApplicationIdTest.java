package Customer;

import java.util.Arrays;

public class ApplicationIdTest {

	public static void main(String[] args) {
		
		
		String confirmPopup = "Application submitted successfully\r\n"
				+ "\r\n"
				+ "Application number : 393028305\r\n"
				+ "\r\n"
				+ "Please visit bank with application number for account approval\r\n"
				+ "\r\n"
				+ "Hint: From staff login, approve application\r\n"
				+ "Online Banking System";
	
		String[] lines = confirmPopup.split("\\r?\\n");

		System.out.println(Arrays.toString(lines));
		// Search for the line containing the application number
		int applicationNumber = 0;
		for (String line : lines) {
			if (line.startsWith("Application number :")) {
				// Split the line to get the application number
				String[] parts = line.split(":");
				// Extract the application number string
				String applicationNumberStr = parts[1].trim();
				// Parse the application number string to integer
				applicationNumber = Integer.parseInt(applicationNumberStr);

				break; // Break the loop once the application number is found
			}
		}
		System.out.println(applicationNumber);
//		String[] tx = debitText.split("\\r?\\n");
//		System.out.println(tx);
//		System.out.println(Arrays.toString(line));
//		// Search for the line containing the application number
//		String debitNo = null ;
//		String PinNo = null ;
//		for (String li : tx) {
//			if (li.startsWith("Your Debit Card No is :") ) 
//			{
//				String[] dNo = li.split(":");
//				if(li.startsWith("Pin is :")) 
//				{
//					// Split the line to get the application number
//
//					String[] pinNo = li.split("and ");
//					// Extract the application number string
//					debitNo= dNo[1].trim();
//					PinNo = pinNo[1].trim();
//					// Parse the application number string to integer
//					a.accept();
//					break;
//				}
//			}
//		}
//		System.out.println(debitNo);
//		System.out.println(PinNo);
	}
}
