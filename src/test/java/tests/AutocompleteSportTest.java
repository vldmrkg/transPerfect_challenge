package tests;

import org.junit.jupiter.api.Test;
import pages.DropDownsPage;
import utils.TestDataReader;
import java.util.Map;

public class AutocompleteSportTest extends BaseTest {

    @Test
    public void testSelectAndClearSport() {
        Map<String, String> testData = TestDataReader.readJson("src/test/resources/dropdown-test-data.json");

        DropDownsPage dropDownsPage = new DropDownsPage(driver);
        dropDownsPage.openDropDownsPage();

        String sport = testData.get("favoriteSport");
        dropDownsPage.selectFavoriteSport(sport);

        dropDownsPage.verifySelectedSport(sport);

        dropDownsPage.clearSportSelection();
        dropDownsPage.verifySportSelectionCleared();
    }
}