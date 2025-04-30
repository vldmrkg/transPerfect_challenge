package tests;

import org.junit.jupiter.api.Test;
import pages.DialogPage;
import utils.TestDataReader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DialogTest extends BaseTest {

    @Test
    public void testDialogInteraction() {
        Map<String, String> testData = TestDataReader.readJson("src/test/resources/dialog-test-data.json");
        String expectedColor = testData.get("expectedYesButtonColor");

        DialogPage dialog = new DialogPage(driver);
        dialog.openPage();
        dialog.openDialog();

        assertTrue(dialog.isDialogDisplayed(), "Dialog is not displayed");
        assertTrue(dialog.areButtonsDisplayed(), "Yes/No buttons are not displayed");

        String actualColor = dialog.getYesButtonBackgroundColor();
        assertEquals(expectedColor, actualColor, "The 'Yes' button color is not as expected");

        dialog.closeDialog();
        assertTrue(dialog.isDialogClosed(), "Dialog is not closed");
    }
}
