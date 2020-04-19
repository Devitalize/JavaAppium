package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */

    //Изменение подстроки для поиска нужного заголовка
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    //Изменение подстроки для поиска по тайтлу и описанию одновременно
    private static String getResultSearchElementByTitleAndDescription(String title, String description) {
        String search_result_replace_title = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION.replace("{TITLE}", title);
        return search_result_replace_title.replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    //Клик и ожидание строки поиска
    public void initSearchInput() {
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                20);
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search input element",
                15);
    }

    //Ввод текста в строку поиска
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                10);
    }

    //Ожидание появления нужного результата
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    //Ожидание появления списка с результатами
    public void waitForSearchResult() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find search result with substring ");
    }

    //Поиск кнопки закрытия поиска
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                10);
    }

    //Проверка, что кнопка закрытия поиска исчезла
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                10);
    }

    //Клик по пнопке закрытия поиска
    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                10);
    }

    //Клик по нужному заголовку среди результатов
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10);
    }

    //Возвращает кол-во элементов в результатах поиска
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(
                SEARCH_RESULT_ELEMENT);
    }

    //проверка наличия сообщения при отсутствии результатов
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
    }

    //Проверка, что результатов поиска нет
    public void assertThereIsNoResultOfSearch() {
        this.assertElementsNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not find any results");
    }

    //Проверка, что есть хоть один результат поиска
    public void assertThereIsResultOfSearch() {
        this.assertElementsPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find search results");
    }

    //Извлечение текста плейсхолдера в строке поиска
    public String getArticleSearchPlaceholder() {
        WebElement title_element = this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot find placeholder on page",
                15);
        return title_element.getAttribute("text");
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find and search result with substring " + title + " and " + description,
                20);
    }

}
