package ecom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelDataUtil {

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\ecom\\Testdata\\LoginTestData.xlsx";

    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timestamp = String.format("%1$tY%1$tm%1$td_%1$tH%1$tM%1$tS", date);
        return "ashvini" + timestamp + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_PATH));
        XSSFWorkbook workbook = null;
        Object[][] data = null;

        try {
            workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows(); // Use getPhysicalNumberOfRows() instead of getLastRowNum() for accurate row count
            int cols = sheet.getRow(0).getLastCellNum();
            
            data = new Object[rows - 1][cols]; // Excluding header row

            for (int i = 1; i < rows; i++) { // Starting from 1 to skip header row
                XSSFRow row = sheet.getRow(i);

                if (row != null) {
                    for (int j = 0; j < cols; j++) {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    data[i - 1][j] = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                                        data[i - 1][j] = cell.getDateCellValue();
                                    } else {
                                        data[i - 1][j] = cell.getNumericCellValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    data[i - 1][j] = cell.getBooleanCellValue();
                                    break;
                                default:
                                    data[i - 1][j] = "";
                                    break;
                            }
                        } else {
                            data[i - 1][j] = ""; // Handle null cells
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error reading the Excel file", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileInputStream.close();
        }

        return data;
    }

	public static String captureScreenshot(WebDriver driver, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
