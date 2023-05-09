package com.steady.reconciler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoreService {
    public static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";
    DataFormatter dataFormatter = new DataFormatter();


    public static void readSpreadSheets(){
        try{
            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
            log.info("Workbook has {} Sheets :", workbook.getNumberOfSheets() );

            workbook.forEach(sheet -> {
                log.info("=> {}", sheet.getSheetName());
                sheet.forEach(row -> {
                    row.forEach(cell -> {
                        printCellValue(cell);
                    });
                    System.out.println();
                });
            });

            workbook.close();

        }catch (IOException io){
            log.info("Error happened::::: {}", io.getMessage());
            io.printStackTrace();
        }

    }

    private static Object printCellValue(Cell cell) {
        Object cellValue = null;
        switch (cell.getCellType()) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case STRING:
                cellValue = (cell.getRichStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }
}
