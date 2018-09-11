package com.programa.visualizacion.android;

import junit.framework.Assert;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.programa.visualizacion.android.database.ExcelImporter.importExcelDataFileOne;

public class ExcellImporterTest {

    @Test
    public void importExcelDataFileOneTest(){

        InputStream programme1 = this.getClass().getClassLoader().getResourceAsStream("programme1.xlsx");
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(programme1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(2);
        Assert.assertNotNull(sheet);
    }

    @Test
    public void importExcelDataFileTwoTest(){

        InputStream programme2 = this.getClass().getClassLoader().getResourceAsStream("programme2.xlsx");
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(programme2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(3);
        Assert.assertNotNull(sheet);
    }
}
