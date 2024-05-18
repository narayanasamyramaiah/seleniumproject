package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	private static XSSFCell cell;

	public void setExcelFile(String excelFilePath, String sheetName) throws IOException {
		// Create an object of File class to open xls file
		File file = new File(excelFilePath);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		// creating workbook instance that refers to .xls file
		workbook = new XSSFWorkbook(inputStream);

		// creating a Sheet object
		sheet = workbook.getSheet(sheetName);

	}

	public String getCellData(int rowNumber, int cellNumber) {
		// getting the cell value from rowNumber and cell Number
		
		cell = sheet.getRow(rowNumber).getCell(cellNumber);
		cell.setCellType(CellType.STRING);
		String cellvalue=cell.getStringCellValue();;
//		if (cell.getCellType() == CellType.NUMERIC) {
//            // If the cell type is numeric, get the numeric value
//			cellvalue =  String.valueOf(cell.getNumericCellValue());
//          
//        } else if (cell.getCellType() == CellType.STRING) {
//            // If the cell type is string, get the string value
//             cellvalue = cell.getStringCellValue();
//          
//        } else {
//            // Handle other types (like formulas, boolean, etc.) as needed
//            System.out.println("Cell is not numeric or string.");
//        }
//		// returning the cell value as string
//		//return cell.getStringCellValue().toString();
		return cellvalue;
	}

	public int getRowCountInSheet() {
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		return rowcount;
	}

	public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException {
		// creating a new cell in row and setting value to it
		sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);

		//sheet.getRow(rowNum).getCell(cellNum).setCellValue(cellValue);
		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
		
	}

	public void ReadExcelFile(String excelfilePath) {
		try {
			File s = new File(excelfilePath);
			FileInputStream stream = new FileInputStream(s);
			workbook = new XSSFWorkbook(stream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getData(int sheetnumber, int row, int column) {
		sheet = workbook.getSheetAt(sheetnumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}

	public int getRowCount(int sheetIndex) {
		int row = workbook.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}
	
	public void killInstance() throws IOException
	{
		
		workbook.close();
		
		
	}
}