package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;




public class Excelpractive {
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	
	public static void main(String[] args) throws IOException {
	
		MultiArray();
		
	}
	public static void ExractRowData() throws IOException {
		fis = new FileInputStream("C:\\Programs_Download\\RestAssuredAPI\\Book1.xlsx");
		 wb = new XSSFWorkbook(fis);
		int sheets = wb.getNumberOfSheets();
		
		for(int i=0;i<sheets;i++) {
			if(wb.getSheetName(i).equalsIgnoreCase("datademo")) {
				ws = wb.getSheetAt(i);
				Iterator<Row> rows = ws.rowIterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				int k =0;
				int col = 0;
				
				while(ce.hasNext()) {
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						col = k;
					}
					k++;
				}
				
				while(rows.hasNext()) {
					Row val = rows.next();
					if(val.getCell(col).getStringCellValue().equalsIgnoreCase("Delete Profile")) {
						Iterator<Cell> it = val.cellIterator();
						while(it.hasNext()) {
							Cell cel = it.next();
							if(cel.getCellType() == CellType.STRING) {
								System.out.println("Obtained call val "+cel);
							}else {
								System.out.println(NumberToTextConverter.toText(cel.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
	}
	
	public static String[][] MultiArray() throws IOException{
		
		fis = new FileInputStream("C:\\Programs_Download\\TestData\\ExcelData.xlsx");
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet("sheet2");
		int lastRow = ws.getLastRowNum();
		
		System.out.println("LastRow : "+lastRow);
		Row row = ws.getRow(lastRow);
		int lastCol = row.getLastCellNum();
		
		String[][] arr = new String[lastRow][lastCol];
		
		for(int i=1;i<=lastRow;i++) {
			for(int j=0;j<lastCol;j++) {
				arr[i-1][j] = XLUtils.getCellData("C:\\Programs_Download\\TestData\\ExcelData.xlsx", "sheet2", i, j);
			}
		}
		for(int i=0;i<lastRow;i++) {
			for(int j=0;j<lastCol;j++) {
				System.out.println(i +" Val "+arr[i][j]);
			}
		}
		
		return arr;
	}
	
}
