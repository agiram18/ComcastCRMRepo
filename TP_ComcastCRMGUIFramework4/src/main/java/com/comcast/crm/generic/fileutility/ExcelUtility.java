package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName , int rowNum ,int cellNum) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testData/Book 1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;	
	}
	
	public int getRowcount(String sheet) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testData/Book 1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheetName , int rowNum , int cellNum , String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./testData/Book 1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos = new FileOutputStream("./testData/Book 1.xlsx");
		wb.write(fos);
		wb.close();
	}
	

}
