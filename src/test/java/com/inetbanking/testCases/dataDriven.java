package com.inetbanking.testCases;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public ArrayList<String> getData(String testName) throws IOException {
		ArrayList<String> li = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Programs_Download\\RestAssuredAPI\\Book1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		int count = 0;
		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.rowIterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				int k = 0;
				int col = 0;
				while (ce.hasNext()) {

					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase(testName)) {

						col = k;
						System.out.println("Index is " + col);
					}
					k++;
				}
				while (rows.hasNext()) {
					Row value = rows.next();
					if (value.getCell(col).getStringCellValue().equalsIgnoreCase("Purchase")) {
						Iterator<Cell> cel = value.cellIterator();
						while (cel.hasNext()) {
							Cell val = cel.next();
							if(val.getCellType() == CellType.STRING) {
								li.add(val.getStringCellValue());
							}else {
								li.add(NumberToTextConverter.toText(val.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return li;
	}
}
