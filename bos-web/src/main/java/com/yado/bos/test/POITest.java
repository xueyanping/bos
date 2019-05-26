package com.yado.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



public class POITest {

	/*@Test
	public void test1() {
		String path = "E:\\区域导入测试数据.xls";
		String fileName = "区域导入测试数据.xls";
		try {
			HSSFWorkbook hsw = new HSSFWorkbook(new FileInputStream(new File(path)));
			//读取文件中的第一个sheet标签项
			HSSFSheet sheetAt = hsw.getSheetAt(0);
			//遍历标签页中的所有行
			for (Row row : sheetAt) {
				System.out.println();
				for (Cell cell : row) {
					String value = cell.getStringCellValue();
					System.out.print(value+" ");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
