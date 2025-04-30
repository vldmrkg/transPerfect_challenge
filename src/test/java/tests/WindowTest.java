package tests;

import org.junit.jupiter.api.Test;
import pages.WindowPage;

import static org.junit.jupiter.api.Assertions.*;

public class WindowTest extends BaseTest {

    @Test
    public void testWindowFunctionality() {
        WindowPage windowPage = new WindowPage(driver);
        windowPage.openPage();

        windowPage.openWindow();

        assertTrue(windowPage.isWindowDataCorrect(), "Window data is incorrect");

        windowPage.maximizeWindow();

        assertTrue(windowPage.isMaximizeButtonInvisible(), "Maximize button is still visible");
        windowPage.closeWindow();

        assertTrue(windowPage.isWindowClosed(), "Window is not closed");
    }
}