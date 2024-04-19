package PracticeTestNg;

import org.testng.annotations.DataProvider;

public class UsernamePwdCredentialDataProvider {
	@DataProvider
	public Object[][] userData()
	{
		Object [][] obj=new Object[2][2];
		
		obj[0][0]="210001";
		obj[0][1]="password";
		
		
		obj[1][0]="210002";
		obj[1][1]="passwords";
		
		return obj;
	}
	
	
	
	
	public Object[][] userData1()
	{
		Object [][] obj=new Object[2][2];
		
		obj[0][0]="210011";
		obj[0][1]="userlogin";
		
		
		obj[1][0]="210001";
		obj[1][1]="password";
		
		return obj;
	}
}
