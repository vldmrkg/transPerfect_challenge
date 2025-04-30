package tests;

import org.junit.jupiter.api.Test;
import pages.DropDownsPage;
import utils.TestDataReader;

import java.util.List;
import java.util.Map;

public class MultiSelectSportTest extends BaseTest {

    @Test
    public void testMultiSelectFavoriteSports() {
        Map<String, Object> testData = TestDataReader.readJsonArray("src/test/resources/multiselect-dropdown-test-data.json");

        List<String> dropdownItems = (List<String>) testData.get("dropdownItems");
        List<String> selectedSports = (List<String>) testData.get("selectedSports");

        DropDownsPage dropDownsPage = new DropDownsPage(driver);
        dropDownsPage.openDropDownsPage();

        dropDownsPage.openMultiSelectDropdown();
        dropDownsPage.verifyDropdownItemsVisible(dropdownItems.toArray(new String[0]));

        for (String sport : selectedSports) {
            dropDownsPage.openMultiSelectDropdown();
            dropDownsPage.selectOptionFromDropdown(sport);
        }

        dropDownsPage.verifySelectedSports(selectedSports.toArray(new String[0]));
    }
}