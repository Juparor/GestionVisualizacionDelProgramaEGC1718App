package com.programa.visualizacion.android;

import android.util.Log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jupar on 12/01/2018.
 */

public class ExcelImporter {

    public static List<String> importExcelDataFileOne(InputStream inputStream) {

        List<String> listOfRows = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(2);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            int rowsLimit = numberOfRows - 25;
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = sheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns - 1;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                    }
                }
                listOfRows.add(lineOfRow);
            }

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException: ", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException: ", e.getMessage());
        }

        return listOfRows;
    }

    public static List<String> importExcelDataFileTwo(InputStream inputStream) {

        List<String> listOfRows = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(3);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            int rowsLimit = numberOfRows - 34;
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = sheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns - 4;

                String lineOfRow = "";

                for (int column = 3; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                    }
                }
                listOfRows.add(lineOfRow);
            }

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException: ", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException: ", e.getMessage());
        }

        return listOfRows;
    }

    public static List<String> importExcelDataFileOneSchedule(InputStream inputStream) {

        List<String> listOfRows = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet mondaySheet = workbook.getSheetAt(0);
            int numberOfRows = mondaySheet.getPhysicalNumberOfRows();
            int rowsLimit = numberOfRows;
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = mondaySheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                        lineOfRow += "--" + mondaySheet.getSheetName();
                    }
                }
                listOfRows.add(lineOfRow);
            }

            XSSFSheet tuesdaySheet = workbook.getSheetAt(1);
            numberOfRows = tuesdaySheet.getPhysicalNumberOfRows();
            rowsLimit = numberOfRows;

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = tuesdaySheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                        lineOfRow += "--" + tuesdaySheet.getSheetName();
                    }
                }
                listOfRows.add(lineOfRow);
            }

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException: ", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException: ", e.getMessage());
        }

        return listOfRows;
    }

    public static List<String> importExcelDataFileTwoSchedule(InputStream inputStream) {

        List<String> listOfRows = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet wednesdaySheet = workbook.getSheetAt(0);
            int numberOfRows = wednesdaySheet.getPhysicalNumberOfRows();
            int rowsLimit = numberOfRows - 1;
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = wednesdaySheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                        lineOfRow += "--" + wednesdaySheet.getSheetName();
                    }
                }
                listOfRows.add(lineOfRow);
            }

            XSSFSheet thursdaySheet = workbook.getSheetAt(1);
            numberOfRows = thursdaySheet.getPhysicalNumberOfRows();
            rowsLimit = numberOfRows;
            formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = thursdaySheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                        lineOfRow += "--" + thursdaySheet.getSheetName();
                    }
                }
                listOfRows.add(lineOfRow);
            }

            XSSFSheet fridaySheet = workbook.getSheetAt(2);
            numberOfRows = fridaySheet.getPhysicalNumberOfRows();
            rowsLimit = numberOfRows;
            formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int row = 1; row < rowsLimit; row++) {
                Row actualRow = fridaySheet.getRow(row);
                int numberOfColumns = actualRow.getPhysicalNumberOfCells();
                int columnsLimit = numberOfColumns;

                String lineOfRow = "";

                for (int column = 0; column < columnsLimit; column++) {
                    String value = getCellAsString(actualRow, column, formulaEvaluator);

                    if (column != columnsLimit - 1) {
                        lineOfRow += value + "--";
                    } else {
                        lineOfRow += value;
                        lineOfRow += "--" + fridaySheet.getSheetName();
                    }
                }
                listOfRows.add(lineOfRow);
            }

        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException: ", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException: ", e.getMessage());
        }

        return listOfRows;
    }

    private static String getCellAsString(Row actualRow, int column, FormulaEvaluator formulaEvaluator) {
        String result = "";
        try {
            Cell cell = actualRow.getCell(column);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    result = "";
                case Cell.CELL_TYPE_STRING:
                    result = "" + cellValue.getStringValue();
                default:
                    result = "" + cellValue.getStringValue();
            }
        } catch (NullPointerException e) {
            Log.e("getCellAsString: ", e.getMessage());
        }
        return result;
    }
}
