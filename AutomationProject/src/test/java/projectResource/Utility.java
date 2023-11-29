package projectResource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {
	
	public static String searchData() throws IOException {
	//accessing excel file
	FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\testdata\\data.xlsx");
	
	XSSFWorkbook workbook=new XSSFWorkbook(file);//calling workbook
	XSSFSheet sheet=workbook.getSheet("Sheet1");//calling sheet
	XSSFRow row=sheet.getRow(0);//calling row
	XSSFCell cell=row.getCell(0);//calling row
	String value=String.valueOf(cell);	//passing cell value to 'value' variable
	workbook.close();//closing workbook
	file.close();//closing file
	return value;
}
	public static void ExcelData(int price1,int price2,int totalprice) throws IOException {
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\output.xlsx");
		//creating workbook
		XSSFWorkbook workbook=new XSSFWorkbook();
		//creating sheet
		XSSFSheet sheet=workbook.createSheet("sheet2");
		//creating row
		XSSFRow row0=sheet.createRow(0);
		//creating cell and assigning values
		row0.createCell(0).setCellValue("Price1");
		row0.createCell(1).setCellValue("Price2");
		row0.createCell(2).setCellValue("Total Price");	
		
		//creating cell and assigning values
		XSSFRow row=sheet.createRow(1);		
		row.createCell(0).setCellValue(price1);
		row.createCell(1).setCellValue(price2);
		row.createCell(2).setCellValue(totalprice);
		
		
		workbook.write(file);
		workbook.close();
		file.close();
	}

}
