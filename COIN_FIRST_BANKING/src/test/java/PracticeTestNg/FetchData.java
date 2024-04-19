package PracticeTestNg;

import org.testng.annotations.Test;

public class FetchData 
{

	@Test(dataProviderClass = DataProviderAnnotation.class, dataProvider  = "customerData")
	public void getDataOfMyTeam(String name,String place,int sal)

	{
		System.out.println("Team Memb name are"+name+"-from----."+place+"  getting salary ==>"+sal);
	}
	
	
	@Test(dataProviderClass = GetTheStaffDataFromExcel.class,dataProvider = "getStaffData")
	public void getcustData(String name,String loc,String sal)
	{
		System.out.println(name+"----->"+loc+"----->"+sal);
		
	}

}
