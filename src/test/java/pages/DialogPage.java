package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object class representing the Dialog component on the page.
 * Handles dialog interactions such as opening, checking visibility,
 * verifying buttons, getting styles, and closing via keyboard input.
 */
public class DialogPage extends BaseClass {

    // Locators for elements inside the dialog
    private By openDialogButton = By.xpath("//span[normalize-space()='Open dialog']");
    private By dialogTitle = By.cssSelector(".k-dialog-title");
    private By yesButton = By.xpath("//button/span[text()='Yes']/..");
    private By noButton = By.xpath("//button/span[text()='No']/..");
    private By closeIcon = By.cssSelector(".k-svg-i-x");

    public DialogPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the "Open dialog" button to display the dialog window.
     */
    public void openDialog() {
        clickElement(openDialogButton);
    }

    /**
     * Checks if the dialog window is currently displayed.
     *
     * @return true if the dialog title element is visible.
     */
    public boolean isDialogDisplayed() {
        return isElementDisplayed(dialogTitle);
    }

    /**
     * Checks if both "Yes" and "No" buttons are visible inside the dialog.
     *
     * @return true if both buttons are visible.
     */
    public boolean areButtonsDisplayed() {
        return isElementDisplayed(yesButton) && isElementDisplayed(noButton);
    }

    /**
     * Returns the background color of the "Yes" button.
     *
     * @return CSS background-color value as a string.
     */
    public String getYesButtonBackgroundColor() {
        return getElementBackgroundColor(yesButton);
    }

    /**
     * Checks whether the dialog window is closed (i.e., not visible).
     *
     * @return true if the dialog title is not visible.
     */
    public boolean isDialogClosed() {
        return isElementNotDisplayed(dialogTitle);
    }

    /**
     * Closes the dialog by hovering over the close icon and pressing Enter (Return key).
     * Simulates keyboard interaction for accessibility validation.
     */
    public void closeDialog() {
        hoverElement(closeIcon);
        waitForCondition(ExpectedConditions.elementToBeClickable(closeIcon));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.RETURN).perform();
    }
}
