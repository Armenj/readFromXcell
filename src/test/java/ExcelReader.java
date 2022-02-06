import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private final String excelFilePath;
    private XSSFSheet sheet; // для работы со страницей
    private XSSFWorkbook book; // для работы с икселем

    public ExcelReader(String excelFilePath) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet("Лист 1");
        }catch (IOException e){
            throw new IOException("Не поддерживаемый формат");
        }
    }

    public ExcelReader(String excelFilePath, String sheetName) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName);
        }catch (IOException e){
            throw new IOException("Не поддерживаемый формат");
        }
    }

    private String cellToString(XSSFCell cell) throws Exception { // метод который читает ячейку
        Object result = null;
        CellType type = cell.getCellType();
        switch (type){
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default: throw new Exception("Ошибка чтения");
        }
        return result.toString();
    }

    private int xlsxCountColumn(){
        return sheet.getRow(0).getLastCellNum();
    }

    private int xlsxCountRow(){
        return sheet.getLastRowNum() + 1;
    }

    public String[][] getSheetDataForTdd() throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet("Лист 1");
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows -1][numberOfColumn];
        for(int i = 1; i<numberOfRows; i++){
            for(int j = 0; j<numberOfColumn; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if(value == null){
                    System.out.println("Пустые ячейки");
                }
            }
        }
        return data;
    }

    public String[][] getSheetDataForTdd(String sheetName) throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows -1][numberOfColumn];
        for(int i = 1; i<numberOfRows; i++){
            for(int j = 0; j<numberOfColumn; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if(value == null){
                    System.out.println("Пустые ячейки");
                }
            }
        }
        return data;
    }
}
