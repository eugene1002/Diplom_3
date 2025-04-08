package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsMainPage.*;

public class MainPage extends BasePage {

    @Step("Открытие личного кабинета")
    public void openPersonalCabinet() {
        clickElement($x(PERSONAL_CABINET_BUTTON), "Кнопка 'Личный кабинет'");
    }

    @Step("Переход в конструктор")
    public void goToConstructor() {
        clickElement($x(CONSTRUCTOR_BUTTON), "Кнопка 'Конструктор'");
    }

    @Step("Ожидание заголовка 'Соберите бургер'")
    public void waitForBurgerTitle() {
        shouldBeVisible($x(MAKE_BURGER_TITLE), "Заголовок 'Соберите бургер'");
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        clickElement($x(BUNS_TAB), "Вкладка 'Булки'");
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        clickElement($x(SAUCES_TAB), "Вкладка 'Соусы'");
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        clickElement($x(FILLINGS_TAB), "Вкладка 'Начинки'");
    }

    @Step("Оформить заказ")
    public void clickMakeOrder() {
        clickElement($x(BUTTON_MAKE_ORDER), "Кнопка 'Оформить заказ'");
    }

    @Step("Проверка, что активная вкладка: Булки")
    public void shouldSeeBunsTabActive() {
        $x(ACTIVE_ELEMENT_CONSTRUCTOR).shouldHave(text("Булки"));
    }

    @Step("Проверка, что активная вкладка: Соусы")
    public void shouldSeeSaucesTabActive() {
        $x(ACTIVE_ELEMENT_CONSTRUCTOR).shouldHave(text("Соусы"));
    }

    @Step("Проверка, что активная вкладка: Начинки")
    public void shouldSeeFillingsTabActive() {
        $x(ACTIVE_ELEMENT_CONSTRUCTOR).shouldHave(text("Начинки"));
    }

    @Step("Получить название активной вкладки конструктора")
    public String getActiveTabText() {
        return $x(ACTIVE_ELEMENT_CONSTRUCTOR).text();
    }

    @Step("Получить текст с кнопки 'Оформить заказ'")
    public String getMakeOrderButtonText() {
        return $x(BUTTON_MAKE_ORDER).text();
    }

    @Step("Переход в личный кабинет")
    public void goToPersonalCabinet() {
        clickElement($x(PERSONAL_CABINET_BUTTON), "Кнопка 'Личный кабинет'");
    }


}
