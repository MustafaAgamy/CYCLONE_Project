package com.ej.factories.testData;

import com.ej.properties.setup.Test;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
@SuppressWarnings("unused")
public class ReadEXCEL {

    private final String excelFilePath;
    private static final Test testSetups = ConfigFactory.create(Test.class);
    private static final String TEST_DATA_DEFAULT_FOLDER = testSetups.testDataDefaultFolder();


    public ReadEXCEL(String excelFilePath){
        this.excelFilePath = excelFilePath;
    }


    public String readFromExcel(String headerKey, int rowIndex){
       return readFromExcel(null, headerKey, rowIndex);
    }

    public  String readFromExcel(Object sheetIndex, String headerKey, int rowIndex) {
        try {
            File file = new File(TEST_DATA_DEFAULT_FOLDER + excelFilePath);
            DataFormatter dataFormatter = new DataFormatter();
            if (!file.exists()) {
                throw new FileNotFoundException("ExcelFile Not Found");
            }
            if(file.length() == 0){
                throw new NullPointerException("ExcelFile content is Null");
            }
            if (rowIndex == 0) {
                throw new IllegalArgumentException("rowIndex Cannot be 0");
            }

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet;
            if(sheetIndex == null){
               sheet = workbook.getSheetAt(0);
            }
            else {
                sheet = workbook.getSheetAt((Integer) sheetIndex);
            }
            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (dataFormatter.formatCellValue(cell).equalsIgnoreCase(headerKey)) {
                    columnIndex = i;
                    break;
                }
            }
            if (columnIndex != -1) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    throw new NullPointerException("rowIndex cannot contain a Null value");
                }
                Cell cell = row.getCell(columnIndex);
                if (cell == null) {
                    throw new NullPointerException("Cell cannot contain a Null value");
                }
                String cellValue = dataFormatter.formatCellValue(cell);

                workbook.close();

                return cellValue;
            } else {
                throw new NullPointerException("Cell headerKey doesn't Exist");
            }

        } catch (InvalidFormatException | IOException | NotOfficeXmlFileException e) {
            throw new RuntimeException(e);
        }
    }


//    public  String readFromExcel(String headerKey, int rowIndex) {
//        try {
//            File file = new File(TEST_DATA_DEFAULT_FOLDER + excelFilePath);
//            DataFormatter dataFormatter = new DataFormatter();
//            if (!file.exists()) {
//                throw new FileNotFoundException("File Not Found");
//            }
//            if(file.length() == 0){
//                throw new NullPointerException("File content is Null");
//            }
//            if (rowIndex == 0) {
//                throw new IllegalArgumentException("rowIndex Cannot be 0");
//            }
//
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Row headerRow = sheet.getRow(0);
//            int columnIndex = -1;
//            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//                Cell cell = headerRow.getCell(i);
//                if (dataFormatter.formatCellValue(cell).equalsIgnoreCase(headerKey)) {
//                    columnIndex = i;
//                    break;
//                }
//            }
//            if (columnIndex != -1) {
//                Row row = sheet.getRow(rowIndex);
//                if (row == null) {
//                    throw new NullPointerException("rowIndex cannot contain a Null value");
//                }
//                Cell cell = row.getCell(columnIndex);
//                if (cell == null) {
//                    throw new NullPointerException("Cell cannot contain a Null value");
//                }
//                String cellValue = dataFormatter.formatCellValue(cell);
//
//                workbook.close();
//
//                return cellValue;
//            } else {
//                throw new NullPointerException("headerKey doesn't Exist");
//            }
//
//        } catch (InvalidFormatException | IOException | NotOfficeXmlFileException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
