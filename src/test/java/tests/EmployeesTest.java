package tests;

import org.junit.jupiter.api.Test;
import pages.EmployeesPage;
import utils.ExcelUtils;
import utils.TestDataReader;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeesTest extends BaseTest {

    @Test
    public void testExportOnlineEmployeesFromUSA() {
        Map<String, Object> testData = TestDataReader.readJsonArray("src/test/resources/employees-test-data.json");

        String filePath = (String) testData.get("exportFilePath");
        List<String> expectedColumns = (List<String>) testData.get("expectedColumns");

        EmployeesPage employeesPage = new EmployeesPage(driver);
        employeesPage.openEmployeesPage();

        List<Map<String, String>> onlineUSAEmployees = employeesPage.getOnlineEmployeesFromUSA();
        employeesPage.exportToExcel(onlineUSAEmployees, filePath);

        assertTrue(
                ExcelUtils.verifyExportedExcel(filePath, onlineUSAEmployees, expectedColumns),
                "Excel file verification failed."
        );
    }
}