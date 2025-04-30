package pages;

import base.BaseClass;
import org.openqa.selenium.*;
import utils.ExcelUtils;

import java.util.*;
import java.util.NoSuchElementException;

/**
 * Page Object class for the Employees Page.
 * Handles filtering employees from the USA and exporting online employees to Excel.
 */
public class EmployeesPage extends BaseClass {

    public EmployeesPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By rows = By.cssSelector("tr.k-master-row");
    private final By nextButton = By.cssSelector(".k-svg-i-caret-alt-right");

    /**
     * Checks if the employee in the given row is from the USA by inspecting the flag image source.
     *
     * @param rowIndex 1-based index of the row
     * @return true if the employee is from the USA, false otherwise
     */
    private boolean isEmployeeFromUSA(int rowIndex) {
        try {
            String flagSelector = "tbody tr:nth-child(" + rowIndex + ") td:nth-child(4) img:nth-child(1)";
            WebElement flagElement = driver.findElement(By.cssSelector(flagSelector));
            String flagSrc = flagElement.getAttribute("src");
            return isUSFlag(flagSrc);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns the trimmed text content of a specific cell in the grid.
     *
     * @param rowIndex Row number (1-based)
     * @param colIndex Column number (1-based)
     * @return Cell text
     */
    private String getCellText(int rowIndex, int colIndex) {
        String selector = "tbody tr:nth-child(" + rowIndex + ") td:nth-child(" + colIndex + ")";
        return driver.findElement(By.cssSelector(selector)).getText().trim();
    }

    /**
     * Navigates to the next page in the grid.
     *
     * @param currentRows List of current row elements (used for comparison)
     * @return true if the page was changed, false if it's already the last page or the button is not available
     */
    private boolean goToNextPage(List<WebElement> currentRows) {
        List<WebElement> nextButtons = driver.findElements(nextButton);
        if (nextButtons.isEmpty() || !isElementDisplayed(nextButton)) return false;

        try {
            String firstRowTextBefore = currentRows.get(0).getText();
            WebElement nextBtn = nextButtons.get(0);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
            sleepSafe(300);
            clickElement(nextButton);

            // Wait for page content to change
            wait.until(d -> {
                List<WebElement> newRows = d.findElements(rows);
                return !newRows.isEmpty() && !newRows.get(0).getText().equals(firstRowTextBefore);
            });

            sleepSafe(500);
            return true;
        } catch (ElementClickInterceptedException e) {
            return false;
        }
    }

    /**
     * Scrolls to the bottom of the page using JavaScript.
     */
    private void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Sleeps safely, ignoring InterruptedException.
     *
     * @param millis Duration in milliseconds
     */
    private void sleepSafe(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Validates if the given base64-encoded flag image corresponds to the US flag.
     *
     * @param flagSrc The src attribute of the flag image
     */
    private boolean isUSFlag(String flagSrc) {
        return flagSrc != null &&
                flagSrc.startsWith("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAAAiCAIAAABgN0jY");
    }

    /**
     * Collects a list of online employees from the USA by traversing all pages in the grid.
     *
     * @return List of employees (as maps) containing only Name, JobTitle, Phone, and Address
     */
    public List<Map<String, String>> getOnlineEmployeesFromUSA() {
        List<Map<String, String>> filteredEmployees = new ArrayList<>();

        while (true) {
            waitForElementVisible(rows);
            List<WebElement> allRows = driver.findElements(rows);
            scrollToBottom();
            sleepSafe(1000);

            for (int i = 1; i <= allRows.size(); i++) {
                try {
                    if (!isEmployeeFromUSA(i)) continue;

                    String name = getCellText(i, 2);
                    String jobTitle = getCellText(i, 3);
                    String phone = getCellText(i, 9);
                    String address = getCellText(i, 10);
                    String status = getCellText(i, 5);

                    System.out.printf("[USA EMPLOYEE] Name: %s | Title: %s | Phone: %s | Address: %s%n",
                            name, jobTitle, phone, address);

                    if (status.equalsIgnoreCase("Online")) {
                        Map<String, String> employee = new HashMap<>();
                        employee.put("Name", name);
                        employee.put("JobTitle", jobTitle);
                        employee.put("Phone", phone);
                        employee.put("Address", address);
                        filteredEmployees.add(employee);
                    }
                } catch (Exception ignored) {
                    // Ignore row if parsing fails
                }
            }

            if (!goToNextPage(allRows)) break;
        }

        return filteredEmployees;
    }

    /**
     * Exports the given list of employees to an Excel file.
     *
     * @param employees List of employee data to export
     * @param filePath  Target file path
     */
    public void exportToExcel(List<Map<String, String>> employees, String filePath) {
        ExcelUtils.writeEmployeesToExcel(employees, filePath);
    }
}
