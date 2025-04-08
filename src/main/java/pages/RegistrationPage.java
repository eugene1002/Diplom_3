package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsMainPage.PERSONAL_CABINET_BUTTON;
import static locators.LocatorsAuthPage.REGISTER_LINK;
import static locators.LocatorsRegistrationPage.*;

public class RegistrationPage extends BasePage {

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickOnPersonalCabinetButton() {
        clickElement($x(PERSONAL_CABINET_BUTTON), "Кнопка 'Личный кабинет'");
    }

    @Step("Переход по ссылке 'Зарегистрироваться'")
    public void clickOnRegisterLink() {
        clickElement($x(REGISTER_LINK), "Ссылка 'Зарегистрироваться'");
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        enterText($x(NAME_INPUT), "Поле 'Имя'", name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        enterText($x(EMAIL_INPUT), "Поле 'Email'", email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        enterText($x(PASSWORD_INPUT), "Поле 'Пароль'", password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        clickElement($x(REGISTER_BUTTON), "Кнопка 'Зарегистрироваться'");
    }

    @Step("Переход на страницу входа")
    public void goToLogin() {
        clickElement($x(SIGN_IN_LINK), "Ссылка 'Войти'");
    }

    @Step("Проверка текста ошибки пароля: '{expectedText}'")
    public void checkIncorrectPasswordMessage(String expectedText) {
        $x(INCORRECT_PASSWORD_TEXT).shouldHave(text(expectedText));
    }

    @Step("Регистрация пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        clickOnPersonalCabinetButton();
        clickOnRegisterLink();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}
