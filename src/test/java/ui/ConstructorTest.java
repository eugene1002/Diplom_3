package ui;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@DisplayName("[UI] Переход по разделам конструктора")
public class ConstructorTest extends BaseUiTest {

    @Test
    @DisplayName("Переход к разделу Булки")
    public void successTransferToBunInConstructorTest() {
        mainPage.clickSaucesTab(); // делаем переход на другую вкладку, т.к. текущая - по умолчанию
        mainPage.clickBunsTab();
        assertActiveTabIs("Булки");
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void successTransferToSaucesInConstructorTest() {
        mainPage.clickSaucesTab();
        assertActiveTabIs("Соусы");
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void successTransferToFillingsInConstructorTest() {
        mainPage.clickFillingsTab();
        assertActiveTabIs("Начинки");
    }

    @Step("Проверка, что активная вкладка: \"{expected}\"")
    private void assertActiveTabIs(String expected) {
        String actual = mainPage.getActiveTabText();
        assertEquals(expected, actual);
    }
}
