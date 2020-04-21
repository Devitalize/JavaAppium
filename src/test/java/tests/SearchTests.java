package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SearchTests extends CoreTestCase {
    @Test
    public void testFindSearchPlaceholder() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String actual_placeholder = SearchPageObject.getArticleSearchPlaceholder();

        assertEquals
                (
                        "Placeholder 'Search...' does not present on the screen",
                        "Search…",
                        actual_placeholder
                );
    }

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCancelSearchAfterClickXButton() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Test");
        SearchPageObject.waitForSearchResult();
        SearchPageObject.assertThereIsResultOfSearch();

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "dfbdrfhbytejdhy";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchThreeResultByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForElementByTitleAndDescription("Java", "sland of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "bject-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "rogramming language");
    }

    @Test
    public void testSearchTermInSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "test";
        SearchPageObject.typeSearchLine(search_line);

        List <WebElement>results = SearchPageObject.getListResultElement();

        for (int i = 0; i < results.size(); i++) {
            Assert.assertTrue("Search result does not contain input word",
                    results.get(i).getAttribute("text").contains("test")||results.get(i).getAttribute("text").contains("Test"));
        }
    }


}
