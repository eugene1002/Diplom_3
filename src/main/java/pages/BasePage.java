package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class BasePage {

    @Step("Клик на элемент: {elementName}")
    public void clickElement(SelenideElement element, String elementName) {
        element.shouldBe(visible, enabled).click();
    }

    @Step("Ввод текста в поле \"{fieldName}\": {text}")
    public void enterText(SelenideElement field, String fieldName, String text) {
        field.shouldBe(visible, enabled).setValue(text);
    }

    @Step("Проверка, что элемент \"{elementName}\" отображается")
    public void shouldBeVisible(SelenideElement element, String elementName) {
        element.shouldBe(visible);
    }

    @Step("Проверка текста в элементе \"{elementName}\": ожидается [{expected}]")
    public void shouldHaveText(SelenideElement element, String elementName, String expected) {
        element.shouldHave(text(expected));
    }
}
