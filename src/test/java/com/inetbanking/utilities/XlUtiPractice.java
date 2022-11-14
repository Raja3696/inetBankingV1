package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlUtiPractice {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String path = "C:\\Users\\raja9\\OneDrive\\Documents\\AResume\\LoginDetails.xlsx";
	
	public static void main(String[] args) throws IOException {
		getRowCount(path, "sheet1");
		getCellCount(path, "sheet1",1);
		getCellData(path, "sheet1",1, 1);
	}
	
	public static void getRowCount(String xlFile, String xlSheet) throws IOException {
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlSheet);
		int rowCount = ws.getLastRowNum();
		System.out.println(rowCount);
		fis.close();
		wb.close();
	}
	
	public static void getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException{
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		System.out.println("cellCount  "+cellCount);
		fis.close();
		wb.close();
	}
	public static void getCellData(String xlFile, String xlSheet, int rowNum, int cellNum) throws IOException{
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(cellNum);
		String Data = null;
		try {
			DataFormatter format = new DataFormatter();
			String cellData = format.formatCellValue(cell);
			System.out.println(cellData);
		}catch(Exception e) {
			Data = "";
		}
		fis.close();
		wb.close();
		System.out.println(Data);
	}
	
	
	
}
