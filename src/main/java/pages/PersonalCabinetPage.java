package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsPersonalCabinetPage.*;

public class PersonalCabinetPage extends BasePage {

    @Step("Сохранить изменения в личном кабинете")
    public void clickSaveButton() {
        clickElement($x(SAVE_BUTTON), "Кнопка 'Сохранить'");
    }

    @Step("Получить текст с кнопки 'Сохранить'")
    public String getSaveButtonText() {
        return $x(SAVE_BUTTON).text();
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickExitButton() {
        clickElement($x(EXIT_BUTTON), "Кнопка 'Выход'");
    }

}
