package PracticeTestNg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderAnnotation {
	
	
	
//	@Test(dataProvider = "customerData")
//	public void getDataOfMyTeam(String name,String place,int sal)
//	
//	{
//		System.out.println("Team Memb name are"+name+"-from----."+place+"  getting salary ==>"+sal);
//	}
	
	@DataProvider
	public Object[][] customerData()
	{
		Object[][] obj =new Object[3][3];
		
		
		obj[0][0]="Vaishnavi";
		obj[0][1]="Hubli";
		obj[0][2]=200000;
		
		
		obj[1][0]="Gokul";
		obj[1][1]="UttaraKhand";
		obj[1][2]=6992000;
		
		
		obj[2][0]="Sameer";
		obj[2][1]="Jarkhand";
		obj[2][2]= 100000;
		
		
		return obj;
		
	}

}
