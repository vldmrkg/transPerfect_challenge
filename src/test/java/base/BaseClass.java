package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BaseClass provides reusable Selenium utilities and page navigation logic.
 * All page objects should extend this class.
 */
public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navigates to the Dialogs demo page.
     */
    public void openPage() {
        driver.get("https://demos.telerik.com/kendo-angular-ui/demos/dialogs/preview?theme=default-main");
    }

    /**
     * Navigates to the DropDowns demo page.
     */
    public void openDropDownsPage() {
        driver.get("https://demos.telerik.com/kendo-angular-ui/demos/dropdowns/overview?theme=default-main");
    }

    /**
     * Navigates to the Employees Grid page.
     */
    public void openEmployeesPage() {
        driver.get("https://demos.telerik.com/kendo-angular-ui/demos/grid/filter-all-columns?theme=default-main");
    }

    /**
     * Waits for element to be visible and clicks it.
     *
     * @param locator The element locator.
     */
    public void clickElement(By locator) {
        waitForElementVisible(locator);
        driver.findElement(locator).click();
    }

    /**
     * Checks if the element is displayed on the page.
     *
     * @param locator The element locator.
     * @return true if displayed, false otherwise.
     */
    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    /**
     * Gets the background-color CSS value of an element.
     *
     * @param locator The element locator.
     * @return CSS background color value as string.
     */
    public String getElementBackgroundColor(By locator) {
        return driver.findElement(locator).getCssValue("background-color");
    }

    /**
     * Returns true if the element is not present or not visible.
     *
     * @param locator The element locator.
     * @return true if element is not displayed.
     */
    public boolean isElementNotDisplayed(By locator) {
        try {
            return !driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Waits until the element becomes visible.
     *
     * @param locator The element locator.
     */
    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element becomes invisible.
     *
     * @param locator The element locator.
     */
    public void waitForElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Performs a hover (mouse over) action on the given element.
     *
     * @param locator The element locator.
     */
    public void hoverElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Waits until the provided custom condition is met.
     *
     * @param condition A custom expected condition.
     */
    public void waitForCondition(ExpectedCondition<?> condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(condition);
    }
}
