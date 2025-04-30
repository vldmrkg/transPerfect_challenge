package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object class representing the Window modal functionality.
 * Handles interactions with open, maximize, and close window actions.
 */
public class WindowPage extends BaseClass {

    // Locators for window elements
    private By openWindowButton = By.xpath("//span[normalize-space()='Open window']");
    private By windowName = By.cssSelector(".k-window-title");
    private By windowText = By.cssSelector(".k-window-content");
    private By maximizeButton = By.cssSelector(".k-svg-i-window");
    private By closeButton = By.cssSelector(".k-svg-i-x");

    public WindowPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the "Open window" button to display the modal window.
     */
    public void openWindow() {
        clickElement(openWindowButton);
    }

    /**
     * Verifies whether the opened window contains a valid title and text content.
     *
     * @return true if both window title and content are displayed.
     */
    public boolean isWindowDataCorrect() {
        try {
            waitForElementVisible(windowName);
            waitForElementVisible(windowText);

            WebElement window = driver.findElement(windowName);
            WebElement text = driver.findElement(windowText);

            return window.isDisplayed() && text.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clicks the maximize button to enlarge the window.
     */
    public void maximizeWindow() {
        clickElement(maximizeButton);
    }

    /**
     * Verifies that the maximize button is no longer visible after maximizing the window.
     *
     * @return true if the maximize button disappears (becomes invisible).
     */
    public boolean isMaximizeButtonInvisible() {
        waitForElementInvisible(maximizeButton);
        return true;
    }

    /**
     * Closes the modal window.
     */
    public void closeWindow() {
        clickElement(closeButton);
    }

    /**
     * Checks whether the window has been closed successfully.
     *
     * @return true if the window title is not visible.
     */
    public boolean isWindowClosed() {
        return isElementNotDisplayed(windowName);
    }
}
