package automation.vaccinationSlot.utils;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import automation.vaccinationSlot.helper.generic.ResourceHelper;
import automation.vaccinationSlot.helper.logger.LoggerHelper;

public class ExcelReader {

	private Logger log = LoggerHelper.getLogger(ExcelReader.class);

	public void getExcelDate(String excelFileName, String sheetName) {
		try {
			String dataset[][];
			FileInputStream fis = ResourceHelper.getResourcePathInputStream(excelFileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			log.info(workbook);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			log.info(sheet);
			int totalRow = sheet.getLastRowNum() + 1;
			
			int totalColumn = sheet.getRow(0).getLastCellNum();
		
			// Create array of rows and column
			dataset = new String[totalRow - 1][totalColumn];
			
			int i=0;
			int j=0;
			
			for (Row row : sheet) {
				 j++;
				for (Cell cell : row) 
				{
					if(cell.getCellType()==CellType.STRING)
					{
						dataset[i][j++]=cell.getStringCellValue();
					}
				}
			}
			//return dataset;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
