package pages;

import base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object class for handling the dropdowns and autocomplete inputs on the DropDowns page.
 */
public class DropDownsPage extends BaseClass {

    public DropDownsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By sportInput = By.xpath("//input[@placeholder='Your favorite sport']");
    private By suggestionItem = By.cssSelector(".k-list-item");
    private By clearButton = By.xpath("//kendo-autocomplete[@placeholder='Your favorite sport']//*[@class='k-svg-i-x k-svg-icon k-icon']");
    private By multiSelectInput = By.cssSelector(".k-input-values .k-input-inner");
    private By dropdownContainer = By.cssSelector(".k-list-content");
    private By dropdownItems = By.cssSelector(".k-list-item");
    private By selectedChips = By.cssSelector(".k-chip-list .k-chip");

    /**
     * Selects a sport using the autocomplete input field.
     *
     * @param sport The sport name to be selected.
     */
    public void selectFavoriteSport(String sport) {
        waitForElementVisible(sportInput);

        WebElement inputField = driver.findElement(sportInput);
        inputField.click();
        inputField.clear();
        inputField.sendKeys(sport);

        waitForElementVisible(suggestionItem);
        inputField.sendKeys(Keys.RETURN);
    }

    /**
     * Verifies the selected sport in the autocomplete input matches the expected value.
     *
     * @param expectedSport Expected sport name.
     */
    public void verifySelectedSport(String expectedSport) {
        WebElement inputField = driver.findElement(sportInput);
        String actualValue = inputField.getAttribute("value");

        if (!actualValue.equalsIgnoreCase(expectedSport)) {
            throw new AssertionError("Expected: " + expectedSport + ", but got: " + actualValue);
        }
    }

    /**
     * Clears the selected sport using the 'X' button.
     */
    public void clearSportSelection() {
        WebElement clearBtn = driver.findElement(clearButton);
        clearBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(clearButton));
    }

    /**
     * Verifies that the sport selection is cleared and input is empty.
     */
    public void verifySportSelectionCleared() {
        try {
            driver.findElement(clearButton);
            throw new AssertionError("Clear button is still present after clearing the input.");
        } catch (NoSuchElementException ignored) {
            // Expected - clear button should be gone
        }

        WebElement inputField = driver.findElement(sportInput);
        String clearedValue = inputField.getAttribute("value");

        if (!clearedValue.isEmpty()) {
            throw new AssertionError("Sport selection was not cleared! Still present: " + clearedValue);
        }
    }

    /**
     * Verifies that the dropdown contains the expected items.
     *
     * @param expectedItems List of expected dropdown values.
     */
    public void verifyDropdownItemsVisible(String... expectedItems) {
        waitForElementVisible(dropdownItems);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(dropdownItems, expectedItems.length - 1));

        List<String> visibleItemTexts = driver.findElements(dropdownItems).stream()
                .map(item -> item.getText().trim())
                .collect(Collectors.toList());

        for (String expected : expectedItems) {
            if (visibleItemTexts.stream().noneMatch(text -> text.equalsIgnoreCase(expected))) {
                throw new AssertionError("Expected dropdown item not visible: " + expected);
            }
        }
    }

    /**
     * Opens the multi-select dropdown.
     */
    public void openMultiSelectDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(multiSelectInput));
        WebElement input = driver.findElement(multiSelectInput);
        input.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownContainer));
    }

    /**
     * Selects an option from the multi-select dropdown.
     *
     * @param optionText The text of the option to be selected.
     */
    public void selectOptionFromDropdown(String optionText) {
        WebElement input = driver.findElement(multiSelectInput);
        input.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownContainer));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(dropdownItems, 0));

        WebElement dropdownList = driver.findElement(dropdownContainer);
        List<WebElement> options = dropdownList.findElements(dropdownItems);

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(optionText)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                return;
            }
        }

        throw new NoSuchElementException("Option '" + optionText + "' not found in dropdown.");
    }

    /**
     * Verifies that the expected sports are shown as selected chips.
     *
     * @param expectedSports Array of expected selected sports.
     */
    public void verifySelectedSports(String... expectedSports) {
        List<WebElement> selected = driver.findElements(selectedChips);

        for (String expected : expectedSports) {
            boolean found = selected.stream()
                    .anyMatch(chip -> chip.getText().trim().equalsIgnoreCase(expected));

            if (!found) {
                throw new AssertionError("Expected selected sport not found: " + expected);
            }
        }
    }
}
