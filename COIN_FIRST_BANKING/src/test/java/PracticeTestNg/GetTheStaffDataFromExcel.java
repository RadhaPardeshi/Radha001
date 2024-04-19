package PracticeTestNg;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.GenericTtilities.IPathConstant;

public class GetTheStaffDataFromExcel {
	
	@DataProvider
	public Object[][] getStaffData() throws EncryptedDocumentException, IOException
	{
		
		FileInputStream fis =new FileInputStream(IPathConstant.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("CustomerData");
		int rowcount = sh.getLastRowNum();//or getPhysicalNumberOfRows()
		short cellcount = sh.getRow(0).getLastCellNum();
		Object [][] obj=new Object[rowcount+1][cellcount];
		
		for (int i = 0; i <= rowcount ; i++) 
		{
			for (int j = 0; j < cellcount; j++) 
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
			
		}
		return obj;	
	}


}
