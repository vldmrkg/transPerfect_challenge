package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * Writes a list of employees to an Excel file.
     *
     * @param employees List of employee data (each employee is a map with keys like "Name", "JobTitle", etc.)
     * @param filePath  Path to the output Excel file
     */
    public static void writeEmployeesToExcel(List<Map<String, String>> employees, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        String[] columns = {"Name", "JobTitle", "Phone", "Address"};

        // Create header row
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        // Write employee data rows
        for (Map<String, String> emp : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(emp.get("Name"));
            row.createCell(1).setCellValue(emp.get("JobTitle"));
            row.createCell(2).setCellValue(emp.get("Phone"));
            row.createCell(3).setCellValue(emp.get("Address"));
        }

        // Save the Excel file to disk
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to safely extract the string value from a cell.
     *
     * @param cell Cell to extract value from
     * @return String value of the cell
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue()).trim();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue()).trim();
            default:
                return "";
        }
    }

    /**
     * Verifies that the data in the exported Excel file matches the expected data.
     *
     * @param filePath        Path to the exported Excel file
     * @param expectedData    List of expected employee records
     * @param expectedColumns Expected column names in the Excel header
     * @return true if the file matches the expected structure and data, false otherwise
     */
    public static boolean verifyExportedExcel(String filePath, List<Map<String, String>> expectedData, List<String> expectedColumns) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null || sheet.getPhysicalNumberOfRows() == 0) {
                return false;
            }

            // Read header row
            Row headerRow = sheet.getRow(0);
            List<String> actualColumns = new ArrayList<>();
            for (Cell cell : headerRow) {
                actualColumns.add(cell.getStringCellValue());
            }

            // Validate column headers
            if (expectedColumns != null && !actualColumns.containsAll(expectedColumns)) {
                return false;
            }

            // Validate row-by-row data
            for (int i = 0; i < expectedData.size(); i++) {
                Row row = sheet.getRow(i + 1); // Skip header
                if (row == null) continue;

                Map<String, String> expectedRow = expectedData.get(i);
                for (int j = 0; j < actualColumns.size(); j++) {
                    String columnName = actualColumns.get(j);
                    Cell cell = row.getCell(j);
                    String actualValue = getCellValue(cell);

                    // Compare actual vs expected value
                    if (!actualValue.equals(expectedRow.get(columnName))) {
                        System.out.printf("Mismatch at row %d, column %s: expected '%s', but found '%s'%n",
                                i + 1, columnName, expectedRow.get(columnName), actualValue);
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
