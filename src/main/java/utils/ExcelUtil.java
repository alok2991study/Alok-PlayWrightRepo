package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {

        try {
            FileInputStream fis = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int rowNum, int colNum) {

        return sheet.getRow(rowNum)
                .getCell(colNum)
                .getStringCellValue();
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }
}