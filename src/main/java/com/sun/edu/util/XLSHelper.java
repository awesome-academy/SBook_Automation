package com.sun.edu.util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLSHelper {
	public static String filelocation;

	private static HSSFWorkbook wb = null;
	private static HSSFSheet ws = null;

//	public static void main(String[] args){
//		readXLS();
//		int r=2;
//		int c=2;
//		retrieveCellsMulti(r,c);
//	}

	public static void readXLS(String file) {
		FileInputStream ipstr = null;
		try {
			ipstr = new FileInputStream(Settings.getSetting(Settings.RESOURCE_ROOT) + file);
			wb = new HSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
//			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int retrieveNoOfRows() {
		return ws.getLastRowNum();
	}

	public static int retrieveNoOfCols() {
		return ws.getRow(0).getLastCellNum();
	}

	public static void main(String[] args) {
		XLSHelper.retrieveCells(Settings.getSetting(Settings.USER_FILE), 2, 2);
	}

	public static Object[][] retrieveCells(String file, int startRw, int endRw) {
		readXLS(file);
		int colNum = retrieveNoOfCols();

		Object[][] data = new Object[endRw - startRw + 1][colNum];
		int element = 0;
		for (int i = startRw - 1; i < endRw; i++) {
			HSSFRow row = ws.getRow(i);

//			System.out.println(element);
			for (int j = 0; j < colNum; j++) {
				if (row.getCell(j) == null) {

					data[element][j] = "";

				} else {
					data[element][j] = row.getCell(j).getStringCellValue();

//					System.out.println(row.getCell(j).getStringCellValue());

				}

			}
			element++;

		}
		return data;
	}

	public static Object[][] retrieveCells(String file, int sheetNum, int rowNum1, int rowNum2, int colNum) {
		readXLS(file);
		int rowNum = rowNum2 - rowNum1 + 1;
		Object data[][] = new Object[rowNum][colNum];
		int temp = 0;
		for (int i = rowNum1 - 1; i < rowNum2; i++) {
			HSSFRow row = wb.getSheetAt(sheetNum).getRow(i);
			for (int j = 0; j < colNum; j++) {
				if (row.getCell(j) == null) {
					data[temp][j] = "";
				} else {
					data[temp][j] = row.getCell(j).getStringCellValue();
				}
			}
			temp++;
		}
		return data;

	}
}
